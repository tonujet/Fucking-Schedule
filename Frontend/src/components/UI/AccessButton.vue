<template>
  <button class="button"><slot></slot></button>
</template>

<script>
export default {
name: "AccessButton"
}
</script>

<style scoped>
  .button{
    width: 110px;
    border-radius: 10px;
    border: 1px solid #252526;
  }
</style>