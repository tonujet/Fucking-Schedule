<template>
  <div class="page">
    <c-r-u-d-page-header
        class="page__header"
        :query-arr="queryArr"
        :icon="icon"
    />
    <div class="page__container">
      <field-list
          class="page__list"
          :raws="raws"
          @load="fetch"
          :isLoaded="isLoaded"
          :item="item"
      />
    </div>

  </div>
</template>

<script>
import {useRoute} from "vue-router"
import CRUDPageHeader from "@/components/SubjectCRUDComp/CRUDPageHeader.vue";
import FieldList from "@/components/SubjectCRUDComp/FieldList.vue";
import weekDays from "@/mocks/WeekDays";

export default {
  name: "UpdatePage",
  components: {
    CRUDPageHeader, FieldList
  },
  data() {
    return {
      item: {},
      raws: [],
      isLoaded:false,
      columnsName: [
        "Назва поля", "Значення", "Нове значення"
      ],
      queryArr: [
        {
          name: "data",
          isURL: true
        },
        {
          name: this.$store.state.subject.subjectName,
          isURL: true
        },
        {
          name: "update",
          isURL: false
        },
        {
          name: `id=${useRoute().params.id}`,
          isURL: false
        }
      ],
      icon: {
        name: "pen-to-square",
        prefix: "fas",
        size: "5x",
        class: "updateIcon"
      },
      paramId:0,
      img:null
    }
  },
  methods: {
    calcRaws() {
      const raws = []
      raws.push(this.columnsName)
      raws.push(["id", this.item.id, ""])
      for (const field of this.$store.state.subject.subjectColNames) {
        let raw
        const fieldValue = this.item[field.name];
        if (typeof fieldValue == 'object') {
          if (!fieldValue) {
            raw = [field.name, "Пусто"]
          } else if (field.name === "teacher") {
            raw = [field.name, `${fieldValue.name} ${fieldValue.surname}`]
          } else {
            raw = [field.name, fieldValue.name]
          }
        } else {
          if (field.name === "dayOfWeek") {
            raw = [field.name, `${weekDays.filter(item => item.serverName === fieldValue)[0].uaName}`]
          } else if (field.name === "headerImg") {
            raw = [field.name, this.img]
          } else if(field.name === "dateCreated"){
            raw = [field.name, new Date(fieldValue).toLocaleString()]
          } else {
            raw = [field.name, fieldValue]
          }

        }
        if (field.type === "String") {
          const element = {
            type: `$input`,
            nullable: field.nullable,
          }
          raw.push(element)
        } else {
          const element = {
            type: `$${field.type}`,
            data: field.data,
            nullable: field.nullable,
          }
          raw.push(element)
        }
        raws.push(raw)
      }
      return raws;
    },
    async fetch(){
      // setTimeout(() =>{
      //
      // }, 300)
      this.$store.dispatch("subject/fetchSubjectColumnNames")
      this.item = null;
      if(useRoute()){
        this.paramId = useRoute().params.id;
      }
      await this.$load(async () => {
        this.item = (await this.$api.subject.readSubjectItem(this.$store.state.subject.subjectName, this.paramId)).data
        console.log(this.item)
      })
      if(this.item.headerImgId){
        this.isLoaded = false;
         this.$load(() => {
          this.$api.img.downloadImage(this.item.headerImgId)
              .then((response) => {
                const reader = new window.FileReader();
                reader.readAsDataURL(response.data);
                reader.onload = () => {
                  setTimeout(() =>{
                    this.img = reader.result
                    this.raws = this.calcRaws();
                  }, 200)
                  setTimeout(() =>{
                    this.isLoaded = true
                  }, 600)
                }
              });
        })
      }else{
        this.raws = this.calcRaws();
        this.isLoaded = true
      }

    }

  },

  created() {
    this.fetch()
    if (!useRoute().path.match(this.$store.state.subject.subjectName)) {
      this.$store.commit("error/setMessage", `Incorrect url, must to be ' data/${this.$store.state.subject.subjectName}/ ', but not ' ${useRoute().path} '`)
      this.$router.push("/error")
    }
  }
}
</script>

<style scoped>

.page {
  margin: 15px;
}

@media (max-width: 400px) {
  .page {
    margin: 5px;
  }
}
</style>