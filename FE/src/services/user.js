import * as $ from 'jquery'
import Common from './common'

let BASE_URL = "/api";
let USER_BASE_URL = BASE_URL + "/mealeculecommercewebservices/v2/mealecule/users/"
      
export default {
   getAuthToken(success, error) {
      let token = localStorage.getItem('token')
      if(token){
         success(token);
         return
      }
        $.ajax({
         url : BASE_URL+'/authorizationserver/oauth/token',
         method:'POST',
         headers:{
            "Access-Control-Allow-Origin": "*",
         },
         data : {
            "client_id":"trusted_client",
            "grant_type":"client_credentials",
            "client_secret":"secret",
            "scope":"extended"
         }
      }).then(
            (response) => {
               token = response.access_token
               localStorage.setItem('token',token);
                setTimeout(function(){
                  localStorage.removeItem('token');
                }, response.expires_in)
                success(token)
            },
            (response) => {
                error(response)
            }
        )
   },
   setHeader() {
      this.getAuthToken((token)=>{
         $.ajaxSetup({
            headers:{
               "Authorization" : "Bearer "+token,
               "contentType":"Application/json"
           }
         })
      }, (data)=>{
         console.log(data);
      })
   },
   accessHandler(response){
      if(response.status == 401) {
         if(response.responseJSON.errors[0].type == "InvalidTokenError")
            localStorage.removeItem('token')
            Common.Alert('session expired. please refresh the page');
            this.setHeader()
      }

   },
   registerUser(user, success,error){
      let url = USER_BASE_URL.slice(0,-1);
      let that =  this;
      $.ajax({
         url:url,
         method:'post',
         data:JSON.stringify(user),
         contentType:'application/json',
         dataType:'json',
         success:(data)=>{
            success(data)
         },
         error:(response) => {
            that.accessHandler(response);
            error(response.responseJSON)
         }
      })

   },
   updateUser(user, success,error){
      let that = this;
      let url = `${USER_BASE_URL}${user.uid}/customerPersonalData?height=${user.height}&weight=${user.weight}&age=${user.age}`;
      $.ajax({
         url:url,
         method:'post',
         contentType:'json',
         success:(data)=>{
            success(data)
         },
         error:(response) => {
            that.accessHandler(response);
            Common.Alert('some error occured')
            error(response.responseJSON)
         }
      })

   },
   loginUser(user, success,error){
      let url = USER_BASE_URL + user.uid;
      $.ajax({
         url:url,
         method:'get',
         data:user,
         contentType:'json',
         success:(data)=>{
            success(data)
         },
         error:(response) => {
            this.accessHandler(response);
            error(response.responseJSON)
         }
      })

   },
   addRegCoins(coin, user){
      let url = USER_BASE_URL + user + '/gameData?coins='+coin;
      $.ajax({
         url,
         method:'POST'
      });
   },
   addCoins(coin,success, error){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/gameData?coins='+coin;
      $.ajax({
         url,
         method:'POST',
         success:function(data){
            let userbadge = (user.gameData.badge)?user.gameData.badge.level : '';
            if(data.badge && data.badge.level != userbadge){
               Common.Alert('new badge received '+data.badge.level);
            }
            user.gameData = data;
            localStorage.userData =  JSON.stringify(user);
            success(data);
         },
         error:function(){
            error()
         }
      })
   },
   createCart(success){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/carts';
      $.ajax({
         url,
         method:'POST',
         success:function(){
            user.cart = 0;
            localStorage.setItem('userData',JSON.stringify(user));
            success();
         }
      })
   },
   addToCart(p ,success){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/carts/current/entries?code='+p;
      let that = this;
      $.ajax({
         method:'POST',
         url:url,
         success:function(data){
            console.log(data)
            user.cart ++;
            user.cartMQ = data.mealeculeQuotientData;
            localStorage.setItem('userData',JSON.stringify(user));
            Common.Alert('Added to cart successfully');
            success(data);
         },
         error:function(response){
            that.accessHandler(response);
            Common.Alert('Couldn\'t add to cart');
         }
      })
   },
   updateCart(ind, qnty, success){
      let user = JSON.parse(localStorage.userData);
      let method = (qnty > 0) ? "PATCH" : 'DELETE'
      let url = `${USER_BASE_URL}${user.uid}/carts/current/entries/${ind}?qty=${qnty}`;
      let that = this;
      $.ajax({
         method:method,
         url,
         success:function(data){
            data.totalItems = user.cart;
            localStorage.userData = JSON.stringify(user);
            success(data)
         },
         error:function(r){
            that.accessHandler(r);
            Common.Alert('Couldn\'t update cart');
         }
      })
   },
   myCart(success, error){
      let that =this;
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/carts/current/?fields=FULL';
      $.ajax({
         url,
         method:'GET',
         success:function(data){
            user.cartMQ = data.mealeculeQuotientData;
            user.cart = data.totalItems
            localStorage.userData = JSON.stringify(user);

            success(data);
         },
         error:function(){
            that.accessHandler();
            error();
         }
      })
   },
   postMealecule(preferredMealecule, success, error){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/preferredMealecules?preferredMealecule='+preferredMealecule;
      $.ajax({
         url,
         method:'POST',
         success:function(data){
            Common.Alert('Saved')
            user.preferredMealecule = data.preferredMealecule;
            localStorage.setItem('userData', JSON.stringify(user));
            success(data);
         },
         error:function(){
            this.accessHandler();
            error();
         }
      })
   },
   updateAddress(user){
      return new Promise((resolve) => {
         $.ajax({
            url:`${USER_BASE_URL}${user.id}/carts/current/addresses/delivery`,
            method:'post',
            data:JSON.stringify(user),
            contentType:'application/json',
            dataType:'json',
            success:function(d){
               resolve(d)
            }
         })
      })
   },
   updatePayment(user){
      return new Promise((resolve) => {
         $.ajax({
            url:`${USER_BASE_URL}${user.id}/carts/current/paymentdetails`,
            method:'post',
            data:JSON.stringify(user),
            contentType:'application/json',
            dataType:'json',
            success:function(d){
               resolve(d)
            }
         })
      })
   },
   placeOrder(user){
      return new Promise((resolve, reject) => {
         $.ajax({
            url:`${USER_BASE_URL}${user.id}/orders?cartId=current`,
            method:'post',
            data:JSON.stringify(user),
            contentType:'application/json',
            dataType:'json',
            success:function(d){
               Common.Alert('order placed successfully');
               resolve(d)
            },
            error: function(r){
               reject(r)
            }
         })
      })
   },
   userOrders(user){
      return new Promise((resolve, reject) => {
         $.ajax({
            url:`${USER_BASE_URL}${user.uid}/orders`,
            success:function(d){
               resolve(d)
            },
            error: function(r){
               reject(r)
            }
         })
      })
   }
}     

