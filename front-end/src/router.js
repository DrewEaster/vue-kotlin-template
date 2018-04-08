import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import About from './views/About.vue'
import PrivateAlbums from './views/PrivateAlbums.vue'
import Callback from './views/Callback.vue'
import Login from './views/Login.vue'
import Logout from './views/Logout.vue'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/albums/private',
      name: 'private-albums',
      component: PrivateAlbums,
      meta: { requiresAuth: true }
    },
    {
      path: '/callback',
      name: 'callback',
      component: Callback
    },
        {
      path: '/login',
      name: 'login',
      component: Login
    },    
    {
      path: '/logout',
      name: 'logout',
      component: Logout
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!router.app.$auth.isAuthenticated()) {
      router.app.$auth.login(to.name)
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router

