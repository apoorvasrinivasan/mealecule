<template>
  <nav aria-label="Main menu" class="ui menu" :class="{'secondary':currentRouteName == 'home'}">
    <router-link to="/" class=''>
      <img src="/logowhitesmall.png" class="logo" alt="mealecule logo" v-if="currentRouteName == 'home'"/>
      <img v-else src="/logosmall.png" class="logo" alt="mealecule logo"/>
    </router-link>
    <router-link to="/game" class='item'>Play Game</router-link>
    <div id="dropdown" tabindex=0 aria-label="Shop By Categories" role="button" class='ui dropdown item' v-if="categories.length">
    Shop By
    <i class="dropdown icon" aria-hidden></i>
    <div class="menu" aria-hidden>
      <router-link :to="{name:'CatList'}" class="item" >All Categories</router-link>
      <div class="ui dropdown item" :aria-label="c.name" v-for="c in categories" :key="c.id">
        {{ c.name }}
      <i class="dropdown icon"></i>
      <div class=" submenu menu">
        <router-link :to="{name:'plp', params:{id:cs.id}}"  class="item" v-for="cs in c.subcategories" :key="cs.id">{{ cs.name }}</router-link>
      </div>
      </div>
    </div>
    </div>
    
    <div class="right menu" > 
    <div class=" item promo-bar" v-if="filteredCartMq">
      <span  class="ui label promo-bar-item tiny" v-for="k,v, ind in filteredCartMq" :key="k" :style="'background-color:'+ $root.colors[ind]">
      {{ Math.floor(k)}}g {{v}} 
      </span>
    </div>
    <div class="item"> <span class="ui circular bCoins tiny yellow label"> {{ total_coins || 0}} </span> </div>
    
      <router-link to="/login" class='item' aria-label = "Login" v-if="!isLogged">Login</router-link>
      <router-link :to="{name:'MyAccount'}" aria-label = "My Account" class="item" v-if="isLogged">
        <i class="user circle icon"></i> 
      </router-link>
      <router-link v-if="cart>=0" aria-label="click here to view my cart" to="/myCart" class="item"> <i class="shopping cart icon"></i> 
        <span class="cart-number" v-if="cart>0"></span></router-link>
    </div>
  </nav>
  <section id="homepage" class="home-banner" v-if="currentRouteName == 'home'">
  </section>
   <section class="ui container site-content">
    <router-view></router-view>
  </section>
</template>

<script src="@/assets/js/app.js"></script>

<style>
.site-content {
  margin-top: 54px;
}

nav{
  position: relative;
  z-index: 1;
}
.ui.dropdown.item:focus-within, .menu .item:focus-within {
    outline: 1px solid #222;
}

@media screen and (max-width: 600px){
  .menu {
    flex-wrap: wrap;
  }
  #homepage.home-banner {
    background-position: 0 0;
    background-size: auto 100%;
    margin-top: -86px;
    width: 100%;
    filter: grayscale(0.3);
  }
}
.home-banner {
  background:url('assets/leaf.webp') no-repeat #dff7ce;
  background-attachment: fixed;
  background-size: contain;
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
img.logo {
    height: 25px;
    margin-top: 8px;
    margin-left: 8px;
}
</style>
