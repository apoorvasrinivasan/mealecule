import {createApp} from 'vue'
import Routes from './routes.js'
import App from './App.vue'

const app = createApp(App);
app.use(Routes.router)
app.mount('#app')
