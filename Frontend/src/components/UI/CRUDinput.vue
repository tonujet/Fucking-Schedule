<template>
  <input
      type="text"
      class="input"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      placeholder="Значення поля"
  >
</template>

<script>
export default {
  name: "CRUDInput",
  props:{
    modelValue:[String, Number, Date]
  }
}
</script>

<style scoped>
  .input{
    background: #2f3136;
    outline: none;
    color: #BBFFA7;
    border: 1px solid white;
    font-size: 1.2rem;
    border-radius: 0 0 15px 15px;
    padding: 10px;
  }
</style>