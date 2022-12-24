<template>
  <div class="field">
    <slot class="field__icon"></slot>
    <div class="field__value field__exist" v-if="value">
      {{value}}
    </div>
    <div class="field__value field__empty" v-else>
      Пусто
    </div>
  </div>

</template>

<script>
export default {
  name: "LessonField",
  props:{
    value:{
      type:[Object,Number,String]
    }
  }
}
</script>

<style scoped>

  .field__icon{
    margin-right: 5px;
    display: inline-block;
  }
  .field__value{
      font-size: 1rem;
      display: inline-block;
  }
  .field__exist{

  }
  .field__empty{
      color: #FF3F36;
  }
</style>