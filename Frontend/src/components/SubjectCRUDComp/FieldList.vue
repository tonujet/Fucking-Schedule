<template>
  <div class="component">
    <div class="list" v-if="isLoaded">
      <div class="list__container">
        <list-raw
            v-for="(raw, index) in raws"
            :raw="raw"
            class="list__raw"
            :class="`elem${index+1}`"
            :payload="payload"
            :errorMessage="errorMessages[raw[0]]"
            :buttonClass="buttonClass"
            @enter="createUpdateItem"
        />
      </div>
      <custom-button
          class="list__button"
          :class="[{shake: !isInvalid}, buttonClass]"
          @click="createUpdateItem"
      >
        {{ buttonClass === "updateButton" ? "Зберегти" : "Створити" }}
      </custom-button>
    </div>
    <div class="spinner" v-else>
      <fa
          :icon="['fas', 'spinner']"
          spin
          size="3x"
          style="color: white"
          class="spinner__icon"
      />
    </div>
  </div>
</template>

<script>
import {selectSubmitHandler, selectHandler} from "@/assets/js/Handler";
import ListRaw from "@/components/SubjectCRUDComp/ListRaw.vue";

export default {
  name: "FieldList",
  components: {
    ListRaw
  },
  props: {
    item: {
      type: Object,
      default: {}
    },
    raws: {
      type: Array,
      required: true,
    },
    buttonClass: {
      type: String,
      default: 'updateButton'
    },
    isLoaded:{
      type:Boolean,
      required: true,
    },

  },
  data() {
    return {
      payload: {},
      errorMessages: {},
      img:null,
      isInvalid:false
    }
  },
  mounted() {
    for (let i = 1; i < this.raws.length; i++) {
      if (this.raws[i][0] !== "id") {
        this.payload[this.raws[i][0]] = null
        this.errorMessages[this.raws[i][0]] = ""
      }
    }
  },
  methods: {
    createUpdateItem() {
      this.isInvalid = true
      if (this.validateForm()) {
        setTimeout(() =>{
          this.isInvalid = false
        },100)
        return
      }
      const id = this.raws[1][1]
      const name = this.$store.state.subject.subjectName
      const payloadCopy = this.deleteUnnecessaryFields();
      let img = null;
      if (payloadCopy.headerImg) {
        img = payloadCopy.headerImg
        delete payloadCopy.headerImg
      }
      if (this.buttonClass === 'updateButton') {
        payloadCopy.id = id;
        this.$load(async () => {
          if(img){
            this.$load(async () => {
              await this.$api.subject.updateSubjectItem(name, payloadCopy)
              this.$load(async () => {
                const formData = new FormData();
                formData.append("file", img)
                await this.$api.img.updateImage(payloadCopy.id, formData)
              })
            })
          }else{
            await this.$api.subject.updateSubjectItem(name, payloadCopy)
          }
          this.$emit("load")
        })
      } else {
        if (img) {
          let itemId;
          this.$load(async () => {
            itemId = (await this.$api.subject.createSubjectItem(name, payloadCopy)).data
            this.$load(async () => {
              const formData = new FormData();
              formData.append("file", img)
              await this.$api.img.uploadImage(itemId, formData)
            })
          })
        } else {
          this.$load(async () => {
            await this.$api.subject.createSubjectItem(name, payloadCopy)
          })
        }
        this.clearItem()

      }
    },
    validateForm() {
      let errorFlag = false
      for (const param in this.errorMessages) {
        let error = selectSubmitHandler(param, this.payload[param], this.emptyPermissions)
        if (error) {
          this.errorMessages[param] = error
          errorFlag = true
        }
      }
      return errorFlag
    },
    clearItem() {
      for (const key in this.payload) {
        this.payload[key] = null;

      }
      console.log(this.payload)
    },
    setStartFields() {
      if (this.buttonClass === 'updateButton') {
        for (let i = 2; i < this.raws.length; i++) {
          if (this.raws[i][1] !== "Пусто") {
            const key = this.raws[i][0]
            this.payload[key] = this.item[key]
            if (typeof this.payload[key] === "object") {
              this.payload[key].selectedName = this.payload[key].name
            }

          }

        }
      }
    },

    deleteUnnecessaryFields() {
      const payloadCopy = {...this.payload}
      for (const key in payloadCopy) {
        if (!key && typeof key !== "boolean") {
          delete payloadCopy[key]
        }
      }
      return payloadCopy;
    }
  },
  watch: {
    payload: {
      handler(e) {
        for (const param in this.errorMessages) {
          this.errorMessages[param] = selectHandler(param, e[param], this.errorMessages[param])
        }
      },
      deep: true
    },
    raws: {
      handler(e) {
        for (let i = 1; i < this.raws.length; i++) {
          if (this.raws[i][0] !== "id") {
            this.payload[this.raws[i][0]] = null
            this.errorMessages[this.raws[i][0]] = ""
          }
        }
        console.log(this.raws)
        this.setStartFields()
      },
      deep: true
    }
  },
  computed: {
    emptyPermissions() {
      const permissions = [];
      this.raws.forEach(raw => {
        if (raw[raw.length - 1].nullable) {
          permissions.push(raw[0])
        }
      })
      return permissions;
    }
  }

}
</script>

<style scoped>
.list {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  overflow: auto;

}

.list__container {
  overflow: auto;
}

.list__raw {
  min-width: 470px;
  color: #C1FF9D;
  margin-bottom: 15px;
}

.list__button {
  align-self: center;
  font-size: 2rem;
}

.elem1 {
  font-size: 1.6rem;
  color: #FF858F;
  text-align: center;
}
.spinner{
  text-align: center;
  margin: 19vh;
}

@media (max-width: 700px) {
  .list__button {
    font-size: 1.5rem;
  }
}

@media (max-width: 450px) {
  .list__button {
    font-size: 1.2rem;
    padding: 5px;
  }

  .elem1 {
    font-size: 1.1rem;
  }
}
.shake {
  animation: shake 0.4s ease both;
  transform: translate3d(0, 0, 0);
}

@keyframes shake {
  10%,
  90% {
    transform: translate3d(-3px, 0, 0);
  }

  20%,
  80% {
    transform: translate3d(6px, 0, 0);
  }

  30%,
  50%,
  70% {
    transform: translate3d(-9px, 0, 0);
  }

  40%,
  60% {
    transform: translate3d(12px, 0, 0);
  }
}
</style>