<template>
  <div class="creation">
    <c-r-u-d-page-header
        class="creation__header"
        :query-arr="queryArr"
        :icon="icon"
    />
    <field-list
        class="creation__list"
        :raws="raws"
        :button-class="'addButton'"
        :isLoaded="isLoaded"
    />
  </div>
</template>

<script>
import CRUDPageHeader from "@/components/SubjectCRUDComp/CRUDPageHeader.vue";
import FieldList from "@/components/SubjectCRUDComp/FieldList.vue";
import {useRoute} from "vue-router";

export default {
  name: "CreatePage",
  components: {
    CRUDPageHeader, FieldList
  },

  data() {
    return {
      item: {},
      raws: [],
      columnsName: [
        "Назва поля", "Значення",
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
          name: "create",
          isURL: false
        },
      ],
      icon: {
        name: "square-plus",
        prefix: "fas",
        size: "5x",
        class: "createIcon"
      },
      isLoaded:false,
    }
  },

  created() {
    this.$store.dispatch("subject/fetchSubjectColumnNames")
    if (!useRoute().path.match(this.$store.state.subject.subjectName)) {
      this.$store.commit("error/setMessage", `Incorrect url, must to be ' data/${this.$store.state.subject.subjectName}/ ', but not ' ${useRoute().path} '`)
      this.$router.push("/error")
    }
    setTimeout(() =>{
      this.isLoaded = true
      this.raws = this.calcRaws();

    }, 500)
    console.log(this.raws)


  },
  methods: {
    calcRaws() {
      const raws = []
      raws.push(this.columnsName)
      for (const field of this.$store.state.subject.subjectColNames) {
        let raw
        if (field.type === "String") {
          const element = {
            type: `$input`,
            nullable: field.nullable,
          }
          raw = [field.name, element]
        } else {
          const element = {
            type: `$${field.type}`,
            data: field.data,
            nullable: field.nullable,
          }
          raw = [field.name, element]
        }
        raws.push(raw)
      }
      return raws
    }
  },
  watch: {

  },
}
</script>

<style scoped>
.creation {
  margin: 15px;
}

@media (max-width: 400px) {
  .creation {
    margin: 5px;
  }
}
</style>