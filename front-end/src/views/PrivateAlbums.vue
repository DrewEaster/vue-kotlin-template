<template>
  <section v-if="loading">
    <Loader/>
  </section>
  <section v-else>
    <section>
      <div class="container has-text-centered">
        <p class=" is-size-3">My Private Albums</p>
      </div>
    </section>
    <section class="section">
      <div class="columns is-multiline is-mobile">
        <div v-for="album in albums" class="column is-one-quarter">
          <Album v-bind:title="album.title" v-bind:artist="album.artist"/>
        </div>
      </div>
    </section>
  </section>
</template>

<script>
import Album from '@/components/Album.vue'
import Loader from '@/components/Loader.vue'

export default {
  name: 'private-albums',
  components: {
    Album,
    Loader
  },
  computed: {
    loading () {
      return this.$store.state.loadingPrivateAlbums
    },
    albums () {
      return this.$store.state.privateAlbums
    }
  },
  created() {
    this.$store.dispatch('fetchPrivateAlbums')
  }
}
</script>
