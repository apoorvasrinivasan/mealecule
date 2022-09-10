<template lang="pug">
div.ui.error.message.mobile-error
 | please play game from desktop
div.gamearea
  div#score
    div.ui.label.huge Score: {{ score }} 
    div.ui.huge.circular.label.bCoins {{coins}}
    h3.ui.heading.red Avoid
    img.rule-images(v-for="img in mq_images" :src="require('../assets/' + img+'.png')")
    
  div#gameArea(:class="{'active':!gamepaused}")
    div#obst
    div.overlay( v-show="gamepaused")
      ul.gameSummary 
        li
          b Score
          span {{ p_score }}
        li
          b Coins
          span {{ p_coins }}
        li
          b Total Coins
          span {{ $root.total_coins }}
      button.startbutton.ui.primary.large.button(autofocus v-on:click="startGame()") {{ startButton}}
    div#player(:style="'left:'+player + 'px'")
section#rules.ui.segment
  h2.ui.header How to play
  ul
    li Play our simple single-player game to collect coins as Popeye runs endlessly dodging a list of food obstacles and collecting coins.
    li Help Popeye collect as many coins in the pursuit of avoiding tempting food obstacles.
    li Higher the number of coins won yields in achievement badges unlocked.
    li Winning 100 BCoins would lead to winning a Bronze badge and further coins collection will unlock Silver, Gold and Platinum badges.
    li Win exciting discounts on MRP by collecting max coins and unlocking new badges along with the exhilarating benefits helping you and your family maintain your ideal health and fitness quotient.
</template>

<script>
import * as $ from 'jquery';
import User from '../services/user.js';
import Common from '../services/common.js';

let gameInterval;
let gameInterval2;

export default {
  name: 'GameArea',
  data(){
    return {
      coins:0,
      gamepaused: true,
      obsticles:[],
      p_coins:0,
      p_score:0,
      player:300,
      score:0,
      speed: 9000,
      startButton: 'Start',
      mq_images:[
      'sugar','carbohydrate', 'fat']
    }
  },
  mounted() {
    // this.startGame();
    // this.total_coin = localStorage.coins || 0;
  },
  methods: {

    startGame:function(){
      let vm = this;
      vm.gamepaused =false;

      vm.createbox(0);
      vm.createbox(1);
      vm.createbox(2);
      gameInterval = setInterval(()=>{ vm.score +=10; vm.checkCollision()},100);
      gameInterval2 = setInterval(()=>{ vm.createCoins()},2000);
      document.addEventListener('keydown',function(e){
        if(e.keyCode =='37' && vm.player >0) // left arrow
          vm.player -=100;
        if(e.keyCode =='39' && vm.player <500) // right arrow
          vm.player +=100;
      });
    },
    stopGame: function(){
      let vm =this;
      let u = localStorage.userData;
      let coins = vm.$root.total_coins || 0;
      if(u) {
        coins += parseInt(vm.coins)
        User.addCoins(coins,()=>{
          vm.$root.total_coins = coins;
          vm.p_coins = vm.coins;
          vm.p_score = vm.score;
          vm.score = 0;
          vm.coins =  0;
        }, 
        ()=>{
          Common.Alert('error occured in saving');
          vm.score = 0;
          vm.coins =  0;
        });
      }
      else {
        vm.$root.total_coins = vm.coins;
        vm.p_coins = vm.coins;
        vm.p_score = vm.score;
        vm.score = 0;
        vm.coins =  0;
      }
      clearInterval(gameInterval);
      clearInterval(gameInterval2);
      $('#obst').empty();
      this.gamepaused =true;
      this.startButton = 'Play again'
    },
    createbox:function(idx){
      let vm=this;
      let box = document.createElement('div');
      $(box).addClass("obsticle");
      let randomI = Math.floor(Math.random() * vm.mq_images.length);
      $(box).css({
        'background-image':'url('+require("../assets/"+vm.mq_images[randomI]+".png")+')'
      })
      $('#obst').append(box);
      $(box).on('animationiteration',function(){
        let randomNo = Math.floor(Math.random() * 6);
        vm.obsticles[idx] = randomNo;
        $(this).css('left', randomNo*100 + 'px');
      })
      
    },
    createCoins:function(){
      let coin = document.createElement('div');
      $(coin).addClass("coins");
      $('#obst').append(coin);
      let randomNo = Math.floor(Math.random() * 6);
      $(coin).css('left', randomNo*100 + 'px');

      
    },
    checkCollision:function(){
      let vm=this;
      let left = $('#player').offset().left; 
      let top = $('#player').offset().top;
      $('.obsticle, .coins').each(function(){
        let p = $(this).offset();
        if(p.top + 100 >=top && p.left/100 == left/100 ){
          if( $(this).hasClass('obsticle')){
            vm.stopGame();
            Common.Alert('Gameover')
            return
          }
          if( $(this).hasClass('coins')){
            vm.coins ++;
            $(this).remove();
          }
        } 
      }) 
    }
  }
}
</script>
<style>
.gamearea{
  display: flex;
}
#gameArea {
  background: url(../assets/road.png);
  border:  1px solid #ccc;
  height: 400px;
  margin:  0 auto;
  overflow: hidden;
  position: relative;
  width:600px;
}
#gameArea.active {
  animation: move 6s linear infinite;
  border:  solid #fff;
  border-width: 0 3px;
  outline: 4px solid #565852;
}
#obst{
  background: red;
}
.overlay {
  align-items: center;
  background: rgba(0,0,0,0.5);
  display: grid;
  height: 100%;
  justify-content: center;
  left: 0;
  position: absolute;
  right: 0;
  top: 0;
  z-index: 4;
}
#player{
  background: url(../assets/popeye.png) no-repeat;
  background-size:contain ;
  bottom:  0;
  height:  100px;
  position:  absolute;
  width: 100px;
}
.obsticle{
  animation: fall 6s linear infinite;
  background: black;
  background-size:  contain;
  background-repeat: no-repeat;
  background-position: center center;
  border:  5px solid transparent;
  color:  #fff;
  height:  90px;
  position:  absolute;
  width: 90px;
  z-index: 2;
}
.coins{
  animation: fall 5s linear 2;
  background: #ebdc60;
  border-radius: 50%;
  border: 3px solid gold;
  box-shadow: 2px 2px 2px #222, 2px 2px 0 #222 inset;
  color:  #222;
  height:  50px;
  position:  absolute;
  top:  -50px;
  width: 50px;
}

@keyframes fall {
  from {top: -50px;}
  to {top: 400px;}
}
@keyframes move {
  from {background-position: 0 0}
  from {background-position: 0 -100px}
}
.gameSummary {
  background: var(--red);
  border-radius: 10px;
  color: white;
  display: block;
  list-style-type: none;
  margin: 0;
  padding: 20px;
  width: 200px;
}
.gameSummary li {
  display: flex;
  justify-content: space-between;
}
#rules{
  margin-top: 24px;
}
.rule-images {
    max-width: 78px;
    margin-right: 24px;
}
@media screen and (max-width: 600px){
  #gameArea {display:none;}
  #score{
    position: static;
  }
}
@media screen and (min-width: 600px){
  .mobile-error {
    display:none
  }
}
</style>