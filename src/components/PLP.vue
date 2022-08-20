<template lang="pug">
div.plp
  div.ui.header.teal {{ category}}
  div.ui.grid
    div.ui.three.wide.column Filters
    div.ui.thirteen.wide.column  
      div.ui.link.cards
        div.card(v-for="p in products" :key="p.id")
          div.image
            img(src="https://www.maggi.in/sites/default/files/styles/product_image_desktop_617_900/public/maggi-2minutes-noodles-617x900.png?itok=GgDSaGCE")
          
          div.content
            div.header {{ p.userId }}
            div.meta
              router-link(to="/pdp")  {{ p.completed }}
            div.description  {{ p.title}}
</template>

<script>

import Product from '../services/product'

export default {
  name: 'PLP',
  data(){
    return {
    category: "Maggie",
    products: []
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
.ui.card, .ui.cards>.card {
  width:  212px;
}
.image img{
  max-height: 100%;
}
</style>
