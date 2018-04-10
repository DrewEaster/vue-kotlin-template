<template>
    <div>
      <nav-bar v-bind:isAuthenticated="isAuthenticated"/>
      <router-view/>
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import bus from '@/eventbus'

export default {
  name: 'home',
  components: {
    NavBar
  },
  data() {
    return {
      isAuthenticated: this.$auth.isAuthenticated()
    }
  },
  created() {
    bus.$on('authentication_event', (eventType) => {
      if(eventType === 'logged_in' || eventType === 'logged_out') {
        this.isAuthenticated = this.$auth.isAuthenticated()
      } else if(eventType === 'api_authentication_failed') {
        let currentRouteName = this.$route.name
        this.$auth.login(currentRouteName)
      }
    });
  }
}
</script>
