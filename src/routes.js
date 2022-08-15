import {createWebHashHistory,createRouter}  from 'vue-router';
import HelloWorld from './components/HelloWorld.vue'
import PLP from './components/PLP.vue'

const routes = [ 
  {path: '/about', component: HelloWorld },
  {path: '/plp', component: PLP },
]

const router = createRouter({
  // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
  history: createWebHashHistory(process.env.BASE_URL),
  routes, // short for `routes: routes`
})

export default {
  router
}