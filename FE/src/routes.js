import {createWebHashHistory,createRouter}  from 'vue-router';
import HelloWorld from './components/HelloWorld.vue'
import Home from './components/Home.vue'
import PLP from './components/PLP.vue'
import PDP from './components/PDP.vue'
import GameArea from './components/Game.vue'
import Login from './components/Login.vue'
import MyCart from './components/MyCart.vue'
import NotFound from './components/NotFound.vue'

const routes = [ 
  {
    path: '/', 
    component: Home,
    name:"home",
    meta: {title:"How healthy is your cart"}
     },
  {
    path: '/about',
    component: HelloWorld,
    name:'about',
    meta: {title:"about"}
     },
  {
    path: '/plp/:id',
    component: PLP , 
    name:"plp",
    meta: {title:"plp/"}
  },
  {
    path: '/pdp/:code',
    component: PDP , 
    name:"pdp",
    meta: {title:"pdp"}
  },
  {
    path: '/game',
    component: GameArea , 
    name:"game",
    meta: {title:"Play game to win coins."}
  },
  {
    path: '/mycart',
    component: MyCart , 
    name:"MyCart",
    meta: {title:"MyCart"}
  },
  {
    path: '/login',
    component: Login , 
    name:"Login",
    meta: {title:"Login"}
  },
  {
  path: "/:catchAll(.*)",
  component: NotFound,
},
]

const router = createRouter({
  // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
  history: createWebHashHistory(process.env.BASE_URL),
  routes, // short for `routes: routes`
  linkExactActiveClass: "active"
})

router.beforeEach((to, fromRoute, next) => {
    window.document.title = 'Mealecule |' +to.meta && to.meta.title ? to.meta.title : 'How healthy is your cart';
    next();
});

export default {
  router
}