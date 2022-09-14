import {createApp} from 'vue'
import Routes from './routes.js'
import App from './App.vue'
import VueLazyload from 'vue-lazyload'

const app = createApp(App);
app.use(VueLazyload)
app.use(Routes.router)
app.mount('#app')
