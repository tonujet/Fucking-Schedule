<template>
  <div class="data">
    <div class="data__blocks">
      <first-layer-block
          v-for="subject in subjects"
          @click="loadSubject($event, subject)"
          class="data__block">
        {{ subject }}
      </first-layer-block>
    </div>
  </div>
</template>

<script>
import {mapActions, mapMutations} from "vuex"

export default {
  name: "Data",
  data() {
    return {
      subjects: [
        "schedules",
        "disciplines",
        "groups",
        "students",
        "teachers",
        "departments",
        "news",
        "faculties"
      ]
    }
  },
  methods: {
    ...mapMutations({
      setSubject: "subject/setSubjectName",
      setLimit: "subject/setLimit"
    }),
    loadSubject(e, subject) {
      this.setSubject(subject)
      this.setLimit(0)
      localStorage.setItem("subject", subject)
      this.$router.push('/data/'.concat(subject));
    }
  },
  created() {
    this.$store.dispatch("subject/fetchSubjectColumnNames")
  }
}


</script>

<style scoped>
.data {
  padding: 10px 20px;
}

.data__block {
  font-size: 2.5rem;
  text-transform: capitalize;
  color: wheat;
  flex: 1 1 40%;
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 10px;
  border-radius: 20px;
  cursor: pointer;
  padding: 5px;

}

.data__blocks {
  display: flex;
  flex-wrap: wrap;
}

.data__block:hover {
  background: #27292E;
}

.data__title {
  font-size: 3rem;
  color: white;
  text-align: center;
  margin-bottom: 10px;
}

@media (max-width: 600px ) {
  .data {
    padding: 10px 5px;
  }

  .data__block {
    flex: 1 1 100%;
    height: 150px;
    font-size: 2.2rem;
  }
}
</style>