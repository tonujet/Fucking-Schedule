<template>
  <select :value="modelValue" @change="changeSort" class="select">
    <option disabled value="" >Фільтри</option>
    <option
        v-for="option in options"
        :key = "option.value"
        :value ="option.value"
    >
      {{option.name}}
    </option>
  </select>
</template>

<script>
export default {
  name: "FilterSelect",
  props:{
    modelValue:{
      type:String
    },
    options:{
      type:Array,
      default:() => [],
    }
  },
  methods:{
    changeSort(event){
      this.$emit("update:modelValue", event.target.value)
    }
  }
}
</script>

<style scoped>
.select{
  background: #2f3136;
  border: none;
  box-shadow: 2px 2px 2px black;
  padding: 10px;
  color: wheat;
  border-radius: 15px;
  font-size: 1.5rem;
}
@media (max-width: 1300px) {
  .select{
    font-size: 1rem;
  }
}
</style>