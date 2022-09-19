<template>
<div class="login">
    <div class="ui  segment">
  <div class="ui two column very relaxed stackable grid">
    <div class="column">
      <h3 class="ui header huge  teal"> Login </h3>
        <div class="ui error message" v-if="log_error"> {{log_error}} </div>
      <div class="ui form">
        <div class="field">
          <label>Username</label>
          <div class="ui left icon input">
            <input v-model = "ulogin.uid" type="email" placeholder="Username">
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
            <input v-model="user.firstName" type="email" placeholder="Firstname"  required>
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
          <div class="ui circular label tiny bCoins"> 50</div>
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

<script src="@/assets/js/login.js">
  export default {
    name:'UserLogin'
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
.ui.compact.selection.dropdown {
    min-width: 55px;
    padding: 3px;
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
