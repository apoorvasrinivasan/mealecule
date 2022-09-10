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

<script  src="@/assets/js/game.js">
export default {
  name:'GameArea'
}
</script>
<style src="@/assets/css/game.css"></style>