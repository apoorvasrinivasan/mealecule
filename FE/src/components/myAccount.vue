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
      button.ui.primary.fluid.button(v-on:click="savePM()" :disabled="form_disabled") 
        | Save   
        div.ui.circular.label.tiny.bCoins 20

    div.ui.card(v-if="badges")
      div.ui.top.attached.label(:class="badges") {{ badges }}
      h2.ui.header.teal BMI Calculator
      form.ui.form
        div.ui.field
          label Weight (in kg)
          input(v-model="bmi.w")
        div.ui.field
          label Height (in cm)
          input(v-model="bmi.h")
        button.ui.primary.fluid.button(v-on:click="calcBMI()") Calculate BMI
        div.bmi_result.ui.message(v-if="bmi.result") {{ bmi.result }}


  
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
      user_pm:[],
      bmi:{ w:70, h:170}

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
  },
  computed:{
    form_disabled: function(){
      return this.user_pm.slice().sort().join(',') == this.$root.preferredMealecule.slice().sort().join(',')
    }
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
        vm.MQ_list = data.preferredMealecule.map(i=>{
          return i.toLowerCase();
        }).filter(i=>{
         return i != 'calories'
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
    
      User.addCoins(vm.coins + 20, (data)=>{
        vm.coins = data.coins;
        vm.badges = data.badge.level
        vm.$root.total_coins = data.coins
      });
    },
    calcBMI:function(){
      let vm = this.bmi;
      let result = Math.round(((vm.w / Math.pow(vm.h,2)) * 10000)*10)/10;
      let bmi_message = (result > 40) ? 'Severe Obesity' : (result >30) ? 'Obesity' : (result >25)? 'Overweight' :(result > 19) ? 'Normal': "Underweight";
      vm.result = "Your BMI is : " + result + " and you are " + bmi_message;
    }
  }
}
</script>
<style scoped>
.ui.cards{
  margin-top: 24px;
}
.ui.cards > .card {
  padding:  24px;
}
.ui.label.BRONZE{
  color: #f4f4f4;
  background-color: var(--bronze);
}
.preffered_mq {
  display: grid;
    grid-template-columns: 50% 50%;
}
.preffered_mq.ui.form .field>label {
  font-weight: 400;
  text-transform: capitalize;
}
.ui.button .bCoins{
  margin-left: 10px;
}
</style>