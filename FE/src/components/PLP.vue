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
        
        div
          div.ui.header.tiny 
              | Mealecule
          span.mqRange(v-for="p in facetDatas")
            label(:for="p.code") {{ p.name }}:
              small {{ p.val }}g
            input.slider(:id="p.code" type="range"  min=0, :max="p.maxValue" :name="p.code" v-model="p.val")

        

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
          div.image(v-if='p.img')
            img(:src="p.img" :alt="p.name")
          
          div.content
            span.brandname {{p.manufacturer}}
            div.header {{ p.name }}
            div.meta
              MQ(:nutrients="p.mq" v-if='p.mq')
              div.ui.label.tiny {{ category}}
              div.price-row
                span.mrp {{ p.price.value }}
                span.price {{ p.price.discounted }}
                span.ui.circular.yellow.tiny.label {{ p.price.coins }}
            button.cta-button.ui.fluid.primary.button(v-on:click="addCart($event, p.code)")  Add to cart
  div.ui.message(v-if="products.length==0") Sorry no products found 
      
</template>

<script>

import Product from '../services/product'
import User from '../services/user'
import MQ from './MQ.vue'

export default {
  name: 'PLP',
  components:{
    MQ
  },
  data(){
    return {
    category: "",
    filterKeys : [],
    mqKeys : [],
    sortKey:'energy',
    products: [],
    mealecules:[],
    brands:[],
    sortAs:true,
    facetDatas:[]
  }
  },
  mounted(){
   this.getProducts();

  },
   watch: {
    '$route' () {
      this.getProducts();
    }
  },
  computed: {
    filteredList() {
      let vm=this;
      let a = [];
      let mqRange = {};
      vm.facetDatas.map(i=>{
        if(i.code == 'CALORIES')
          mqRange['energy'] = i.val
        else
          mqRange[i.code.toLowerCase()] = i.val
      })
      if(vm.filterKeys.length ==0)
        a= vm.products;
      else
        a = vm.products.filter(i => {
          return vm.filterKeys.indexOf(i.manufacturer.toString()) >=0;
        })
      a = a.filter(i=>{
          let fr = true;
          for(let k in mqRange){
            let v = mqRange[k];
            fr = fr && i.mealeculeQuotientData[k] <= v; 
          }
          return fr;
          
        })
      let asc = (vm.sortAs) ?1:-1;
      return a.sort((a,b)=> {
        return asc*((parseFloat(a["mealeculeQuotientData"][vm.sortKey] )< parseFloat(b["mealeculeQuotientData"][vm.sortKey]))?-1:1);
      });
    }
  },
  methods : {
    getProducts (){
      let vm = this;
      let category = this.$route['params'].id;
      Product.getProducts(category,(data) => {
        vm.category = data.name;
        vm.facetDatas = data.facetDatas.filter((i)=>{
          i.val = i.maxValue;
          return i.maxValue >=1;
        })
        vm.products = data.products.map((i)=>{
          if(vm.brands.indexOf(i.manufacturer) < 0)
            vm.brands.push(i.manufacturer)
          i.mq = Product.makeMQData(i.mealeculeQuotientData);
          i.price.coins = Math.floor(i.price.value /10);
          i.price.discounted = i.price.value - i.price.coins;
          return i
        });
        
          vm.mealecules = [...vm.$root.preferredMealecule, 'energy'];
      },
      (data) => {
        console.log("error");
        console.log(data);
      });
    },
    addCart(e, code){
      let vm = this;
      e.preventDefault();
      User.addToCart(code, ()=>{
        vm.$root.cart ++;
        alert('added to cart successfullu')
      },()=>{
        alert('error in adding to cart')

      })
    }
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
.check-box input[type="checkbox"] {
  position: absolute;
  opacity: 0;
}
.check-box input[type="checkbox"]:focus-visible +label {
  outline: 1px solid var(--yellow);
  border-radius: 3px;
}

.check-box input:checked +label:before {
  background-color: var(--green);
  border-color: var(--green) ;
  box-shadow:  0 0 2px #fff inset;
}

.check-box label:before {
    content: "";
    display: inline-block;
    height: 15px;
    width: 15px;
    border: 1px solid var(--lightgrey) ;
    box-shadow: 0 0 0 1px var(--lightgrey) ;
    border-radius: 2px;
    left: -13px;
    position: relative;
}
.check-box label {
  display: block;
  padding: 0 5px;
  padding-left: 20px;
  margin: 5px 0;
}

.select-box {
    width: fit-content;
    display:flex;
    margin-left: auto;
    margin-bottom: 27px;
    margin-top: -8px;
    position:relative
}
.select-box .sort-box {
  border-radius: 5px;
  border: 1px solid var(--lightgrey) ;
  margin-right: 10px;
  padding: 5px 10px;
}
.select-box:before {
    content: "sort by";
    position: absolute;
    font-size: 9px;
    top: -15px;
    background: var(--white) ;
    display: block;
    padding: 0 4px;
    color: var(--secondary) ;
}
.select-box select{
  border:0;
  outline: 0;
  text-transform: capitalize;
}

.product-card > .content {
  display: grid;
  grid-gap: 10px;
}
.price-row {
  width:50%;
  margin-top: 5px;
}
.mrp:before, .price:before {
  content: 'Rs.';
  font-size: 10px;
}

.meta {
  position: relative;
}
.mrp {
  color: var(--lightgrey);
   /*text-decoration: line-through;*/
  display: block;
}
.price {
  font-size: 1.4rem;
  color: var(--red);
}
.cta-button{
  margin-top: auto;
}
.mq{
    position:absolute;
    right: -10px;
    bottom:-10px;
    width: 60%;
}
.mqRange{
  display: block;
  margin-bottom:  14px;
  font-size:  .9em;
}
.slider:focus-visible {
  outline-color: var(--yellow);
}
.brandname{
  color: var(--secondary);
}
@media screen and (max-width:  800px){
  .ui.column{
    min-width: 100%;
  }
  .ui.segment.filter-form.form {
    display: grid;
    grid-template-columns: 50% 50%;
  }
  .filter-form .header{
    grid-column:  1/3;
    font-size:  1.5rem;
    font-weight: 700;
  }
  .ui.card, .ui.cards>.card {
    width: 90%;
  }
  .mq {
    width:  113px;
  }
}
</style>
