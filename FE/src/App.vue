<template>
  <div class="ui menu" :class="{'secondary':currentRouteName == 'home'}">
    <router-link to="/" class='item'>Mealecule</router-link>
    <router-link to="game" class='item'>Play Game</router-link>
    
    <div class="right menu" v-if="categories.length">
          <div  class='ui dropdown item'>
      Shop By
      <i class="dropdown icon"></i>
      <div class="menu">
      <router-link :to="{name:'plp', params:{id:c.id}}" class="item" v-for="c in categories" :key="c.id">{{ c.id }}</router-link>
    </div>
  </div>
      <div class="item">
        <div class="ui transparent icon input">
          <input type="text" placeholder="Search...">
          <i class="search link icon"></i>
        </div>
      </div>
      <router-link to="login" class='item'>Login</router-link>
      <div class="item">
        <i class="user circle icon"></i>
        My account
      </div>
      <router-link to="myCart" class="item"> <i class="shopping cart icon"></i> </router-link>
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
    }
  },
  data(){
    return {
      categories:[]
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
