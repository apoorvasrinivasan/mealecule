
import User from '../../services/user'

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
      preferd_mq:{},
      pincode:'',
      orderplacing:false,
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

        let absmaxCoins = Math.ceil((.12) * carttotal);
        let maxCoins = Math.ceil((vm.$root.disc/100) * carttotal);
        let userCoins = vm.$root.total_coins;
        let discount = 0;
        if( userCoins > 0 && vm.$root.badges)
            discount = Math.min(userCoins, maxCoins);
        vm.price = {
          discount: discount,
          total: total - discount,
          carttotal: carttotal,
          tax: total - carttotal,
          maxDiscount: absmaxCoins
        }
    },
    delCartItem(i){
      let x = confirm("are you sure you want to remove item?");
      if(!x) return;
      let vm = this;
      User.updateCart(i,0,(data)=>{
        vm.cartitems.splice(i,1);
        vm.$root.cart = data.totalItems;
        vm.mq = vm.processCartMq(data.mealeculeQuotientData);
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
        vm.$root.cart = data.totalItems;
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
      let cmq = this.$root.filteredCartMq || mq
      for( let i in pm){
        if(pm[i]=='energy') continue
        try{
          mmq.push({
            k : pm[i],
            v: Math.round(cmq[pm[i]]),
            fill: 100 -  cmq[pm[i]],
            color: this.$root.colors[i]
          })
        }
        catch(e){continue}
      }
      highest_g = Math.max(highest_g, ...Object.values(cmq));
      // already in %
      if( highest_g == 100) return mmq;
      if (highest_g < 200) highest_g = 200;
      for( let i in mmq){
        let k = mmq[i].v
        mmq[i].fill = 100 - (Math.round((k/highest_g) * 100));
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
        if(index == -1 || k =='energy') continue;
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
    },
    async placeOrder(){
      let vm = this;
      vm.orderplacing=true
      let user  = JSON.parse(localStorage.userData);
      let useraddress = {
        "id":user.uid,
        "firstName":user.firstName,
        "lastName":user.lastName || 'Test',
        "titleCode":user.titleCode || 'mr',
        "line1":"123",
        "line2":"Road",
        "town":"NY",
        "region":{
            "isocode":"US-CA",
            "country":"US",
            "isocodeShort":"CA",
            "name":"California"
        },
        "postalCode":parseInt(vm.pincode),
        "phone":5555500000,
        "email":user.uid,
        "country":{
            "isocode":"US",
            "name":"US"
        },
        "shippingAddress":true,
        "defaultAddress":true
      }
      let payment;
      await User.updateAddress(useraddress).then((d)=>{
        d.titleCode = 'mr'
        payment = {
           "id": user.uid,
           "accountHolderName": "Test",
           "cardType": {
               "code":"visa",
               "name":"Visa"
           },
           "cardNumber": "4111111111111111",
           "startMonth": "10",
           "startYear": "2020",
           "expiryMonth": "10",
           "expiryYear": "2030",
           "issueNumber": "123",
           "subscriptionId": 1245,
           "billingAddress": d,
           "defaultAddress": true
        };
      });
      await User.updatePayment(payment);
      await User.placeOrder(payment).then(()=>{
        vm.orderplacing = false;
        vm.$root.cart = -1;
        vm.$router.push('/myAccount')

      });
    }
  }
}