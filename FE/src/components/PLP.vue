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
            img(:src="'/api/'+p.imageURL" :alt="p.name")
          
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
      let pm = this.$root.preferredMealecule;
      let col = this.$root.colors;

      vm.facetDatas = vm.facetDatas.filter(i=>{
        let ind = pm.indexOf(i.code.toLowerCase())
        if(ind > -1) i.style = "background-color:"+col[ind];
        return (ind > -1)
      }).map(i=>{
        if(i.code == 'CALORIES')
          mqRange['energy'] = i.val
        else
          mqRange[i.code.toLowerCase()] = i.val
        return i;
      }).sort((a,b)=>{
        let inda = pm.indexOf(a.code.toLowerCase())
        let indb = pm.indexOf(b.code.toLowerCase())
        return (inda>indb) ? 1:-1;
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
      User.addToCart(code, (data)=>{
        vm.$root.cart ++;
        vm.$root.cartMQ = data.mealeculeQuotientData;
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.ui.card, .ui.cards>.card {
  width:  212px;
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
.product-card .product-name{
  margin-bottom:  10px;
}
.product-card > .content {
  display: grid;
  grid-gap: 10px;
}
.price-row {
  width:50%;
  margin-top: 5px;
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
  font-size: 1.2rem;
  color:  #222;
  /*color: var(--red);*/
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
.slider {
  appearance:  none;
  background-color: var(--lightgrey);
      border-radius: 18px;
    height: 8px;
}
.slider:focus-visible {
  outline-color: var(--yellow);
}
.brandname{
  color: var(--secondary);
}
.ui.cards>.card> .image {
  background: #fff;
  display: flex;
  height: 200px;
  align-items: center;
}
.ui.cards>.card>.image>img{
  width: auto;
  max-height:  100%;
  margin:  0 auto;
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

  .ui.cards {
    justify-content: center;
  }
  .ui.card, .ui.cards>.card {
    width: 90%;
  }
  .mq {
    width:  113px;
  }
  .ui.cards>.card>.image{
    height:  100px;
  }
}
</style>
