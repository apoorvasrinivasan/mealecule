import {createWebHashHistory,createRouter}  from 'vue-router';
import HelloWorld from './components/HelloWorld.vue'
import Home from './components/Home.vue'
import PLP from './components/PLP.vue'
import PDP from './components/PDP.vue'
import GameArea from './components/Game.vue'
import Login from './components/Login.vue'

const routes = [ 
  {path: '/', component: Home, name:"home" },
  {path: '/about', component: HelloWorld, name:'about' },
  {path: '/plp', component: PLP , name:"plp"},
  {path: '/pdp', component: PDP , name:"pdp"},
  {path: '/game', component: GameArea , name:"game"},
  {path: '/login', component: Login , name:"login"},
]

const router = createRouter({
  // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
  history: createWebHashHistory(process.env.BASE_URL),
  routes, // short for `routes: routes`
  linkExactActiveClass: "active"
})

export default {
  router
}