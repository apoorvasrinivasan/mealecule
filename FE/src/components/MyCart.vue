<template lang="pug">
div.cart
  h1.ui.header My cart
  div.ui.grid
    div.ui.ten.wide.column
      h3.ui.header.teal {{ response.totalItems }} items
      ul.carts
        li.cartitems(v-for ="c in cartitems")
          div.ui.image
          div.productname  {{ c.product.name }}
          div.quantity.ui.icon.buttons.small.basic
            div.ui.button
              i.icon.minus
            div.ui.right.labeled.icon.basic.button
              | {{ c.quantity }}
              i.icon.plus
          div.label.price {{ c.totalPrice.value }}
          <!-- i.icon.large.trash.alternate.outline -->
        li.cartitems.total
          span
          b Total
          span
          b.price {{ price.carttotal }}
    div.ui.five.wide.column
      div.ui.segment
        h4.ui.header.teal Cart summary
        div.mq-bar
          div.mq-item(v-for="m ,q in mq" :class="q" :style="'background-size: 100% '+ m +'%'") {{ q }} 
            small {{ m }}g
        div.ui.form.user-add
          textarea(placeholder="Address", height=50)
          input(placeholder="pincode")
        div.cart-summary
          span Cart Value
          span.price {{ price.carttotal }}
          span Tax
          span.price {{ price.tax }}
          span Coins Discount
          span.price {{ price.discount }}
          span.total Total
          span.total.price {{price.total }}
        button.ui.button.primary.cta.fluid Place Order
</template>

<script>

import User from '../services/user'

export default {
  name: 'MyCart',
    components:{
  },
  data(){
    return {
      cartitems:[],
      response:{},
      price:{},
      mq:{}
    }
  },
  mounted() {
    this.getCart()
  },
  methods:{
    getCart(){
      let vm = this;
      User.myCart((data)=>{
        vm.response = data;
        vm.mq = data.mealeculeQuotientData;
        let carttotal = parseFloat(data.totalPrice.value)
        let total = parseFloat(data.totalPriceWithTax.value)
        let maxCoins = Math.floor(0.1 * carttotal);
        let userCoins = vm.$root.total_coins;
        let discount = Math.min(userCoins, maxCoins);
        vm.price = {
          discount: discount,
          total: total - discount,
          carttotal: carttotal,
          tax: total - carttotal
        }

        vm.cartitems = data.entries;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cartitems{
  display: grid;
  grid-template-columns: 60px 1fr auto 80px;
  justify-content: space-between;
  margin-bottom: 15px;
  border-bottom: 1px solid #ccc;
  padding: 15px 0px;
  grid-gap:  10px;
}
.cartitems .image {
  grid-row:  1/3;
}

.cartitems .price {
  margin-left: auto;
  text-align: right;
  align-self: center;
  font-size:  1.2rem;

}

.icon.trash{
  color:  var(--red);
  align-self: center;
}
.quantity .button{
  ;
}
.mq-bar {
  display: grid;
  grid-template-columns: repeat(4, 20%);
  justify-content: space-between;
  height:  200px;
  padding:  20px 10px 5px;
}
.mq-bar .mq-item {
  /*animation: wave 4s ease infinite;*/
  background-color: #f4f4f4;
  background-image:  linear-gradient(var(--red) , var(--yellow));
  background-position: bottom;
  background-repeat: repeat-x;
  border-radius: 0 0 6px 6px;
  border:  2px solid #ccc;
  border-top:  0;
  display: block;
  height:  100%;
  overflow: hidden;
  position: relative;
  text-align: center;
  text-transform: capitalize;
}

.cart-summary{
  display: grid;
  grid-gap: 5px;
  margin-top:  20px;
  grid-template-columns: 1fr 40px;
  justify-content: space-between;
}
.cart-summary .price{
  text-align: right;
  padding:  5px 0;
}
.cart-summary .price:before{
  content:  'Rs';
  font-size: .7em;
}
.cart-summary .total{
  font-weight: bold;
  border-top:  1px solid #ccc;
  padding-top:  10px;
}
/*.mq-item:after {
    content: "";
    position: absolute;
    display: block;
    background: red;
    width: 26px;
    height: 19px;
    bottom: calc(40% - 15px);
    border-radius: 50%;
}

.mq-item:before {
    content: "";
    position: absolute;
    display: block;
    background: #f4f4f4;
    width: 25px;
    height: 13px;
    bottom: calc(40% - 5px);
    border-radius: 50%;
    left: 25px;
    animation: wave 6s cubic-bezier( 0.36, 0.45, 0.63, 0.53) infinite;
}*/
@keyframes wave{
  from {
    background-size:  100% 38% ;
  }
  to {

    background-size:  100% 40%;
  }
}
.cta{
  margin-top:  20px;
}
.user-add{
  margin:  10px 0;
}
.user-add textarea, .user-add input{
  margin: 10px 0;
}
</style>
