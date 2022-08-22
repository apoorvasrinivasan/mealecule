<template lang="pug">
div.plp
    div.ui.grid.product
      div.ui.grid.thirteen.wide.column.product
        section.five.wide.column.product-image.ui.image
          img(src="https://www.maggi.in/sites/default/files/styles/product_image_desktop_617_900/public/maggi-2minutes-noodles-617x900.png?itok=GgDSaGCE")
        section.ten.wide.column.product-detail
          div.ui.label Noodles
          h1.header.ui MAGGI® 2-Minute Noodles
          div.ui.two.column.grid
            div.column
              div.ui.label.teal Maggie
              br
              div.ui.statistic
                div.value ₹150
            div.column
              MQ(:nutrients="mq")
            p.product-description
              | {{ description }}
            ul.product-features
              li(v-for="item in features")
                b {{ item.key }}
                span {{ item.value }}
        
        div.ui.section.divider
        section#Nutrients
          h3.ui.header Mealecules information
          table.ui.table.definition
            thead
              tr
                th
                th per 100g
                th per serve
                th %GDA per serve

            tbody
              tr
                td Energy (kcal)
                td 427
                td 299
                td 15%
              tr
                td Protein (g)
                td 427
                td 299
                td 15%
              tr
                td Carbohydrate (g)
                td 427
                td 299
                td 15%
              tr
                td -Total sugars(g)
                td 427
                td 299
                td 15%
              tr
                td Sugar (Sucrose)(g)
                td 427
                td 299
                td 15%
        div.ui.section.divider
        section#Ingredients
          h3.ui.header Ingredients
          p {{ description }}  

      div.three.wide.column
        div.ui.segment.cta-box
          form.ui.form
            div.ui.grouped.fields
              div.ui.radio.checkbox
                input#fulPrice(type="radio" name="price")
                label(for="fulPrice") Rs. 150
            
              div.ui.radio.checkbox
                input#discountPrice(type="radio" name="price")
                label(for="fulPrice") 
                  span Rs. 130
                  span.ui.yellow.circular.label.tiny.plutoCoin 20
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
      mq: {chart:[
        {key:'fat', value:30},
        {key:'fibre', value:20},
        {key:'protien', value:50},
      ]
      ,cal: 250},
      features: [
        {key:'fat', value:30},
        {key:'fat', value:30},
        {key:'fat', value:30},
        {key:'fat', value:30},
        {key:'fibre', value:20},
        {key:'protien', value:50}
      ],
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Rhoncus urna neque viverra justo nec ultrices dui sapien. Aliquet lectus proin nibh nisl condimentum id. Tristique senectus et netus et malesuada fames ac turpis. Neque ornare aenean euismod elementum nisi quis. Elit sed vulputate mi sit amet mauris."
    }
  },
  mounted() {
      Product.getProducts((data) => {
        this.products = data
      },
      (data) => {
        console.log("error");
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
  max-width: 100px;
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
</style>
