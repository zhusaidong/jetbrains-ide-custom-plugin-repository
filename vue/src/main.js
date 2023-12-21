import './assets/main.css'
import 'element-plus/dist/index.css'

import {createApp} from 'vue'
import App from './App.vue'
import router from './router/index.js'
import axios from 'axios'
import vueAxios from 'vue-axios'

createApp(App)
    .use(router)
    .use(vueAxios, axios)
    .mount('#app')
