<template>
  <nav aria-label="Main menu" class="ui menu" :class="{'secondary':currentRouteName == 'home'}">
    <router-link to="/" class=''  >
      <img  src="/logowhitesmall.png"  v-if="currentRouteName == 'home'" class="logo" alt="mealecule logo"/>
      <img  src="/logosmall.png" v-else  class="logo" alt="mealecule logo"/>
    </router-link>
    <router-link to="/game" class='item'><span class="lg">Play </span>Game</router-link>
    <div id="dropdown" tabindex=0 aria-label="Shop By Categories" role="button" class='ui dropdown item' v-if="categories.length">
    <span class="lg">Online  Super</span>market
    <i class="dropdown icon" aria-hidden="true"></i>
    <div class="menu" aria-hidden="true">
      <router-link :to="{name:'CatList'}" class="item" >All Categories</router-link>
      <div class="ui dropdown item" role="link" :aria-label="c.name"  v-for="c in categories" :key="c.id">
        {{ c.name }}
      <i class="dropdown icon"></i>
      <div class=" submenu menu" aria-hidden="true">
        <router-link :to="{name:'plp', params:{id:cs.id}}" role="link" class="item" v-for="cs in c.subcategories" :key="cs.id">{{ cs.name }}</router-link>
      </div>
      </div>
    </div>
    </div>
    
    <div class="right menu" > {{ filteredCartMq.length }}
    <div class=" item promo-bar" v-if="filteredCartMq">
      <span  class="ui label lg promo-bar-item tiny" v-for="k,v, ind in filteredCartMq" :key="ind" :style="'background-color:'+ $root.colors[ind]">
      {{ Math.floor(k)}}g {{v}} 
      </span>
    </div>
    <div class="item" :aria-label="'Coins earned: '+total_coins || 0"> <span class="ui circular bCoins tiny yellow label"> {{ total_coins || 0}} </span> </div>
    
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

<style scoped>

.site-content {
  margin-top: 54px;
}

.home .ui.menu.secondary > .item {
  color: #fff;
}
nav{
  position: relative;
  z-index: 11;
}
.ui.dropdown.item:focus-within, .menu .item:focus-within {
    outline: 1px solid #222;
}

@media screen and (max-width: 600px){
  #homepage.home-banner {
    background-position: 0 0;
    background-size: auto 100%;
    margin-top: -86px;
    width: 100%;
    filter: grayscale(0.3);
  }
   .lg{
    display: none;
  }
  
nav.ui.menu > .right.menu {
    position: fixed;
    bottom: 0;
    display: grid;
    background: white;
    box-shadow: 0 -1px 2px 0 #ccc;
    grid-template-columns: 65% 10% 10% 9%;
    justify-content: space-between;
    width: 100%;
  }
  .ui.menu .item>.bCoins.label{
    margin-left: 0;
  }
  .promo-bar {
    padding:  5px 0 !important;
  }

}
.home-banner {
  background:url('assets/images/leaf.webp') no-repeat #dff7ce;
  background-attachment: fixed;
  background-size: 500px auto;
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
    height: 21px;
    margin-top: 8px;
    margin-left: 8px;
    width:  90px;
    max-width: unset;
}
</style>
