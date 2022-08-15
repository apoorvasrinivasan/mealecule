<template lang="pug">
div.plp
  div.ui.header.teal {{ category}}
  div.ui.grid
    div.ui.three.wide.column Filters
    div.ui.thirteen.wide.column  
      div.ui.link.cards
        div.card(v-for="p in products" :key="p.id")
          div.image
            img(src="/images/avatar2/large/matthew.png")
          
          div.content
            div.header {{ p.userId }}
            div.meta
              a {{ p.completed }}
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
        console.log(data)
        this.products = data
      },
      (data) => {
        console.log("error");
        console.log(data)
      });

    
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
