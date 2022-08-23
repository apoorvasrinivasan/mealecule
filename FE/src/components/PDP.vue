<template lang="pug">
div.plp
    div.ui.grid.product(v-if="product.code")
      div.ui.grid.thirteen.wide.column.product
        section.five.wide.column.product-image.ui.image
          img(src="https://www.maggi.in/sites/default/files/styles/product_image_desktop_617_900/public/maggi-2minutes-noodles-617x900.png?itok=GgDSaGCE")
        section.ten.wide.column.product-detail
          div.ui.label(v-for ='c in product.categories') {{ c.code }}
          h1.header.ui {{ product.name }}
          div.ui.two.column.grid
            div.column
              div.ui.label.teal {{ product.manufacturer }}
              br
              div.ui.statistic
                div.value.price {{ product.price.value }}
            div.column
              MQ(:nutrients="product.mq")
            p.product-description
              | {{ product.summary }}

        
        div.ui.section.divider
        section#Nutrients
          h3.ui.header Mealecules information
          table.ui.table.definition
            thead
              tr
                th
                th per 100g
            tbody
              tr(v-for ='k,v in product.mealeculeQuotientData')
                td {{ v }}
                td {{ k }}
        div.ui.section.divider
        section#Ingredients
          h3.ui.header Ingredients
          p {{ product.description }}  

      div.three.wide.column
        div.ui.segment.cta-box
          form.ui.form
            div.ui.grouped.fields
              div.ui.radio.checkbox
                input#fulPrice(type="radio" name="price")
                label(for="fulPrice") 
                  span.price {{product.price.value }}
            
              div.ui.radio.checkbox
                input#discountPrice(type="radio" name="price")
                label(for="discountPrice") 
                  span.price {{product.price.discounted}}
                  span.ui.yellow.circular.label.tiny.plutoCoin {{product.price.coins}}
            button.ui.button.fluid.primary Add to cart
    
</template>

<script>

import Product from '../services/product'
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
        this.product.price.coins = Math.floor(data.value /10);
        this.product.price.discounted = data.value - this.product.price.coins;
          
      },
      (data) => {
        console.log("error");
        this.product = data; 
        console.log(data);
      });

    
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
.plutoCoin {
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
.price:before {
  content:"Rs.";
  font-size: .4em;
}
</style>
