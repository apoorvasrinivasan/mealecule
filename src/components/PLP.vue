<template lang="pug">
div.plp
  h1.ui.header {{ category}}
  div.ui.grid
    div.ui.three.wide.column
      div.ui.segment.form.filter-form
        span.ui.header.teal Filters
        div.ui.header.tiny Type
        input(v-model="filterKeys" value= "1" id="s1" type="checkbox")
        label(for="s1") Atta Maggie
        input(v-model="filterKeys" value= "2" id="s2" type="checkbox")
        label(for="s2") Oats Maggie
        input(v-model="filterKeys" value= "3" id="s3" type="checkbox")
        label(for="s3") maida
        div.ui.header.tiny 
            | Mealecule
        input(id="s4" type="checkbox" name="example")
        label(for="s4") Protien
        input(id="s5" type="checkbox" name="example")
        label(for="s5") Fat
        input(id="s6" type="checkbox" name="example")
        label(for="s6") Fibre


    div.ui.thirteen.wide.column  
      label.select-box(aria-label ="Sort by")
        select(v-model="sortKey")
          option(value="userId") Price low to high
          option(value="completed") Protien
          option(value="title") Fat
          option(value="id") Carb
          option Fibre
      div.ui.link.cards
        div.card(v-for="p in filteredList" :key="p.id")
          div.image
            img(src="https://www.maggi.in/sites/default/files/styles/product_image_desktop_617_900/public/maggi-2minutes-noodles-617x900.png?itok=GgDSaGCE" alt="productimage")
          
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
    filterKeys : [],
    sortKey:'id',
    products: []
  }
  },
  computed: {
    filteredList() {
      let vm=this;
      let a = [];
      if(vm.filterKeys.length ==0)
        a= vm.products;
      else
        a = vm.products.filter(i => {
          return vm.filterKeys.indexOf(i.userId.toString()) >=0;
        });
      return a.sort((a,b)=> {
        return (a[vm.sortKey] < b[vm.sortKey])?-1:1;
      });
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
.ui.cards {
      justify-content: space-between;
}
.ui.card, .ui.cards>.card {
  width:  212px;
}
.image img{
  max-height: 100%;
}
.filter-form input {
  display:none;
}

.filter-form input:checked +label:before {
  background-color: var(--green) ;
  border-color: var(--green) ;
  box-shadow: 0 0 0 3px var(--white)  inset;
}

.filter-form label:before {
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
.filter-form label {
  display: block;
  padding: 5px;
  padding-left: 20px;
  margin: 5px 0;
}

.select-box {
    width: fit-content;
    display:block;
    border: 1px solid var(--lightgrey) ;
    margin-left: auto;
    margin-bottom: 27px;
    margin-top: -8px;
    padding: 5px 10px;
    border-radius: 5px;
    position:relative
}

.select-box:before {
    content: "sort by";
    position: absolute;
    font-size: 9px;
    top: -11px;
    background: var(--white) ;
    display: block;
    padding: 0 4px;
    color: var(--secondary) ;
}
.select-box select{
  border:0;
  outline: 0;
}

</style>
