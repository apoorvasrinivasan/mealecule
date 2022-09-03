<template lang="pug">
div.cart
  h1.ui.header My cart
  div.ui.grid
    div.ui.ten.wide.column
      h3.ui.header.teal 5 items
      ul.carts
        li.cartitems
          div.ui.image
          div.productname  Maggie Masala
          div.quantity.ui.icon.buttons.small.basic
            div.ui.button
              i.icon.minus
            div.ui.right.labeled.icon.basic.button
              | 1
              i.icon.plus
          div.label.price Rs.150
          i.icon.large.trash.alternate.outline
        li.cartitems
          div.ui.image
          div.productname  Maggie Masala
          div.quantity.ui.icon.buttons.small.basic
            div.ui.button
              i.icon.minus
            div.ui.right.labeled.icon.basic.button
              | 1
              i.icon.plus
          div.label.price Rs.150
          i.icon.large.trash.alternate.outline
        li.cartitems.total
          span
          b Total
          span
          b.price Rs 160
    div.ui.five.wide.column
      div.ui.segment
        h4.ui.header.teal Cart summary
        div.mq-bar
          div.mq-item.carb carb
          div.mq-item.fat fat
          div.mq-item.protien protien
          div.mq-item.fibre fibre
        div.ui.form.user-add
          textarea(placeholder="Address", height=50)
          input(placeholder="pincode")
        div.cart-summary
          span Cart Value
          span.price 160
          span Shipping
          span.price 10
          span Discount
          span.price 30
          span.total Total
          span.total.price 130
        button.ui.button.primary.cta.fluid Place Order
</template>

<script>

export default {
  name: 'MyCart',
    components:{
  },
  data(){
    return {
    }
  },
  mounted() {
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
  display: block;
  background-color: #f4f4f4;
  border:  2px solid #ccc;
  border-top:  0;
  border-radius: 0 0 6px 6px;
  height:  100%;
  text-align: center;
  background-image:  linear-gradient(red , yellow);
  background-repeat: repeat-x;
  background-position: bottom;
  background-size:  100% 40%;
  position: relative;
  overflow: hidden;
    animation: wave 4s ease infinite;
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
.mq-item:after {
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
    /*animation: wave 6s cubic-bezier( 0.36, 0.45, 0.63, 0.53) infinite;*/
}
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
