<template lang='pug'>
div.myaccount
 h1.ui.header Welcome, {{ user }}
 button.ui.tiny.primary.button(v-on:click='logout()') Logout
 div.ui.card
    h2.ui.header.teal Current Progress
    div.ui.statistics
      div.ui.statistic
        span.value 
          i.ui.huge.label.circular.yellow {{ $root.total_coins }}
        span.label Coins
      div.ui.statistic
        span.value 
          i.ui.huge.label.circular.yellow {{ $root.total_coins }}
        span.label Badges
  
</template>
<script type="text/javascript">
export default {
  name: 'MyAccount',
  data(){
    return {
      user: localStorage.user,
      coins: localStorage.coins
    }
  },
  methods:{
    logout:function(){
      localStorage.removeItem('coins')
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
</style>