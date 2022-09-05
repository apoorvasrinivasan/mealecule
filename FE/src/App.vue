<template>
  <div class="ui menu" :class="{'secondary':currentRouteName == 'home'}">
    <router-link to="/" class='item'>Mealecule</router-link>
    <router-link to="/game" class='item'>Play Game</router-link>
    
    <div class="right menu" v-if="categories.length">
          <div  class='ui dropdown item'>
      Shop By
      <i class="dropdown icon"></i>
      <div class="menu">
        <div class="ui dropdown item" v-for="c in categories" :key="c.id">
          {{ c.id }}
        <i class="dropdown icon"></i>
        <div class="menu">
          <router-link :to="{name:'plp', params:{id:cs.id}}" class="item" v-for="cs in c.subcategories" :key="cs.id">{{ cs.name }}</router-link>
        </div>
      </div>
    </div>
  </div>
    <div class="item"> Total Coins : {{total_coins }} </div>
      <router-link to="login" class='item' v-if="!isLogged">Login</router-link>
      <router-link :to="{name:'MyAccount'}" class="item" v-if="isLogged">
        <i class="user circle icon"></i>
        My account
      </router-link>
      <router-link to="/myCart" class="item"> <i class="shopping cart icon"></i> </router-link>
    </div>
  </div>
  <section id="homepage" class="home-banner" v-if="currentRouteName == 'home'">
  </section>
   <div class="ui container site-content">
    <router-view></router-view>
  </div>
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
      total_coins: 0,
    }
  },
  created(){
    this.getCategories()
  },
  methods:{
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

.home-banner {
  background:url('assets/hero-image.png') no-repeat #dff7ce;
  background-attachment: fixed;
  background-size: cover;
  width:100%;
  height:100vh;
  margin-top: -54px;
}
</style>
