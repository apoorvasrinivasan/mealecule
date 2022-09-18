<template lang="pug">
div.cart
  h1.ui.header My cart
  div.ui.message(v-if = 'cartitems.length==0')
    | Your cart is empty. 
    router-link(to="/categories") Shop for products.

  div.ui.grid.cart-grid(v-if = 'cartitems.length')
    div
      h3.ui.header.teal {{ cartitems.length }} items
      ul.carts
        li.cartitems(v-for ="c in cartitems")
          div.ui.image
              img(:src="'api'+c.product.imageURL")
          div
              router-link.productname(:to="{name:'pdp', params:{code:c.product.code}}") {{ c.product.name }}
              br
              span Entry {{ c.entryNumber}}
              br
              span {{ c.product.weightInG}}g
          div
            div.quantity.ui.icon.buttons.small.basic
              div.ui.button(v-on:click="updateCart(c.entryNumber, c.quantity-1)" :class="{'disabled':c.quantity == 1}")
                i.icon.minus

              div.ui.right.labeled.icon.basic.button
                | {{ c.quantity }}
                i.icon.plus(v-on:click="updateCart(c.entryNumber, c.quantity+1)")
          div
              span.label.price {{ c.totalPrice.value }}
              span.ui.tiny.teal.label {{ c.product.mealeculeQuotientData.calories}} kcal
          i.icon.large.trash.alternate.outline(v-on:click="delCartItem(c.entryNumber)")
          div.mq_wise
            div(v-for="p in c.mq_beakers")
              div.mq_beaker(:style="p.style") 
              small.supersmall {{ p.key }}  {{p.value}}g
          small.supersmall nutrients per 100g

        li.cartitems.total
          span
          b Total
          span
          b.price {{ price.carttotal }}
    div
      div.ui.segment
        h4.ui.header.teal Cart summary
        div.mq-bar
          div.mq-item(v-for="i in mq" :class="i.k" :style="'background-size: 100% '+ i.fill +'%; background-color:'+i.color")  
              small.mq-name {{ i.k }}
                  br
                  |{{ i.v }}g
        div.cart-summary
          span Cart Value
          span.price {{ price.carttotal }}
          span Tax
          span.price {{ price.tax }}
          span Coins Discount
          span.price {{ price.discount }}
          small You can get upto Rs. {{ price.maxDiscount }} discount. 
          router-link(style="font-size:.6em; text-align:right" to="/") learn more
          span.total Total
          span.total.price {{price.total }}
        div.ui.form.user-add
          label(for="pincode") Delivering to 
          input#pincode(placeholder="Pincode" v-model="pincode")
        button.ui.button.primary.cta.fluid(v-on:click="placeOrder()" :disabled="orderplacing || !pincode.trim()") Place Order
</template>

<script src="@/assets/js/cart.js">

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="@/assets/css/cart.css">
</style>
