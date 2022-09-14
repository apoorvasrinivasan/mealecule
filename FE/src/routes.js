import {createWebHashHistory,createRouter}  from 'vue-router';

function lazyLoad(view){
  return() => import(`@/components/${view}.vue`)
}

const routes = [ 
  {
    path: '/', 
    component: lazyLoad('Home'),
    name:"home",
    meta: {title:"How healthy is your cart"}
     },
  {
    path: '/about',
    component: lazyLoad('HelloWorld'),
    name:'about',
    meta: {title:"about"}
     },
  {
    path: '/plp/:id',
    component: lazyLoad('PLP') , 
    name:"plp",
    meta: {title:"Products listing "}
  },
  {
    path: '/categories',
    component: lazyLoad('CatList') , 
    name:"CatList",
    meta: {title:"All Categories Listing"}
  },
  {
    path: '/pdp/:code',
    component: lazyLoad('PDP') , 
    name:"pdp",
    meta: {title:"pdp"}
  },
  {
    path: '/game',
    component: lazyLoad('Game') , 
    name:"game",
    meta: {title:"Play game to win coins."}
  },
  {
    path: '/mycart',
    component: lazyLoad('MyCart') , 
    name:"MyCart",
    meta: {title:"MyCart",loginRequired:true}
  },
  {
    path: '/login',
    component: lazyLoad('Login') , 
    name:"Login",
    meta: {title:"Login"}
  },
  {
    path: '/myAccount',
    component: lazyLoad('myAccount') , 
    name:"MyAccount",
    meta: {title:"MyAccount",loginRequired:true}
  },
  {
  path: "/:catchAll(.*)",
  component: lazyLoad('NotFound'),
},
]

const router = createRouter({
  // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
  history: createWebHashHistory(process.env.BASE_URL),
  routes, // short for `routes: routes`
  linkExactActiveClass: "active"
})

router.beforeEach((to, fromRoute, next) => {
    if (to.meta.loginRequired && !localStorage.user){
      next('login')
    }
    window.document.title = 'Mealecule |' +to.meta && to.meta.title ? to.meta.title : 'How healthy is your cart';
    next();
});

export default {
  router
}