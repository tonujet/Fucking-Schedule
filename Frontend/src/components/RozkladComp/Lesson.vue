<template>
  <div>
    <SecondLayerBlock
        class="lesson"
        :class="currClass"
>
      <div class="lesson__name" v-if="lesson.name">
        {{ lesson.name }}
      </div>
      <lesson-field
          class="lesson__field"
          :value="lesson.time"
      >
        <fa class="lesson__icon" :icon="['fas','clock']" size="lg" style="color: #86FF57"/>
      </lesson-field>
      <lesson-field
          class="lesson__field"
          :value="lesson.classroom"
      >
        <fa class="lesson__icon" :icon="['fas','chess-rook']" size="lg" style="color: #92FF63"/>
      </lesson-field>
      <lesson-field
          class="lesson__field"
          :value="lesson.teacher ? `${lesson.teacher.surname}  ${lesson.teacher.name}` : null"
      >
        <fa class="lesson__icon" :icon="['fas','person-chalkboard']" size="lg" style="color: #B8FF8F"/>
      </lesson-field>
    </SecondLayerBlock>
    <MyStripe class="break" v-if="!isLastLesson"/>
  </div>
</template>

<script>
import LessonField from "@/components/RozkladComp/LessonField.vue";

export default {
  name: "Lesson",
  components: {
    LessonField,
  },
  props: {
    lesson: {
      type: Object,
      required: true
    },
    isLastLesson: {
      type: Boolean
    },
    isCurrentLesson:{
      type: Object,
      required: true,
    }
  },
  computed:{
    currClass(){
      let answer = ""
      const currentInfo = this.isCurrentLesson;
      if(currentInfo.time === this.lesson.time &&
            currentInfo.day === this.lesson.dayOfWeek
      ){
        answer+="lesson__now"
      }
      if(this.lesson.id === 0){
        answer+=" lesson__empty"
      }
      return answer
    }
  },
}
</script>

<style scoped>
.lesson {
  padding: 10px;
  border-radius: 10px;
  height: 190px;
}



.lesson__name {
  font-size: 1rem;
  color: #FFC6BD;
  height: 40%;
  margin-bottom: 14px;
}

.lesson__empty {
  visibility: hidden;
}
.lesson__now {
  border: 1px solid white;
}

.lesson__field{
  margin-bottom: 10px;
  color: #DCFFC6;
}

.lesson__icon {
  width: 20px;
  margin-right: 5px;
}

.break {
  height: 25px;
  margin: 10px 0 11px;
  box-shadow: 2px 2px 5px black;
}
</style>