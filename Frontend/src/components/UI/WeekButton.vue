<template>
  <button class="button">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: "WeekButton"
}
</script>

<style scoped>
.button {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  font-size: 1.3rem;
  text-transform: capitalize;
  border: none;
}

@media (max-width: 450px) {
  .button {
    width: 40px;
    height: 40px;
    font-size: 1.1rem;
  }
}
</style>