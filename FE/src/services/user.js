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
         url : BASE_URL+'authorizationserver/oauth/token',
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
      $.ajax({
         url:url,
         method:'post',
         data:JSON.stringify(user),
         contentType:'Application/json',
         dataType:'json',
         success:(data)=>{
            success(data)
         },
         error:(response) => {
            this.accessHandler(response);
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
   addCoins(coin,success, error){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/gameData?coins='+coin;
      $.ajax({
         url,
         method:'POST',
         success:function(data){
            user.gameData.coins = data.coins;
            let userbadge = user.gameData.badge.level;
            if(data.badge.level != userbadge){
               Common.Alert('new badge received '+data.badge.level);
            }
            localStorage.setItem('userData', JSON.stringify(user));
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
   addToCart(p,success){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/carts/current/entries?code='+p;
      let that = this;
      $.ajax({
         method:'POST',
         url:url,
         success:function(data){
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
            success(data)
         },
         error:function(r){
            that.accessHandler(r);
            Common.Alert('Couldn\'t update cart');
         }
      })
   },
   myCart(success, error){
      let user = JSON.parse(localStorage.userData);
      let url = USER_BASE_URL + user.uid + '/carts/current/';
      $.ajax({
         url,
         method:'GET',
         success:function(data){
            success(data);
         },
         error:function(){
            this.accessHandler();
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
   }

}

