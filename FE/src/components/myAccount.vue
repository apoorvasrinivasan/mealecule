<template lang='pug'>
div.myaccount
 h1.ui.header Welcome, {{ user.firstName }}
 button.ui.tiny.primary.button(v-on:click='logout()') Logout
 div.ui.card
    h2.ui.header.teal Current Progress
    div.ui.statistics
      div.ui.statistic
        span.value 
          i.ui.huge.label.circular.bCoins $
        span.label {{ coins }} Coins
      div.ui.statistic
        span.value 
          i.ui.huge.label.circular.badges(:class="badges")
            i.icon.ui.trophy 
        span.label {{ badges }} Badge
  
</template>
<script type="text/javascript">
export default {
  name: 'MyAccount',
  data(){
    return {
      user: {},
      coins: 0,
      badges:''
    }
  },
  mounted(){
    let userData = JSON.parse(localStorage.userData);
    if(!userData) return
    this.user = userData;
    this.coins = userData.gameData.coins
    this.badges = userData.gameData.badge.level
  },
  methods:{
    logout:function(){
      
      localStorage.removeItem('user')
      localStorage.removeItem('userData')
      this.$root.cart = -1;
      this.$router.go('/login')
    }
  }
}
</script>
<style scoped>
.card{
  padding:  24px;
}
.ui.label.BRONZE{
  color: #f4f4f4;
  background-color: #CD7F32;
}
</style>