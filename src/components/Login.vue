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
            <input type="text" placeholder="Username">
            <i class="user icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Password</label>
          <div class="ui left icon input">
            <input type="password">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="ui blue submit button">Login</div>
      </div>
    </div>
    <div class="middle aligned column">
      <h3 class="ui header  huge teal"> Register </h3>
       <div class="ui form">
        <div class="field">
          <label>Username</label>
          <div class="ui left icon input">
            <input type="text" placeholder="Username">
            <i class="user icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Email</label>
          <div class="ui left icon input">
            <input type="Email" placeholder="Email">
            <i class="envelope icon"></i>
          </div>
        </div>
        <div class="field">
          <label>Password</label>
          <div class="ui left icon input">
            <input type="password">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="field">
          <label>ConfirmPassword</label>
          <div class="ui left icon input">
            <input type="password">
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="ui blue submit button">Register</div>
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
}
</script>
<style scoped>
.login {
  margin:  48px auto;
  width:  900px;
  max-width: 80%;
}
</style>
