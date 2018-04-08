<template>
  <div></div>
</template>

<script>
export default {
  name: 'logout',
  mounted() {
    this.$auth.logout().then(() => {
      this.$router.push({ name: 'home' })
    }).catch((err) => {
      this.$router.push({ name: 'home' })
    })
  }
}
</script>