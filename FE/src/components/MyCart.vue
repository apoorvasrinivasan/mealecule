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
</style>
