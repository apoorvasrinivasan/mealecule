<template lang="pug">
div.game
  h1 {{ myScore }}
  button.ui.button(v-on:click='startGame') Start
  input(v-on:keyup.down='movedown', v-on:keyup.up='moveup')
  <br>
  canvas#gameArea
  p  The score will count one point for each frame you manage to "stay alive".
</template>

<script>

function component(width, height, color, x, y, type) {
    this.type = type;
    this.width = width;
    this.height = height;
    this.speedX = 0;
    this.speedY = 0;    
    this.x = x;
    this.y = y;    
    this.update = function() {
        var ctx = myGameArea.context;
        if (this.type == "text") {
            ctx.font = this.width + " " + this.height;
            ctx.fillStyle = color;
            ctx.fillText(this.text, this.x, this.y);
        } else {
            ctx.fillStyle = color;
            ctx.fillRect(this.x, this.y, this.width, this.height);
        }
    }
    this.newPos = function() {
        this.x += this.speedX;
        this.y += this.speedY;        
    }
    this.crashWith = function(otherobj) {
        var myleft = this.x;
        var myright = this.x + (this.width);
        var mytop = this.y;
        var mybottom = this.y + (this.height);
        var otherleft = otherobj.x;
        var otherright = otherobj.x + (otherobj.width);
        var othertop = otherobj.y;
        var otherbottom = otherobj.y + (otherobj.height);
        var crash = true;
        if ((mybottom < othertop) || (mytop > otherbottom) || (myright < otherleft) || (myleft > otherright)) {
            crash = false;
        }
        return crash;
    }
}

var myGameArea;
var myGamePiece;
export default {
  name: 'GameArea',
  data(){
    return {
      
      myObstacles:[],
      myScore: 0
  }
  },
  mounted() {
    this.setGameArea();
  },
  methods : {
    startGame:()=>{

      myGamePiece = new component(30, 30, "red", 10, 120);
      myGameArea.start();
    },
    setGameArea(){
      var vm = this;
       myGameArea = {
        canvas : document.getElementById("gameArea"),
        start : function() {
          this.canvas.width = 480;
          this.canvas.height = 270;
          this.context = this.canvas.getContext("2d");
          this.frameNo = 0;
          this.interval = setInterval(function(){vm.updateGameArea()}, 20);
        },
        clear : function() {
            this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
        },
        stop : function() {
            clearInterval(this.interval);
            
        }
      }
    },
    updateGameArea(){
      var x, height, gap, minHeight, maxHeight, minGap, maxGap;
      var vm=this;
      for (var i = 0; i < vm.myObstacles.length; i += 1) {
        if (myGamePiece.crashWith(vm.myObstacles[i])) {
            myGameArea.stop();
            return;
        } 
      }
      myGameArea.clear();
      myGameArea.frameNo += 1;
      if (myGameArea.frameNo == 1 || vm.everyinterval(150)) {
          x = myGameArea.canvas.width;
          minHeight = 20;
          maxHeight = 200;
          height = Math.floor(Math.random()*(maxHeight-minHeight+1)+minHeight);
          minGap = 50;
          maxGap = 200;
          gap = Math.floor(Math.random()*(maxGap-minGap+1)+minGap);
          vm.myObstacles.push(new component(10, height, "green", x, 0));
          vm.myObstacles.push(new component(10, x - height - gap, "green", x, height + gap));
      }
      for (i = 0; i < vm.myObstacles.length; i += 1) {
          vm.myObstacles[i].speedX = -1;
          vm.myObstacles[i].newPos();
          vm.myObstacles[i].update();
      }
      vm.myScore=myGameArea.frameNo;
      myGamePiece.newPos();    
      myGamePiece.update();
    },
    everyinterval(n){
      if ((myGameArea.frameNo / n) % 1 == 0) {return true;}
      return false;
    },
    moveup(){
      myGamePiece.speedY = -1; 
    },
    movedown() {
      myGamePiece.speedY = 1; 
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
 #gameArea {
  width:  480px;
  height: 270px;
  background: wheat;
 }
</style>
