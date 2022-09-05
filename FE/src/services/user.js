import * as $ from 'jquery'

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
            alert('session expired. please refresh the page');
            this.setHeader()
      }

   },
   registerUser(user, success,error){
      let url = USER_BASE_URL;
      $.ajax({
         url:url,
         method:'post',
         data:user,
         contentType:'json',
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
      let user = localStorage.user;
      localStorage.setItem('coin',coin);
      let url = USER_BASE_URL + user + '/gameData?coins='+coin;
      $.ajax({
         url,
         method:'POST',
         success:function(data){
            success(data);
         },
         error:function(){
            error()
         }
      })
   }

}

