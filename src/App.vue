<template>
  <div class="ui pointing menu">
    <router-link to="/" class='active item'>Mealecule</router-link>
    <router-link to="/plp"  class='item'>Shop By</router-link>
    <router-link to="/game" class='item'>Play Game</router-link>
    
    <div class="right menu">
      <div class="item">
        <div class="ui transparent icon input">
          <input type="text" placeholder="Search...">
          <i class="search link icon"></i>
        </div>
      </div>
    </div>
  </div>
  <div class="ui container">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>

</style>
