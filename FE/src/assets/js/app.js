import Product from '../../services/product';
import * as $ from 'jquery';
import  D from './dropdown';

// console.log(D.a)
export default {
  name: 'App',
  computed: {
    currentRouteName() {
      // to check the current page 
      // display different menu for home page
      return this.$route.name;
    },
    filteredCartMq : function(){
        // filter the carMQ acc to user preference and energy
        let pm = this.preferredMealecule;
        let mq = {}
        if(this.cartMQ) {
          for (let k in this.cartMQ){
           if (pm.indexOf(k) > -1) mq[k] = this.cartMQ[k]
          }
        }
        else
          pm.map((i)=>{
            mq[i] = 0;
          })
       return mq;
    },
    disc() {
      // what % discount will the user get
      switch (this.badges){
        case 'BRONZE' : return 10;
        case 'SILVER' : return 15;
        case 'GOLD' : return 20;
        default: return 0;
      }
    }
  },
  data(){
    return {
      categories:[],
      cart:1,
      total_coins: 0,
      badges:'',
      isLogged:false,
      cartMQ:{},
      preferredMealecule:[],
      defaultPreferredMealecule:[
        'carbohydrate',
        'protein',
        'fiber',
        'fat'
      ],
      colors:[
      '#a7cdab',
      '#42bd7c',
      '#2b6228',
      '#307bcf'

      ]
    }
  },
  created(){
    this.checkLogged();
    this.getCategories()
    this.setMealecule()
  },
  methods:{
    checkLogged: function(){
        // check if user is logged in or not
        let user = localStorage.getItem('user');
        this.isLogged =  user != null; 
    },
    setMealecule:function(){
      // read from localstorgage and set page values.
      let user = {}
      this.preferredMealecule = [...this.defaultPreferredMealecule];
      this.cart = -1;
      try{
        user = JSON.parse(localStorage.userData);
      }
      catch(e) {return;}
      if(!user) return;

      this.badges = (user.gameData.badge)?user.gameData.badge.level : ''
      this.cart = user.cart;
      this.cartMQ = user.cartMQ;
      this.total_coins = user.gameData.coins;
      let pm = user.preferredMealecule;
      if(pm.length > 1) this.preferredMealecule = pm;
      localStorage.userData = JSON.stringify(user);   

    },
    getCategories:function(){
      // category list for dropdown menu
      let vm =this;
      Product.getCategories((data) => {
        vm.categories = data.categories;
        setTimeout(function(){
          D.a($('#dropdown'));
        },300)
      },
      (data) => {
        console.log("error");
        this.product = data; 
        console.log(data);
      });
    }
  }
}