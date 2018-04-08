<template>
  <div class="callback">Callback</div>
</template>

<script>
export default {
  name: 'callback',
  mounted() {
    this.$auth.handleAuthentication().then((redirectRouteName) => {
      this.$router.push({ name: redirectRouteName ? redirectRouteName : 'home' })
    }).catch((err) => {
      this.$router.push({ name: 'home' }) // TODO: Show login error
    })
  }
}
</script>