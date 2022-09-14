<template lang="pug">
div.plp
  h1.ui.header {{ category }}
  div.ui.grid(v-if="products.length")
    div.ui.three.wide.column
      div.ui.segment.filter-form.form
        span.ui.header.teal Filters
        div
          div.ui.header.tiny Brand
          div.check-box
            span(v-for="b,index in brands" :key="index")
              input(v-model="filterKeys" :name="b" :value= "b" :id="'brand_'+index" type="checkbox")
              label(:for="'brand_'+index") {{b}}
        
        div(style="margin-top:24px")
          div.ui.header.tiny 
              | Mealecule
          span.mqRange(v-for="p in facetDatas")
            label(:for="p.code") {{ p.name }}:
              small {{ p.val }}g
            input.slider(:id="p.code" type="range"  :min="p.minValue", :max="Math.ceil(p.maxValue)" :name="p.code" v-model="p.val" :style="p.style")

        

    div.ui.thirteen.wide.column  
      div.select-box
        label.sort-box(for="sortby" aria-label ="Sort by")
          select#sortby(v-model="sortKey")
            option(v-for="s in mealecules" :value=s) {{s}}
        p.check-box
          input#asc(type="checkbox" v-model="sortAs")
          label.checkbox(for="asc" :aria-label ="(sortAs)?'Low to High' : 'High to Low'" v-html="(sortAs)?'Low to High' : 'High to Low'")

      div.ui.link.cards
         router-link.card.product-card(:to="{ name: 'pdp', params: { code: p.code }}" v-for="p in filteredList" :key="p.code")
          div.image
            img( v-lazy="'/api/'+p.imageURL" :alt="p.name")
          
          div.content
            span.brandname {{p.manufacturer}}
            div.header.product-name {{ p.name }}
            div.meta
              MQ(:nutrients="p.mq" v-if='p.mq')
              div.ui.label.tiny {{ category}}
              div.price-row
                span(:class="($root.badges)?'mrp':'price'") {{ p.price.value }}
                span.price(v-if="$root.badges") {{ p.price.discounted }}
                span.ui.circular.tiny.label.bCoins(v-if="$root.badges") {{ p.price.coins }}
                div(style="height:50px" v-else)
            button.cta-button.ui.fluid.primary.button(v-on:click="addCart($event, p.code)")  Add to cart
  div.ui.message(v-if="products.length==0") Sorry no products found 
      
</template>

<script src="@/assets/js/plp.js">

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="@/assets/css/plp.css">

</style>
