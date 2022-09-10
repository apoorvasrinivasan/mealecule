
import Product from '../../services/product'
import User from '../../services/user'
import MQ from '../../components/MQ.vue'

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
        if(i.code == "CALORIES") return true
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
          i.price.coins = Math.ceil((vm.$root.disc/100) * i.price.value);
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