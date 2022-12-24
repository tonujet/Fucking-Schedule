<template>
  <div class="raw">
    <field-block
        v-for="element in raw"
        class="raw__item"
    >

      <div
          v-if="checkComp(element)"
          class="raw__name"
      >
        <div
            v-if="checkImage(element)"
            class="raw__img-div"
        >
          <img :src="element" alt="Header Logo" class="raw__img">
        </div>
        <div
            class="name__empty"
            v-else-if="element === 'Пусто'"
        >
          {{ element }}
        </div>

        <div
            class="name__exist"
            v-else>
          {{ element }}
        </div>
      </div>
      <div
          v-else-if="element.type ==='$input' && (raw[0] ==='phone' || raw[0] === 'tel') "
      >
        <c-r-u-d-input
            class="raw__input"
            v-model.trim="payload[raw[0]]"
            v-mask="'+38(0##) ###-##-##'"
            placeholder="+38(0__)___-__-__"
            @keyup.enter="$emit('enter')"
        />
        <div class="raw__error" v-if="errorMessage">
          {{ errorMessage }}
        </div>
      </div>
      <div
          v-else-if="element.type ==='$input'"
      >
        <c-r-u-d-input
            class="raw__input"
            v-model.trim="payload[raw[0]]"
            @keyup.enter="$emit('enter')"
        />
        <div class="raw__error" v-if="errorMessage">
          {{ errorMessage }}
        </div>
      </div>
      <div
          v-else-if="element.type ==='$Date'"
      >
        <c-r-u-d-date
            v-model.trim="payload[raw[0]]"
        />
        <div class="raw__error" v-if="errorMessage">
          {{ errorMessage }}
        </div>
      </div>
      <div
          class="raw__enum"
          v-else-if="element.type ==='$ENUM'"
      >
        <div
            class="raw__select"
            :class="{raw__select_with_width:isUpdatePage}"
        >
          <SearchSelect
              :options="this.comps"
              v-model.trim="payload[raw[0]]"
              placeholder="Можливі значення поля"
              label="selectedName"
              @keyup.enter="$emit('enter')"
          />
        </div>
        <div class="raw__error" v-if="errorMessage">
          {{ errorMessage }}
        </div>
      </div>
      <div
          class="raw__enum"
          v-else-if="element.type ==='$File'"
      >
        <div
            class="raw__file"
        >
          <c-r-u-d-file
              accept="image/*"
              v-model="payload[raw[0]]"
              :isNull="isImgNull"
          />
        </div>
        <div class="raw__error" v-if="errorMessage">
          {{ errorMessage }}
        </div>
      </div>
      <div
          v-else-if="element.type ==='$Text'"
      >
        <c-r-u-d-textarea
            class="raw__textarea"
            v-model.trim="payload[raw[0]]"
        />
        <div class="raw__error" v-if="errorMessage">
          {{ errorMessage }}
        </div>
      </div>
      <div
          class="raw__enum"
          v-else-if="element.type ==='$weekDays'"
      >
        <div
            class="raw__select"
        >
          <SearchSelect
              :options="weekDays"
              v-model="payload[raw[0]]"
              :reduce="day => day.serverName"
              placeholder="Дні тижня"
              label="uaName"
              @keyup.enter="$emit('enter')"
          />
          <div class="raw__error" v-if="errorMessage">
            {{ errorMessage }}
          </div>
        </div>
      </div>
      <div
          v-else-if="element.type ==='$DateValue'"
      >
        Неможливо задати
      </div>
    </field-block>
  </div>
</template>

<script>
import {mask} from 'vue-the-mask'
import weekDays from "@/mocks/WeekDays";

export default {
  name: "ListRaw",
  components: {},
  directives: {
    mask
  },
  data() {
    return {
      comps: [],
      isRequest: false,
      weekDays: weekDays,
      isImgNull:false
    }
  },
  mounted() {
  },
  methods: {
    checkComp(value) {
      if (typeof value === 'object' && value.type.startsWith("$")) {
        if (!this.isRequest) {
          this.isRequest = true
          const data = value.data
          if (data && data !== "daysOfWeek" && data !== "startsTime") {
            this.$load(async () => {
              this.comps = (await this.$api.subject.readSubjectItems(data, 0)).data
              this.comps.forEach(comp => {
                    if (data === "teachers") {
                      comp.selectedName = `${comp.name} ${comp.surname}`
                    } else {
                      comp.selectedName = comp.name
                    }
                  }
              )
            })
          }
          if (data && data === "startsTime") {
            this.$load(async () => {
              this.comps = (await this.$api.time.readStartsTime()).data
            })
          }
          return false
        }
      } else {
        return true
      }
    },
    checkImage(value){
      return typeof value === 'string' && value.includes("data:");

    }
  },
  props: {
    raw: {
      type: Array,
      required: true,
    },
    payload: {
      type: Object,
      required: true,
    },
    errorMessage: [String],
    buttonClass: {
      type: String,
      default:'updateButton',
    },
  },
  watch:{
    payload: {
      handler(value) {
        if(value.headerImg === null){
            this.isImgNull = !this.isImgNull
        }
      },
      deep: true
    }
  },
  computed:{
    isUpdatePage(){
      console.log(this.buttonClass === "updateButton")
      return this.buttonClass === "updateButton";
    }
  }
}
</script>

<style scoped>

.raw {
  font-size: 1.3rem;
  display: flex;
  justify-content: center;
}

.raw__item {
  margin: 0 10px;
  height: 90px;
  padding: 20px;
  flex: 1 1 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.raw__select {
  background: #BBFFA7;
  color: #BBFFA7;
  border: 1px solid white;
  border-radius: 10px;
  margin-bottom: 5px;
}
.raw__select_with_width{
  width: 338px;
}

.raw__input {
  width: 100%;
  margin-bottom: 5px;
}

.raw__name {
  margin: 0px 5px 0px;
}

.name__empty {
  color: #FF1D30;
}
.raw__img{
  margin: 0 auto;
}
.raw__img-div{
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.name__exist {

}
.raw__img{
  height: 50px;
}
.raw__error {
  color: #FF1D30;
  font-size: 1rem;
}

@media (max-width: 800px) {
  .raw {
    font-size: 1.2rem;
  }

  .raw__item {
    padding: 15px;
    height: 60px;
  }
}

@media (max-width: 450px) {
  .raw {
    font-size: 1rem;
  }

  .raw__item {
    height: 50px;
    padding: 7px;
  }
}

</style>