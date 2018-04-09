<template>
    <nav class="navbar is-info">
      <div class="navbar-menu">
        <div class="navbar-start">
          <span class="navbar-item is-size-4">Music Library App</span>
          <router-link class="navbar-item" :to="{ name: 'home'}">My public albums</router-link>
          <router-link class="navbar-item" :to="{ name: 'private-albums'}">My private albums</router-link>
        </div>
        <div class="navbar-end">
          <div class="navbar-item">
            <div class="field is-grouped">
              <p class="control">
                <router-link v-if="!isAuthenticated" class="button is-link" :to="{ name: 'login'}">Login</router-link>
                <router-link v-else class="button is-link" :to="{ name: 'logout'}">Logout</router-link>
              </p>
            </div>
          </div>
        </div>
      </div>
    </nav>
</template>

<script>
import bus from '@/eventbus'

export default {
  name: 'nav-bar',
  data() {
    return {
      isAuthenticated: this.$auth.isAuthenticated()
    }
  },
  created() {
    bus.$on('authentication_state_changed', () => {
        this.isAuthenticated = this.$auth.isAuthenticated()
    });
  }
}
</script>