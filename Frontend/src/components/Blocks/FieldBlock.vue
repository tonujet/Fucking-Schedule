<template>
<FirstLayerBlock class="block">
  <slot></slot>
</FirstLayerBlock>
</template>

<script>
export default {
  name: "FieldBlock",
  components: {}

}
</script>

<style scoped>
.block{
  border-radius: 0 0 30px 30px ;
  border: 1px solid black;
  border-top: none;
}
@media (max-width: 450px) {
  .block{
    border-radius: 0 0 25px 25px;
  }
}
</style>