<template>
  <div class="block">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "SecondLayerBlock"
}
</script>

<style scoped>
.block {
  background: #202225;
  box-shadow: 0px 0px 2px black;
}

</style>