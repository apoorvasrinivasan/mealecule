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
              br
              div.ui.tiny.statistic
                div.value.weight {{ product.weightInG}}
                div.labe.weight net weight (in g)
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
            small You can get upto Rs.{{product.price.maxdiscount}} discount. 

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
        this.product.price.coins = Math.floor(data.price.value / this.$root.disc);
        this.product.price.discounted = data.price.value - this.product.price.coins;
        this.product.price.maxdiscount = Math.floor(data.price.value * 0.20);
          
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

<style scoped src="@/assets/css/pdp.css"></style>