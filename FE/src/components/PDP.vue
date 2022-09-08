<template lang="pug">
div.plp
    div.ui.grid.product(v-if="product.code")
      div.ui.grid.thirteen.wide.column.product
        section.five.wide.column.product-image.ui.image
          img(v-if="product.imageURL" :src="'/api/'+ product.imageURL" :alt="product.name")
        section.ten.wide.column.product-detail
          router-link.ui.label(:to="{name:'plp', params:{id:c.code}}" v-for ='c in product.categories') {{ c.code }}
          h1.header.ui {{ product.name }}
          div.ui.two.column.grid
            div.column
              div.ui.label.teal {{ product.manufacturer }}
              br
              div.ui.statistic
                div.value.price {{ product.price.value }}
              div.ui.tiny.statistic
                div.value.weight {{ product.weightInG}}g
            div.column
              MQ(:nutrients="product.mq")
            p.product-description
              | {{ product.summary }}

        
        div.ui.section.divider
        section#Nutrients
          h3.ui.header Mealecules information
          table.ui.table.definition.mqTable
            thead
              tr
                th
                th per 100g
            tbody
              tr(v-for ='k,v in product.mealeculeQuotientData')
                td {{ v }}
                td {{ k }}
        div.ui.section.divider

      div.three.wide.column
        div.ui.segment.cta-box
            div.ui.label.green.ribbon {{product.stock.stockLevelStatus}} 
            div(style="margin-top:24px")
              span.mrp-label MRP
              span.price {{product.price.value }}
            
            div(v-if="$root.badges")
              span.discounted.price {{product.price.discounted}}
              span.ui.circular.label.tiny.bCoins {{product.price.coins}}
            small You can get upto Rs.{{product.price.coins}} discount. 

            button.ui.button.fluid.primary.addtocart(v-on:click="addCart()") Add to cart
    
</template>

<script>

import Product from '../services/product'
import User from '../services/user'
import MQ from './MQ.vue'

export default {
  name: 'PDP',
    components:{
    MQ
  },
  data(){
    return {
      product : {}
    }
  },
  mounted() {
      let code =  this.$route.params.code;
      Product.getProduct(code,(data) => {
        this.product = data; 
        this.product.mq = Product.makeMQData(data.mealeculeQuotientData);
        this.product.price.coins = Math.floor(data.price.value /10);
        this.product.price.discounted = data.price.value - this.product.price.coins;
          
      },
      (data) => {
        console.log("error");
        this.product = data; 
        console.log(data);
      });
  },
  methods:{
    addCart(){
      let vm = this;
      let code =  this.$route.params.code;
      User.addToCart(code, (data)=>{
        vm.$root.cart ++;
        vm.$root.cartMQ = data.mealeculeQuotientData;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.product-image img {
    max-width: 100%;
}

.product-detail {
  margin:  24px;
}
.mq {
  max-width: 150px;
  margin-left: auto;
}
.product-features {
  margin:  0;
  padding:  0;
  list-style: none;
  display: grid;
  grid-gap:  0 24px;
  grid-template-columns: auto auto;
  width: 100%;
}
.product-features li {
  padding: 10px;
  display: flex;
  gap:  10px;
  justify-content: space-between;
  margin: 0;
  box-shadow: 0 1px 1px  #ccc;
}
.statistic .weight{
  font-size: .7rem;
  text-transform:lowercase;
}
.bCoins {
  display: inline-block;
  margin-left: 10px;
  vertical-align: middle;
}
.cta-box {
  position: fixed;
  top:  84px;
}
.cta-box .checkbox {
  width: 100%;
}
.mqTable{
  text-transform: capitalize;
}
.addtocart{
  margin-top: 15px;
}
.discounted.price{
  color: var(--red);
  font-size:  1.1em;
}
.mrp-label {
  display: inline-block;
  margin-right:  5px;
  font-size:  .8em;
  color: var(--lightgrey);
}

@media screen and (max-width:  800px){
  .ui.segment.cta-box {
    bottom: 0;
    height: fit-content;
    left: 0;
    top: unset;
    width: 100%;
    z-index: 5;
  }
  .ui.grid.product{
    display: grid;
    grid-gap:  24px;
  }
   .ui.grid>[class*="wide"].column{
    min-width: 100%;
    margin: 0 auto;
    text-align: center;
   }
}
</style>
