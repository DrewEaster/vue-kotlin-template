import 'bulma/css/bulma.css'

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import auth from './auth'
import axios from 'axios'

Vue.config.productionTip = false

Vue.use(auth)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
