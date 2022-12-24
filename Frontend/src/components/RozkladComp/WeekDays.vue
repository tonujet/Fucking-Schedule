<template>
  <div class="days">
    <day-info
        class="day"
        v-for="(value, key, index) in scheduleDays"
        :lessons=value
        :key="key"
        :dayName="engToUaDayNames[key].fullName"
        :isVisible="isActiveDays[index]"
        :is-current-lesson="isCurrentLesson"
    />
  </div>
</template>

<script>
import dayInfo from "@/components/RozkladComp/DayInfo.vue";

export default {
  name: "WeekDays",
  components: {
    dayInfo
  },
  data() {
    return {
      timeArr: [],
      breakTimeArr:[],
      counter:0,
    }
  },
  mounted() {
    this.$load(async () => {
      this.timeArr = (await this.$api.time.readStartsTime()).data
    })
    this.$load(async () => {
      this.breakTimeArr = (await this.$api.time.readBreakTime()).data
    })
    this.counter = 0
    this.startCounter()
  },
  props: {
    scheduleDays: {
      type: Object,
      required: true,
    },
    engToUaDayNames: {
      type: Object,
      required: true,
    },
    isActiveDays: {
      type: Object,
      default: () => [false, false, false, false, false, false]
    },
    widthPage: {
      type: Number,
      required: true,
    },
    fullTimeArr: {
      type: Array,
      required: true,
    },

  },
  computed: {
    activeDays() {
      return [...this.isActiveDays]
    },
    isCurrentLesson(){
      let counter = this.counter
      let currentTime;
      let currentDate;
      currentDate = new Date();
      currentTime = new Date(null,null,null,
          currentDate.getHours() + 1 ,
          currentDate.getMinutes() + 1
      )
      let mintDifference
      let currentLessonTimeInString
      for (const t of this.timeArr) {
        let date = new Date(null, null, null,
            t.slice(0, t.indexOf(":")),
            t.slice(t.indexOf(":")+1)
        );
        let difference = date-currentTime;
        if((difference > mintDifference || !mintDifference) && difference<0){
          mintDifference = difference;
          currentLessonTimeInString = t
        }
      }
      const currentLessonInfo = {
        day: new Date().toLocaleDateString('en-US', {
          weekday: 'long',
        }).toUpperCase(),
      }
      for (const breakTime of this.breakTimeArr) {
        if(breakTime === currentLessonTimeInString){
          return currentLessonInfo;
        }
      }
      currentLessonInfo.time = currentLessonTimeInString;
      return currentLessonInfo

    },
  },
  methods:{
    startCounter(){
      setInterval(() =>{
        this.counter+=1
      }, 5000)
    }
  },
}
</script>

<style scoped>
.days {
  display: flex;
  justify-content: space-between;
}

.day {
  flex: 1 0 210px;
  margin-right: 15px;
}
</style>