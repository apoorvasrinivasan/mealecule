<template lang="pug">
div.cart
  h1.ui.header My cart
  div.ui.message(v-if = 'cartitems.length==0')
    | Your cart is empty. 
    router-link(to="/categories") Shop for products.

  div.ui.grid(v-if = 'cartitems.length')
    div.ui.ten.wide.column
      h3.ui.header.teal {{ response.totalItems }} items
      ul.carts
        li.cartitems(v-for ="c in cartitems")
          div.ui.image
              img(:src="'api'+c.product.imageURL")
          div
              router-link.productname(:to="{name:'pdp', params:{code:c.product.code}}") {{ c.product.name }}
              br
              span {{ c.product.weightInG}}g
          div
            div.quantity.ui.icon.buttons.small.basic
              div.ui.button(v-on:click="updateCart(c.entryNumber, c.quantity-1)" :class="{'disabled':c.quantity == 1}")
                i.icon.minus

              div.ui.right.labeled.icon.basic.button
                | {{ c.quantity }}
                i.icon.plus(v-on:click="updateCart(c.entryNumber, c.quantity+1)")
          div.label.price {{ c.totalPrice.value }}
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
    div.ui.five.wide.column
      div.ui.segment
        h4.ui.header.teal Cart summary
        div.mq-bar
          div.mq-item(v-for="i in mq" :class="i.k" :style="'background-size: 100% '+ i.fill +'%; background-color:'+i.color")  
              small.mq-name {{ i.k }}
                  br
                  |{{ i.v }}g
        div.ui.form.user-add
          textarea(placeholder="Address")
          input(placeholder="Pincode")
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
      mq:{},
      preferd_mq:{}
    }
  },
  mounted() {
    this.getCart();
  },
  methods:{
    getCart(){
      let vm = this;
      User.myCart((data)=>{
        vm.response = data;
        vm.mq = vm.processCartMq(data.mealeculeQuotientData);
        let carttotal = parseFloat(data.totalPrice.value)
        let total = parseFloat(data.totalPriceWithTax.value)
        vm.calcTotal(carttotal, total)

        vm.cartitems = data.entries.map(vm.setPreferdMq)
      })
    },
    calcTotal:function(carttotal, total){
      let vm = this;
        let maxCoins = Math.floor(0.1 * carttotal);
        let userCoins = vm.$root.total_coins;
        let discount = Math.min(userCoins, maxCoins);
        vm.price = {
          discount: discount,
          total: total - discount,
          carttotal: carttotal,
          tax: total - carttotal
        }
    },
    delCartItem(i){
      let x = confirm("are you sure you want to remove item?");
      if(!x) return;
      let vm = this;
      User.updateCart(i,0,()=>{
        vm.cartitems.splice(i,1);
        let carttotal = vm.cartitems.reduce((a,b)=>{
          return a + parseFloat(b.totalPrice.value)  
        },0);
        vm.calcTotal(carttotal, carttotal);
      })
    },
    updateCart(i,q){
      if (q == 0) return
      let vm = this;
      User.updateCart(i,q,(data)=>{
        vm.cartitems[i] = vm.setPreferdMq(data.entry);
        vm.mq = vm.processCartMq(data.mealeculeQuotientData);
        let carttotal = vm.cartitems.reduce((a,b)=>{
          return a + parseFloat(b.totalPrice.value)  
        },0);

        vm.calcTotal(carttotal, carttotal);
      })
    },
    processCartMq(mq){
      let highest_g = 100;
      let mmq = []
      let pm = this.$root.preferredMealecule;
      
      // update the promo bar
      this.$root.cartMQ = mq;
      let user = JSON.parse(localStorage.userData);
      user.cartMQ = mq;
      localStorage.userData = JSON.stringify(user);

      for( let i in pm){
        try{
          mmq.push({
            k : pm[i],
            v: Math.round(mq[pm[i]]),
            fill: 100 -  mq[pm[i]],
            color: this.$root.colors[i]
          })
        }
        catch(e){continue}
      }
      highest_g = Math.max(highest_g, ...Object.values(mq));
      // already in %
      if( highest_g == 100) return mmq;
      if (highest_g < 200) highest_g = 200;
      for( let i in mmq){
        let k = mmq[i].v
        mmq[i].fill = 100 - Math.round((k/highest_g) * 1000)/10;
      }
      return mmq
    },
    setPreferdMq(product){
      let vm = this;
      let pm = vm.$root.preferredMealecule;
      let colors = vm.$root.colors;
      let p_mq = product.product.mealeculeQuotientData;
      let product_beakers = []

      // look for the product _ mq and make a list of nutrients present in the user prefered molecule; in that order
      for (let k in p_mq) {
        let index = pm.indexOf(k);
        if(index == -1 ) continue;
        product_beakers.push({
          key: k,
          value: p_mq[k],
          color: colors[index],
          index: index,
          style:`background-color:${colors[index]}; background-size:100% ${100 - p_mq[k]}%`
        })
      }
      product['mq_beakers'] = product_beakers;
      return product
      
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
  font-size:  1.7rem;

}
.ui.form textarea{
  height: 8em;
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
  margin-top:  40px;
}
.mq-bar .mq-item {
  /*animation: wave 4s ease infinite;*/
  background-image: linear-gradient(180deg, #f4f4f4, #f4f4f4);
  background-position: top left;
  background-repeat: repeat-x;
  border-radius: 0 0 6px 6px;
  border:  2px solid #ccc;
  border-top:  0;
  display: block;
  height:  100%;
  /*overflow: hidden;*/
  position: relative;
  text-align: center;
  text-transform: capitalize;
}
.mq-item .mq-name {
    position: relative;
    top: -38px;
    left: -8px;
    line-height: 4px;
    text-align: center;
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

.cart-summary .total{
  font-weight: bold;
  border-top:  1px solid #ccc;
  padding-top:  10px;
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
.mq_wise {
  display: flex;
}
.mq_wise .mq_beaker{
  background-image: linear-gradient(180deg, #f4f4f4, #f4f4f4);
  background-position: 0px 0px;
  background-repeat: no-repeat;
  background-size: 100% 60%;
  border-radius: 3px;
  border: 1px solid var(--lightgrey);
  border-top: none;
  height: 40px;
  margin-right: 20px;
  margin-bottom: 5px;
  width: 30px;
}
.supersmall{
  align-self: flex-end;
  display: inline-block;
  font-size: .6em;
  line-height: 1;
  text-align: center;
  overflow: hidden;
  /*text-overflow: ellipsis;*/
  width:  30px;
  text-transform: capitalize;
  white-space: break-word;
  
 
}
</style>
