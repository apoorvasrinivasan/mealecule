
import * as $ from 'jquery';
import User from '../../services/user.js';
import Common from '../../services/common.js';

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
        'background-image':'url('+require("../"+vm.mq_images[randomI]+".png")+')'
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