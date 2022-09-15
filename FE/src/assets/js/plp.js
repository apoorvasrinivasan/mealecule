
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
    sortKey:'calories',
    products: [],
    mealecules:[],
    brands:[],
    sortAs:true,
    facetDatas:[],
    loaders:{
      page:true
    }
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
      console.log(pm)
      vm.facetDatas = vm.facetDatas.filter(i=>{
        console.log(i.code)
    
        if(i.code == "CALORIES") return true
        let ind = pm.indexOf(i.code.toLowerCase())
        if(ind > -1) i.style = "background-color:"+col[ind];
        return (ind > -1)
      }).map(i=>{
          mqRange[i.code.toLowerCase()] = i.val
        return i;
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
      vm.products = [];
      Product.getProducts(category,(data) => {
        vm.loaders.page = false;
        vm.category = data.name;
        if(data.products.length == 0) return
          vm.products = data.products.map((i)=>{
            vm.facetDatas = data.facetDatas.filter((i)=>{
              i.val = Math.ceil(i.maxValue);
              return i.maxValue >=1;
            })
          if(vm.brands.indexOf(i.manufacturer) < 0)
            vm.brands.push(i.manufacturer)
          i.mq = Product.makeMQData(i.mealeculeQuotientData);
          i.price.coins = Math.ceil((vm.$root.disc/100) * i.price.value);
          i.price.discounted = i.price.value - i.price.coins;
          return i
        });
        vm.brands.sort()
        vm.mealecules = [...vm.$root.preferredMealecule, 'calories'];
      },
      (data) => {
        vm.loaders.page = false;
        console.log("error");
        console.log(data);
      });
    },
    addCart(e, p){
      let vm = this;
        p.loaders = true;
      e.preventDefault();
      User.addToCart(p.code, (data)=>{
        p.loaders = false;
        vm.$root.cart ++;
        vm.$root.cartMQ = data.mealeculeQuotientData;
      }),(()=>{
        p.loaders = false;
      });
    }
  }
}