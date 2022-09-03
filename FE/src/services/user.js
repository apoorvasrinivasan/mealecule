import * as $ from 'jquery'

let BASE_URL = "/api";
let USER_BASE_URL + "/api/mealeculecommercewebservices/v2/mealecule/users"
      
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
           }
         })
      }, (data)=>{
         console.log(data);
      })
   },
   registerUser(user, success,error){
      let url = USER_BASE_URL;
      $.ajax({
         method:'post',
         data:user,
         contentType:'json',
         success:(data)=>{
            success(data)
         }
      })

   }

}

