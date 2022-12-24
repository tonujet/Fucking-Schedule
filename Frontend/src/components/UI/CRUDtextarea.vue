<template>
  <textarea
      class="textarea"
      name=""
      id=""
      @input="$emit('update:modelValue', $event.target.value)"
      :value="modelValue"
      placeholder="text"
  >

  </textarea>
</template>

<script>
export default {
  name: "CRUDTextarea",
  props:{
    modelValue:[String,Number,Boolean]
  }
}
</script>


<style scoped>
.textarea {
  height: 45px;
  width: 100%;
  background: #2f3136;
  color: #BBFFA7;
  border: 1px solid white;
  padding: 7px 10px;
  resize: none;
  border-radius: 5px;
}
</style>