<template>
  <input
      class="date"
      type="datetime-local"
      @change="$emit('update:modelValue',$event.target.value)">
</template>

<script>
export default {
  name: "CRUDDate"
}
</script>

<style scoped>
.date{
  background: #2f3136;
  outline: none;
  color: #BBFFA7;
  border: 1px solid white;
  font-size: 1.2rem;
  border-radius: 0 0 15px 15px;
  padding: 10px;
  width: 100%;
}


</style>