import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import apiConfig from './config/ApiConfig'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    publicAlbums:  [],
    privateAlbums: [],
    loadingPublicAlbums: false,
    loadingPrivateAlbums: false
  },
  mutations: {
    setLoadingPublicAlbums(state, loading) {
      state.loadingPublicAlbums = loading
    },
    setLoadingPrivateAlbums(state, loading) {
      state.loadingPrivateAlbums = loading
    },
    setPublicAlbums(state, albums) {
      state.loadingPublicAlbums = false
      state.publicAlbums = albums
    },
    setPrivateAlbums(state, albums) {
      state.loadingPrivateAlbums = false
      state.privateAlbums = albums
    }
  },
  actions: {
    fetchPublicAlbums(context) {
      context.commit('setLoadingPublicAlbums', true)
      
      axios.get(`${apiConfig.apiUriBase()}/api/albums/public`)
      .then(response => {
        context.commit('setPublicAlbums', response.data)
      })
      .catch(e => {
        console.log(e) // TODO: Return error to UI
      })
    },
    fetchPrivateAlbums(context) {
      context.commit('setLoadingPrivateAlbums', true)
      axios.get(`${apiConfig.apiUriBase()}/api/albums/private`)
      .then(response => {
        context.commit('setPrivateAlbums', response.data)
      })
      .catch(e => {
        console.log(e) // TODO: Return error to UI
      })
    }
  }
})
