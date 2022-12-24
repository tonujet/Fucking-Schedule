<template>
  <div class="rozklad">
    <SearchSelect
        class="rozklad__search"
        :options="options"
        v-model="currentOption"
        placeholder="Групи"
    />
    <MyStripe
        class="rozklad__stripe"
    />
    <Schedule
        :scheduleTime="scheduleTime"
        :scheduleDays="scheduleDays"
        class="rozklad__schedule"
        :is-loaded="isLoaded"
    />
  </div>
</template>

<script>
import vSelect from "vue-select";
import scheduleDays from "@/mocks/scheduleDays";
import scheduleTime from "@/mocks/scheduleTime";
import MyStripe from "@/components/Blocks/My-Stripe.vue";
import Schedule from "@/components/RozkladComp/Schedule.vue";
import SearchSelect from "@/components/UI/SearchSelect.vue";
import groups from "@/mocks/groups";

export default {
  name: "Rozklad",
  components: {
    SearchSelect,
    Schedule, MyStripe, vSelect
  },
  data() {
    return {
      scheduleTime: [],
      scheduleDays: {},
      options: [],
      currentOption: null,
      isLoaded: true
    }
  },
  mounted() {
    this.currentOption = JSON.parse(localStorage.getItem("searchGroup"))
    this.$load(async () => {
      let groups = (await this.$api.subject.readSubjectItems("groups", 0)).data
      groups.forEach(group => {
        const optionObj = {
          label: group.name,
          code: group.id,
        }
        this.options.push(optionObj)
      })
    })
  },

  watch: {
    currentOption(newValue, oldValue) {
      localStorage.setItem("searchGroup", JSON.stringify(newValue))
        this.$load(async () => {
          this.isLoaded = false
          this.scheduleDays = (await this.$api.scheduleDays.readFullSchedule(newValue.code)).data
          this.isLoaded = true
        })
        this.$load(async () => {
          this.scheduleTime = (await this.$api.time.readFullTime()).data
        })


    }
  }
}

</script>

<style scoped>
.rozklad {
  padding: 20px 0;
  position: relative;
}

.rozklad__search {
  margin: 0 auto 20px;
  width: 600px;
}

.rozklad__stripe {
  height: 10px;
  margin-bottom: 20px;
}

.rozklad__schedule {
  padding: 0 20px;
}

@media (max-width: 670px) {
  .rozklad {
    padding: 15px 0;
  }

  .rozklad__search {
    width: 90%;
    margin: 0 auto 15px;
  }
}

@media (max-width: 450px) {
  rozklad {
    padding: 5px 0;
  }

  .rozklad__schedule {
    padding: 0 10px;
  }
}
</style>
