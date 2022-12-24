<template>
  <div class="time__queue">
    <div  v-for="(time, index) in timeArr" :class="'type'.concat(index)" class="time" >
      {{time}}
    </div>
  </div>
</template>

<script>
export default {
  name: "DayTime",
  props:{
    timeArr:{
      type:Array,
      required:true,
    }
  }
}
</script>

<style scoped>
 .time{
   color: white;
   position: absolute;
   top:0;
   left: 2px;
 }
 .time__queue{
   width: 40px;
 }
 .type0{
   top: 52px;
 }
 .type1{
   top: 248px;
 }
 .type2{
   top: 282px;
 }
 .type3{
   top: 482px;
 }
 .type4{
   top: 518px;
 }
 .type5{
   top: 718px;
 }
 .type6{
   top: 754px;
 }
 .type7{
   top: 952px;
 }
 .type8{
   top: 988px;
 }
 .type9{
   top: 1186px;
 }
 .type10{
   top: 1222px;
 }
 .type11{
   top: 1420px;
 }
</style>