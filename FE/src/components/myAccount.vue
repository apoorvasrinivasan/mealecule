<template lang='pug'>
div.myaccount
 h1.ui.header Welcome, {{ user.firstName }}
 button.ui.tiny.primary.button(v-on:click='logout()') Logout
 div.ui.four.cards
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

    div.ui.card.userinfo
      h2.ui.header.teal Update Information
      form.ui.form
        div.ui.field
          label Weight (in kg)
          input(v-model="user.weight")
        div.ui.field
          label Height (in cm)
          input(v-model="user.height")
        div.ui.field
          label Age
          input(v-model="user.age")
        button.ui.primary.fluid.button(:disabled="inform_disabled" v-on:click="saveVitals()") 
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
 section#orderHistory.orders
    h2.ui.header.teal Order History
    table.ui.table
      thead
        tr
          th Mealecule Quotient
          th Price
          th Status
          th Date
      tbody
        tr(v-for="o in orderHistory")
          td {{o.mealeculeQuotientData}}
          td 
            span.price {{o.total.value}}
          td {{o.status}}
          td {{o.placed}}
 section#orders
  
</template>
<script type="text/javascript">
import Product from '../services/product';
import Common from '../services/common';
import User from '../services/user';
import Highcharts from 'highcharts';

export default {
  name: 'MyAccount',
  data(){
    return {
      user: {},
      coins: 0,
      badges:'',
      MQ_list: [],
      user_pm:[],
      bmi:{ w:70, h:170},
      orderHistory:[]

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
    if(this.user.weight) this.bmi.w = this.user.weight;
    if(this.user.height) this.bmi.h = this.user.height;
    this.getOrders()
  },
  computed:{
    form_disabled: function(){
      return this.user_pm.slice().sort().join(',') == this.$root.preferredMealecule.slice().sort().join(',')
    },
    inform_disabled: function(){
      let u = JSON.parse(localStorage.userData);
      let og_user_vitals = [
        u.weight,
        u.height,
        u.age,
      ]
      let user_vitals = [
        this.user.weight,
        this.user.height,
        this.user.age,
      ]
      return user_vitals.slice().sort().join(',') == og_user_vitals.slice().sort().join(',')
    },
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
    saveVitals : function () {
      let vm = this;
      let user = this.user;
      User.updateUser(user, ()=>{
        User.updateUser(vm.coins+20, (data)=>{
          vm.coins = data.coins;
          vm.badges = data.badge.level
          vm.$root.total_coins = data.coins
          Common.Alert('Saved. you receive 20 Coins');
        });
      });

    },
    savePM:function(){
      let vm = this;
      let pm = this.user_pm.join(','); 
      User.postMealecule(pm, (data)=>{
        vm.$root.preferredMealecule = data.preferredMealecule
        vm.user_pm = data.preferredMealecule
        User.myCart((data)=>{
          vm.$root.cartMQ = data.mealeculeQuotientData;
        });
        User.addCoins(vm.coins + 20, (data)=>{
          vm.coins = data.coins;
          vm.badges = data.badge.level;
          vm.$root.total_coins = data.coins;
        });
      })
    
    },
    calcBMI:function(){
      let vm = this.bmi;
      let result = Math.round(((vm.w / Math.pow(vm.h,2)) * 10000)*10)/10;
      let bmi_message = (result > 40) ? 'Severe Obesity' : (result >30) ? 'Obesity' : (result >25)? 'Overweight' :(result > 19) ? 'Normal': "Underweight";
      vm.result = "Your BMI is : " + result + " and you are " + bmi_message;
    },
    async getOrders(){
      let vm = this;
      let chartData={x:[],sData:{}, series:[]}
      await User.userOrders(this.user).then((data)=>{
          vm.orderHistory = data.orders; 
          vm.orderHistory.map((i)=>{
            chartData.x.push(i.placed);
            for( let k in i.mealeculeQuotientData){
              if (k in chartData.sData)
                chartData.sData[k].push(i.mealeculeQuotientData[k])
              else
                chartData.sData[k] =[i.mealeculeQuotientData[k]]
            }
          })
          for (let k in chartData.sData){
            let data = chartData.sData[k]
            chartData.series.push({
              name : k,
              data:data
            })
          }
      });
      console.log(chartData)
      Highcharts.chart('orders', {
          chart: {
            type: 'column'
        },
        title: {
            text: 'My MQ history'
        },
        xAxis:{
          categories: chartData.x
        },
        series: chartData.series
      });
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
#orderHistory, #orders {
  margin-top: 24px;
}
</style>