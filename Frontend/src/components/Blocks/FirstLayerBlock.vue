<template>
  <div class="block">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "FirstLayerBlock"
}
</script>

<style scoped>
.block{
  background-color: #2f3136;
  box-shadow: 2px 2px 5px black;
  transition: 0.5s;
}
</style>