<template>
  <nav aria-label="Main menu" class="ui menu" :class="{'secondary':currentRouteName == 'home'}">
    <router-link to="/" class='item'>Mealecule</router-link>
    <router-link to="/game" class='item'>Play Game</router-link>
    <div  tabindex=0 aria-label="Shop By Categories" role="button" class='ui dropdown item' v-if="categories.length">
    Shop By
    <i class="dropdown icon" aria-hidden></i>
    <div class="menu" aria-hidden>
      <router-link :to="{name:'CatList'}" class="item" >All Categories</router-link>
      <div class="ui dropdown item" :aria-label="c.name" v-for="c in categories" :key="c.id">
        {{ c.name }}
      <i class="dropdown icon"></i>
      <div class="menu">
        <router-link :to="{name:'plp', params:{id:cs.id}}" class="item" v-for="cs in c.subcategories" :key="cs.id">{{ cs.name }}</router-link>
      </div>
      </div>
    </div>
    </div>
    
    <div class="right menu" >
    <div class=" item promo-bar">
      <span  class="ui label promo-bar-item" v-for="k,v, ind in cartMQ" :key="k" :style="'background-color:'+ $root.colors[ind]">
      {{ Math.floor(k)}}g {{v}} 
      </span>
    </div>
    <div class="item"> Total Coins : {{total_coins }} </div>
      <router-link to="/login" class='item' v-if="!isLogged">Login</router-link>
      <router-link :to="{name:'MyAccount'}" class="item" v-if="isLogged">
        <i class="user circle icon"></i>
      </router-link>
      <router-link v-if="cart>=0" to="/myCart" class="item"> <i class="shopping cart icon"></i> 
        <span class="cart-number" v-if="cart>0"></span></router-link>
    </div>
  </nav>
  <section id="homepage" class="home-banner" v-if="currentRouteName == 'home'">
  </section>
   <section class="ui container site-content">
    <router-view></router-view>
  </section>
</template>

<script>
import Product from './services/product';
export default {
  name: 'App',
  computed: {
    currentRouteName() {
        return this.$route.name;
    },
    isLogged:function(){
      let user = localStorage.getItem('user');
      return user != null; 
    }
  },
  data(){
    return {
      categories:[],
      cart:1,
      total_coins: 0,
      badges:'',
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
    this.getCategories()
    this.setMealecule()
  },
  methods:{
    setMealecule:function(){
      let user = {}
      this.preferredMealecule = [...this.defaultPreferredMealecule];
      this.cart = -1;
      try{
        user = JSON.parse(localStorage.userData);
      }
      catch(e) {return;}
      if(!user) return;
      this.badges = user.gameData.badge.level
      this.cart = user.cart;
      this.cartMQ = user.cartMQ;
      console.log(this.cartMQ);
      this.total_coins = user.gameData.coins;
      let pm = user.preferredMealecule;
      if(pm.length > 1) this.preferredMealecule = pm;
      

    },
    getCategories:function(){
      let vm =this;
      Product.getCategories((data) => {
        vm.categories = data.categories
        // eslint-disable-next-line
        setDropdown();
          
      },
      (data) => {
        console.log("error");
        this.product = data; 
        console.log(data);
      });
    }
  }
}
</script>

<style>
.site-content {
  margin-top: 54px;
}

@media screen and (max-width: 600px){
  .menu {
    flex-wrap: wrap;
  }
  #homepage.home-banner {
    background-position: 0 69px;
    background-size: 100% auto;
    margin-top: -86px;
    width: 90vh;
  }
}
.home-banner {
  background:url('assets/hero-image.png') no-repeat #dff7ce;
  background-attachment: fixed;
  background-size: cover;
  width:100%;
  height:100vh;
  margin-top: -54px;
}
.cart-number{
  background-color: var(--red);
  border-radius: 50%;
  display: block;
  line-height: 0;
  padding:  2px;
  width:  7px;
  height:  7px;
  position: absolute;
  bottom:  20px;
}
.slide-fade-enter-active {
  transition: all .1s ease;
}
.slide-fade-leave-active {
  transition: all .1s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for <2.1.8 */ {
  transform: translateY(10px);
  opacity: 0;
}
</style>
