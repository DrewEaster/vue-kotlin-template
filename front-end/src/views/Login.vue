<template>
  <div></div>
</template>

<script>
export default {
  name: 'login',
  mounted() {
    this.$auth.login()
  }
}
</script>