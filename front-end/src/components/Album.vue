<template>
    <div class="card">
      <div class="card-content">
        <p class="title">
          {{ title }}
        </p>
        <p class="subtitle">
          {{ artist }}
        </p>
      </div>
    </div>
</template>

<script>
export default {
  name: 'album',
  props: {
    title: String,
    artist: String
  }
}
</script>

<style scoped>
.card {
  width: 100%;
  height: 100%;
}
</style>