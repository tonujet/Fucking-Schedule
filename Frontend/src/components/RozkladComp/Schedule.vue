<template>
  <div class="schedule">
    <div class="schedule__load" v-if="!isLoaded">
      <fa
          :icon="['fas', 'spinner']"
          spin
          size="3x"
          style="color: white"
          class="schedule__icon"
      />
    </div>
    <div class="schedule__ready" v-else>
      <div class="schedule__desc" v-if="!isScheduleExist">
        Обери свою групу
      </div>
      <schedule-buttons
          class="schedule__buttons"
          :scheduleDays="scheduleDays"
          :eng-to-ua-day-names="engToUaDayNames"
          @selectDay="setSelectedDay"
          v-if="isButtonsActive"
      />
      <div class="schedule__days days">
        <day-time
            class="days__time"
            :timeArr="scheduleTime"
            v-if="isScheduleExist"
        />
        <week-days
            class="days__block"
            :scheduleDays="scheduleDays"
            :engToUaDayNames="engToUaDayNames"
            :width-page="widthPage"
            :is-active-days="activeAndSelectedDays"
            :full-time-arr="scheduleTime"
        />
      </div>
    </div>
  </div>
</template>

<script>
import dayTime from "@/components/RozkladComp/DayTime.vue";
import dayInfo from "@/components/RozkladComp/DayInfo.vue";
import ScheduleButtons from "@/components/RozkladComp/ScheduleButtons.vue";
import WeekDays from "@/components/RozkladComp/WeekDays.vue";

export default {
  name: "Schedule",
  components: {
    dayTime, dayInfo, ScheduleButtons, WeekDays
  },
  props: {
    scheduleTime: {
      type: Array,
      required: true,
    },
    scheduleDays: {
      type: Object,
      required: true,
    },
    isLoaded:{
      type: Boolean,
      default: false,
    }
  },
  methods: {
    setSelectedDay(value) {
      this.selectedDay = value
    }
  },
  mounted() {
    window.addEventListener('resize', () => {
      this.widthPage = window.screen.width
    })
  },
  computed: {
    activeDays() {
      const selectedDay = [...this.selectedDay]
      const selectedDays = [...this.isActiveDays]
      if (this.widthPage < 525) {
        selectedDays[1] = false
      }
      if (this.widthPage < 750) {
        selectedDays[2] = false
      }
      if (this.widthPage < 981) {
        selectedDays[3] = false
      }
      if (this.widthPage < 1210) {
        selectedDays[4] = false
      }
      if (this.widthPage < 1420) {
        selectedDays[5] = false
        this.isButtonsActive = true
      } else {
        this.isButtonsActive = false
      }
      return selectedDays
    },
    activeAndSelectedDays() {
      const days = this.activeDays
      if (this.selectedDay === "monday" && !days[0]) {
        days[0] = true
        days[1] = false
        days[2] = false
        days[3] = false
        days[4] = false
        days[5] = false
      }
      if (this.selectedDay === "tuesday" && !days[1]) {
        days[0] = false
        days[1] = true
        days[2] = false
        days[3] = false
        days[4] = false
        days[5] = false
      }
      if (this.selectedDay === "wednesday" && !days[2]) {
        days[0] = false
        days[1] = false
        days[2] = true
        days[3] = false
        days[4] = false
        days[5] = false
      }
      if (this.selectedDay === "thursday" && !days[3]) {
        days[0] = false
        days[1] = false
        days[2] = false
        days[3] = true
        days[4] = false
        days[5] = false
      }
      if (this.selectedDay === "friday" && !days[4]) {
        days[0] = false
        days[1] = false
        days[2] = false
        days[3] = false
        days[4] = true
        days[5] = false
      }
      if (this.selectedDay === "saturday" && !days[5]) {
        days[0] = false
        days[1] = false
        days[2] = false
        days[3] = false
        days[4] = false
        days[5] = true
      }
      return days

    }
  },
  watch:{
    isLoaded(){
      this.isScheduleExist = true
    }
  },
  data() {
    return {
      isScheduleExist:false,
      selectedDay: "",
      widthPage: window.screen.width,
      isActiveDays: [true, true, true, true, true, true],
      engToUaDayNames: {
        monday: {
          fullName: "Понеділок",
          shortName: "пн"
        },
        tuesday: {
          fullName: "Вівторок",
          shortName: "вт"
        },
        wednesday: {
          fullName: "Середа",
          shortName: "ср"
        },
        thursday: {
          fullName: "Четвер",
          shortName: "чт"
        },
        friday: {
          fullName: "Пятниця",
          shortName: "пт"
        },
        saturday: {
          fullName: "Субота",
          shortName: "сб"
        },
        sunday: {
          fullName: "Неділя",
          shortName: "нд"
        },
      },
      isButtonsActive:false
    }
  }

}
</script>

<style scoped>
.days {

}
.schedule__desc{
  color: #8DFF5C;
  font-size: 3rem;
  text-align: center;
  margin: 25vh ;
}
.schedule__load{
  text-align: center;
  margin: 25vh ;
}
.schedule__icon{
  margin: 0 auto;
}

.schedule__buttons {
  max-width: 500px;
  margin: 0 auto 20px;
  display: flex;
  justify-content: space-evenly;
}


.days__block {
  margin-left: 48px;
  flex: 1 1 auto;
}

.days__time {
  position: relative;
}

@media (max-width: 1000px) {
  .days__block {
    margin-left: 44px;
  }
}
@media (max-width: 450px) {
  .days__block{
    margin-left: 42px;
  }
}
</style>