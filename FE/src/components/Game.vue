<template lang="pug">
div.gamearea
  h1#score {{score }} 
    div.ui.yellow.circular.label {{coins}} 
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
</template>

<script>
import * as $ from 'jquery';
import User from '../services/user.js';

let gameInterval;
let gameInterval2;

export default {
  name: 'GameArea',
  data(){
    return {
      speed: 9000,
      player:300,
      score:0,
      coins:0,
      obsticles:[],
      gamepaused: true,
      startButton: 'Start',
      p_score:0,
      p_coins:0,
    }
  },
  mounted() {
    // this.startGame();
    this.total_coin = localStorage.coins || 0;
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
      User.addCoins(this.coins,(data)=>{
        console.log(data)
      }, 
      ()=>{
        vm.$root.total_coins += vm.coins;
        localStorage.setItem('coins', vm.$root.total_coins );
        vm.p_coins = vm.coins;
        vm.p_score = vm.score;
        vm.score = 0;
        vm.coins =  0;
      });

      clearInterval(gameInterval);
      clearInterval(gameInterval2);
      $('#obst').empty();
      this.gamepaused =true;
      this.startButton = 'Play again'
      $('startbutton').focus();
    },
    createbox:function(idx){
      let vm=this;
      let box = document.createElement('div');
      $(box).addClass("obsticle");
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
      console.log(randomNo)
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
            alert('gameover')
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
#gameArea {
  width:600px;
  height: 400px;
  border:  1px solid #ccc;
  margin:  0 auto;
  position: relative;
  overflow: hidden;
  background: url(../assets/road.png);
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
  position: absolute;
  top: 0;
  right: 0;
  left: 0;
  z-index: 4;
  background: rgba(0,0,0,0.5);
  height: 100%;
  display: grid;
  align-items: center;
  justify-content: center;
}
#player{
  height:  100px;
  width: 100px;
  background: url(https://i.pinimg.com/originals/05/c1/ab/05c1ab65b1b2e336fe3cb321b9d4ea46.png) no-repeat;
  background-size:contain ;
  position:  absolute;
  bottom:  0;
}
.obsticle{
  height:  90px;
  width: 90px;
  background: black;
  border:  5px solid transparent;
  position:  absolute;
  color:  #fff;
  z-index: 2;
   animation: fall 6s linear infinite;
}
.coins{
  height:  50px;
  width: 50px;
  background: #ebdc60;
  border: 1px solid gold;
box-shadow: 1px 2px 0 #ccc;
  border-radius: 50%;
  position:  absolute;
  top:  -50px;
  color:  #222;
  animation: fall 5s linear 2;
}

#score{
  position: absolute;
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
  margin: 0;
  padding: 20px;
  list-style-type: none;
  width: 200px;
  border-radius: 10px;
  display: block;
  color: white;
  background: var(--red);
}
.gameSummary li {
  display: flex;
  justify-content: space-between;
}

</style>