import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import apiConfig from './config/ApiConfig'

Vue.use(Vuex)

// TODO: Use spinner when loading from API
export default new Vuex.Store({
  state: {
    publicAlbums:  [],
    privateAlbums: []
  },
  mutations: {
    setPublicAlbums(state, albums) {
      state.publicAlbums = albums
    },
    setPrivateAlbums(state, albums) {
      state.privateAlbums = albums
    }
  },
  actions: {
    fetchPublicAlbums(context) {
      axios.get(`${apiConfig.apiUriBase()}/api/albums/public`)
      .then(response => {
        context.commit('setPublicAlbums', response.data)
      })
      .catch(e => {
        console.log(e) // TODO: Return error to UI
      })
    },
    fetchPrivateAlbums(context) {
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
