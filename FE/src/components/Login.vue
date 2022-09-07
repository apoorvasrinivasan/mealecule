<template>
<div class="login">
    <div class="ui  segment">
  <div class="ui two column very relaxed stackable grid">
    <div class="column">
      <h3 class="ui header huge  teal"> Login </h3>
      <div class="ui form">
        <div class="ui error message" v-if="log_error"> {{log_error}} </div>
        <div class="field">
          <label>Username</label>
          <div class="ui left icon input">
            <input v-model = "ulogin.uid" type="text" placeholder="Username">
            <i class="user icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Password</label>
          <div class="ui left icon input">
            <input v-model = "ulogin.password" type="password">
            <i class="lock icon"></i>
          </div>
        </div>
        <button role="button" tabindex="0" class="ui blue primary button" v-on:click="login()">Login</button>
      </div>
    </div>
    <div class="middle aligned column">
      <h3 class="ui header  huge teal"> Register </h3>
       <div class="ui success message" v-show="reg_success">
          User registered successfully
       </div>
       <div class="ui error message" v-show="reg_error">
          {{ reg_error }}
       </div>
       <div class="ui form">
        <div class="field">
          <label>Name</label>
          <div class="ui action input">
            <select v-model="user.titleCode" class="ui compact selection dropdown">
            <option value="mr">Mr</option>
            <option value ="ms">Ms</option>
            <option value="mrs">Mrs</option>
            <option value="dr">Dr</option>
          </select>
            <input v-model="user.firstName" type="text" placeholder="Firstname"  required>
            <input v-model="user.lastName" type="text" placeholder="Last Name"  required>
          </div>
        </div>
        <div class="field">
          <label>Email</label>
          <div class="ui left icon input">
            <input v-model = "user.uid" required type="Email" placeholder="Email">
            <i class="envelope icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Password</label>
          <div class="ui left icon input">
            <input v-model = "user.password" type="password" required v-on:blur="checkpassword()">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="field" :class="{'error':passerror}">
          <label>ConfirmPassword</label>
          <div class="ui left icon input" >
            <input type="password" v-model = "confirmpassword" required v-on:blur="checkpassword()">
            <i class="lock icon"></i>
          </div>
          <div class="ui error" v-if="passerror" >passwods do not match</div>
        </div>
        <button type="submit" tabindex=0 role="button" class="ui blue primary button" v-on:click="register()">Register
          <div class="ui circular label tiny bCoins"> 100</div>
        </button>
      </div>
    </div>
  </div>
  <div class="ui vertical divider">
    Or
  </div>
</div>
  </div>

</template>

<script>
import User from '../services/user'

export default {
  name: 'UserLogin',
  data(){
    return {
      reg_success: false,
      log_error: '',
      reg_error: '',
      passerror:false,
      ulogin:{
        uid:'test19919@yahoo.com',
        password:'Test@123'
      },
      user:{
        titleCode:'mr',
        uid:'',
        password:'',
        firstName:'',
        lastName:''
      },
      confirmpassword:''
    }
  },
  methods:{
    checkpassword(){
      let vm = this;
      vm.passerror = false;
      if (vm.user.password.trim().length > 0 &&  vm.confirmpassword.trim().length > 0 && vm.user.password != vm.confirmpassword)
        vm.passerror = true;
    },
    register(){
      let vm = this;
      vm.reg_error = '';
      vm.reg_success = false;
      for (let i in vm.user){
        if( vm.user[i].trim().length == 0){
          vm.reg_error = i + ' is required';
          return;
        }
      }
      User.registerUser(vm.user, ()=>{
        setTimeout(()=>{
          vm.reg_success = false
        }, 1000);
      }, (r)=>{
        vm.reg_error = r.errors[0].message;
         console.log(r)
      })
    },
    login: function(){
      let vm = this;
      vm.log_error = '';
      for (let i in vm.ulogin){
        if( vm.ulogin[i].trim().length == 0){
          vm.log_error = i + ' is required';
          return;
        }
      }
      User.loginUser(vm.ulogin, (data)=>{
        localStorage.setItem('user',vm.ulogin.uid);
        let userData = {
          uid: data.uid,
          name: data.name,
          firstName: data.firstName,
          gameData: data.gameData,
          preferredMealecule: data.preferredMealecule
        }
        localStorage.setItem('userData',JSON.stringify(userData));
        vm.$root.preferredMealeacule = data.preferredMealeacule;
        vm.$root.total_coins = data.gameData.coins;
        User.createCart(()=>{
            vm.$root.cart = 0;
            vm.$router.go('/categories');
        })
      }, (r)=> {
        vm.log_error = r.errors[0].message;
         console.log(r)
      })
    }
  }
}
</script>
<style scoped>
.login {
  margin:  48px auto;
  width:  900px;
  max-width: 90%;
}
.error{
  color: var(--red);
}
.button .bCoins {
  margin-left: 10px;
  font-size: .5em;
}
@media screen and (max-width: 800px) {
  .ui.divider{display:none;}
  .ui.action.input{
    flex-flow: column;
    gap: 15px;
  }
  .ui.button{
    width:100%;
    margin-bottom: 24px;
  }
}
</style>
