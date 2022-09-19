import Product from '../../services/product';
import Common from '../../services/common';
import User from '../../services/user';
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
      orderHistory:[],
      loaders:{pm:false, info:false}

    }
  },
  mounted(){
    let userData = JSON.parse(localStorage.userData);
    if(!userData) return
    this.user = userData;
    this.coins = userData.gameData.coins
    this.badges = (userData.gameData.badge)? userData.gameData.badge.level : '';
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
      localStorage.removeItem('coins')
      this.$root.cart = -1;
      this.$root.isLogged = false;
      this.$root.total_coins = 0;
      this.$root.cartMQ = {};
      this.$router.push('/login')
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
      vm.loaders.info=true;
      User.updateUser(user, ()=>{
        localStorage.userData = JSON.stringify(user);
        vm.loaders.info=false;
        User.addCoins(vm.coins+20, (data)=>{
          vm.coins = data.coins;
          vm.badges = data.badge.level
          vm.$root.total_coins = data.coins
          Common.Alert('Saved. you receive 20 Coins');
        });
      },()=>{
        vm.loaders.info=false;
      });

    },
    savePM:function(){
      let vm = this;
      let pm = this.user_pm.join(','); 
      vm.loaders.pm=true;
      User.postMealecule(pm, (data)=>{
        vm.$root.preferredMealecule = data.preferredMealecule
        vm.user_pm = data.preferredMealecule
          User.addCoins(vm.coins + 10, (data)=>{
            vm.loaders.pm=false;
            vm.$root.total_coins = data.coins;
            vm.coins = data.coins;
            vm.badges = data.badge.level;
            User.myCart((data)=>{
              vm.$root.cartMQ = data.mealeculeQuotientData;
            });
          });
      })
    
    },
    calcBMI:function(){
      let vm = this.bmi;
      let result = Math.round(((vm.w / Math.pow(vm.h,2)) * 10000)*10)/10;
      let bmi_message = (result > 40) ? 'Severely Obese' : (result >30) ? 'Obese' : (result >25)? 'Overweight' :(result > 19) ? 'Normal': "Underweight";
      vm.result = `Your BMI is : ${result} and you are ${bmi_message}`;
    },
    async getOrders(){
      let vm = this;
      let chartData={x:[],sData:{}, series:[]}
      await User.userOrders(this.user).then((data)=>{
          vm.orderHistory = data.orders; 
          if(data.orders.length ==0 ) return;
          vm.orderHistory.map((i)=>{
            i.placed = new Date(i.placed)
            chartData.x.push(i.placed.toDateString());
            i.energy = i.mealeculeQuotientData.calories;
            delete i.mealeculeQuotientData.calories;
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
      
      if(vm.orderHistory.length)
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