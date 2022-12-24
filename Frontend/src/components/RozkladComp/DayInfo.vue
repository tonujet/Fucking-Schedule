<template>
  <FirstLayerBlock v-if="isVisible" class="day__info" :key="dayName">
    <div class="day__name">{{dayName}}</div>
    <Lesson
        v-for="(lesson, index) in lessons"
        :key="lesson.id"
        :lesson="lesson"
        class="day__lesson"
        :isLastLesson="index === lessons.length-1"
        :is-current-lesson="isCurrentLesson"
    />
  </FirstLayerBlock>
</template>

<script>
import Lesson from "@/components/RozkladComp/Lesson.vue";
export default {
  name: "DayInfo",
  components:{
    Lesson
  },
  props:{
    lessons:{
      type:Array,
      default: ()=>[],
    },
    dayName:{
      type:String,
      required:true,
    },
    isVisible:{
      type:Boolean,
      default:true,
    },
    isCurrentLesson:{
      type: Object,
      required: true,
    },
  }
}
</script>

<style scoped>
.day__info{
  color: white;
  border-radius: 20px;
  padding: 10px 7px;
}
.day__name{
  text-align: center;
  font-size: 2rem;
  margin-bottom: 15px;
  color: white;
}
.day__lesson{
  margin: 5px;
}

</style>