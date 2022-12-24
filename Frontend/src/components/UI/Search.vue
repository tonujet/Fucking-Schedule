<template>
  <input
      class="search"
      type="text"
      @input="$emit('update:modelValue', $event.target.value)"
      :value="modelValue"
  >
</template>

<script>
export default {
  name: "Search",
  props:{
    modelValue:[String, Number]
  }
}
</script>

<style scoped>
.search {
  background: #2f3136;
  font-size: 2rem;
  color: #8DFF5C;
  padding: 8px 10px;
  border-radius: 20px;
  border: none;
  box-shadow: 2px 2px 4px black;
}
*::-webkit-input-placeholder {
  color: #A4FF87;
}
@media (max-width: 700px) {
  .search{
    font-size: 1.5rem;
  }
}
@media (max-width: 400px) {
  .search{
    font-size: 1rem;
  }
}
</style>