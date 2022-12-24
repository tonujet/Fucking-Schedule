<template>
  <div class="buttons">
    <week-button
        v-for="(value, key) in scheduleDays"
        :key="key"
        class="schedule__button"
        @click="$emit('selectDay', key )"
    >
      {{ engToUaDayNames[key].shortName }}
    </week-button>
  </div>
</template>

<script>
export default {
  name: "ScheduleButtons",
  props:{
    scheduleDays: {
      type: Object,
      required: true,
    },
    engToUaDayNames:{
      type: Object,
      required: true,
    }
  }
}

</script>

<style scoped>
  .schedule__button{
    cursor: pointer;
  }

</style>