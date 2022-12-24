<template>
  <span class="field">
    <div
        v-if="name === 'headerImg'"
        class="field__img-value"
    >
      <img :src="this.img" class="field__Img" alt="Header image">
    </div>
     <div
         v-else-if="name === 'dateCreated'"
         class="field__value"
     >
      {{new Date(field).toLocaleString()}}
    </div>
    <span
        v-else-if="!field"
        class="field__value emptyValue"
    >
      {{"Пусто"}}
    </span>
    <span
        v-else-if="typeof field === 'object' && itemName === 'teacher'"
        class="field__value"
    >
      {{`${field.name} ${field.surname}`}}

    </span>

    <span
        v-else-if="typeof field === 'object'"
        class="field__value"
    >
      {{field.name}}
    </span>
    <span
        v-else-if="itemName === 'dayOfWeek'"
        class="field__value"
    >
      {{weekDays.filter(item => item.serverName === field)[0].uaName}}
    </span>
    <span
        v-else
        class="field__value"
    >
      {{field}}
    </span>
  </span>
</template>

<script>
import weekDays from "@/mocks/WeekDays";
export default {
  name: "SubjectValue",
  props:{
    itemName:[String],
    field:[Object, Number, String],
    name:[String],
    item:[Object],
    img:[String],
  },
  data(){
    return{
      weekDays:weekDays,
    }
  },
}
</script>

<style scoped>
.field__value {
  color: #C1FF9D;
}
.emptyValue{
  color: #FF3528;
}
.field__Img{
  margin-top: 10px;
  height: 180px;
  max-width: 400px;
}
.field__img-value{
  display: flex;
  justify-content: center;
}
</style>