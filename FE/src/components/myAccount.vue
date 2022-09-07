<template lang='pug'>
div.myaccount
 h1.ui.header Welcome, {{ user.firstName }}
 button.ui.tiny.primary.button(v-on:click='logout()') Logout
 div.ui.cards
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
   div.ui.card
      h2.ui.header.teal My Preferred Mealecules
      form.ui.form.checkbox.preffered_mq
        div.ui.field(v-for="m in MQ_list")
          div.ui.checkbox
          input(type="checkbox" :id="'mq_'+m" v-model = "user_pm" :value="m" :disabled="user_pm.length>=4 && user_pm.indexOf(m) == -1") 
          label(:for="'mq_'+m" ) {{m}}
        button.ui.primary.fluid.button(v-on:click="savePM()") Save


  
</template>
<script type="text/javascript">
import Product from '../services/product';
import User from '../services/user';
export default {
  name: 'MyAccount',
  data(){
    return {
      user: {},
      coins: 0,
      badges:'',
      MQ_list: [],
      user_pm:[]
    }
  },
  mounted(){
    let userData = JSON.parse(localStorage.userData);
    if(!userData) return
    this.user = userData;
    this.coins = userData.gameData.coins
    this.badges = userData.gameData.badge.level
    this.getMealeculeList()
    this.user_pm=this.$root.preferredMealecule;
    console.log(this.user_pm)
  },
  methods:{
    logout:function(){
      
      localStorage.removeItem('user')
      localStorage.removeItem('userData')
      this.$root.cart = -1;
      this.$router.go('/login')
    },
    getMealeculeList: function(){
      let vm = this;
      Product.getMealeculeList((data)=>{
        console.log(data)
        vm.MQ_list = data.preferredMealecule.map(i=>{
          return i.toLowerCase();
        })
      })
    },
    savePM:function(){
      let vm = this;
      let pm = this.user_pm.join(','); 
      User.postMealecule(pm, (data)=>{
        vm.$root.preferredMealecule = data.preferredMealecule
        vm.user_pm = data.preferredMealecule
      })
    }
  }
}
</script>
<style scoped>
.ui.cards > .card {
  padding:  24px;
}
.ui.label.BRONZE{
  color: #f4f4f4;
  background-color: #CD7F32;
}
.preffered_mq {
  display: grid;
    grid-template-columns: 50% 50%;
}
.preffered_mq.ui.form .field>label {
  font-weight: 400;
  text-transform: capitalize;
}
</style>