import auth0 from 'auth0-js'
import Vue from 'vue'
import auth0Config from './config/Auth0Config'
import bus from '@/eventbus'
import axios from 'axios'

var webAuth = new auth0.WebAuth({
  clientID: auth0Config.clientID(),
  domain: auth0Config.domain()
});

let auth = {
  token() {
    return localStorage.getItem('id_token')
  },
  accessToken() {
    return localStorage.getItem('access_token')
  },
  expiresAt() {
    return localStorage.getItem('expires_at')
  },
  user() {
    return JSON.parse(localStorage.getItem('user'))
  },
  login(redirectRouteName) {
    if(redirectRouteName) {
      localStorage.setItem('redirect_route_name', redirectRouteName)
    }
    
    this.clearLocalStorage()

    webAuth.authorize({
      responseType: 'token id_token',
      redirectUri: auth0Config.redirectUri(),
      audience: auth0Config.audience(),
      scope: auth0Config.scope()
    })
  },
  logout() {
    return new Promise((resolve, reject) => { 
      this.clearLocalStorage()
      bus.$emit('authentication_event', "logged_out");
      resolve()
    })
  },
  clearLocalStorage() {
      localStorage.removeItem('id_token')
      localStorage.removeItem('access_token')
      localStorage.removeItem('expires_at')
      localStorage.removeItem('user')
  },
  isAuthenticated() {
    return new Date().getTime() < this.expiresAt()
  },
  handleAuthentication() {
    return new Promise((resolve, reject) => {
      
      let redirectRouteName = localStorage.getItem('redirect_route_name')
      localStorage.removeItem('redirect_route_name')
      
      webAuth.parseHash((err, authResult) => {
        if (authResult && authResult.accessToken && authResult.idToken) {
          localStorage.setItem('id_token', authResult.idToken)
          localStorage.setItem('access_token', authResult.accessToken)
          localStorage.setItem('expires_at', JSON.stringify(authResult.expiresIn * 1000 + new Date().getTime()))
          localStorage.setItem('user', authResult.idTokenPayload)
          bus.$emit('authentication_event', "logged_in");
          resolve(redirectRouteName)
        } else if (err) {
          this.logout()
          reject(err)
        }
      })
    })
  }
}

export default {
  install: function(Vue) {
    
    axios.interceptors.request.use((config) => {
      if (auth.isAuthenticated()) {
        config.headers.Authorization = `Bearer ${auth.accessToken()}`;
      }
      return config;
    }, (err) => {
      return Promise.reject(err);
    });

    axios.interceptors.response.use( (response) => { return response }, (error) => {
      // TODO: If token expired then could refresh token and retry API request
      if (error.response.status === 401 && error.config && !error.config.__isRetryRequest) {
        bus.$emit('authentication_event', "api_authentication_failed");
      }
      return Promise.reject(error)
    })

    Vue.prototype.$auth = auth
  }
}