import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    publicAlbums:  [
      {
        "artist": "Bruce Springsteen",
        "title": "Born to Run"
      }
    ],
    privateAlbums: [
      {
        "artist": "Spice Girls",
        "title": "Spiceworld"
      }
    ]
  },
  mutations: {
    setPublicAlbums(albums) {
      state.publicAlbums = albums
    },
    setPrivateAlbums(albums) {
      state.privateAlbums = albums
    }
  },
  actions: {

  }
})
