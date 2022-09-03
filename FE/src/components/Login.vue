<template>
<div class="login">
    <div class="ui  segment">
  <div class="ui two column very relaxed stackable grid">
    <div class="column">
      <h3 class="ui header huge  teal"> Login </h3>
      <div class="ui form">
        <div class="field">
          <label>Username</label>
          <div class="ui left icon input">
            <input v-model = "login.uid" type="text" placeholder="Username">
            <i class="user icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Password</label>
          <div class="ui left icon input">
            <input v-model = "login.password" type="password">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="ui blue primary button" v-on:click="login()">Login</div>
      </div>
    </div>
    <div class="middle aligned column">
      <h3 class="ui header  huge teal"> Register </h3>
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
            <input v-model="user.firstName" type="text" placeholder="Firstname">
            <input v-model="user.lastName" type="text" placeholder="Last Name">
          </div>
        </div>
        <div class="field">
          <label>Email</label>
          <div class="ui left icon input">
            <input v-model = "user.email" required type="Email" placeholder="Email">
            <i class="envelope icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Password</label>
          <div class="ui left icon input">
            <input v-model = "user.password" type="password">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="field">
          <label>ConfirmPassword</label>
          <div class="ui left icon input">
            <input type="password" v-model = "confirmpassword">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="ui blue primary button" v-on:click="register()">Register</div>
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
export default {
  name: 'UserLogin',
  data(){
    return {
      user:{
        titleCode:'mr'
      },
      confirmpassword:'',
      error:{

      },
      login:{}
    }
  },
  methods:{
    checkpassword(){}
  }
}
</script>
<style scoped>
.login {
  margin:  48px auto;
  width:  900px;
  max-width: 80%;
}
</style>
