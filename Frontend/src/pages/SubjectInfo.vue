<template>
  <div class="subject">
    <subject-header
        class="subject__header"
        :subjectName="subjectName"
        :sortParamOptions="sortParamOptions"
        @search="setSearchQuery"
        @setSortParam="setSortParam"
    />
    <my-stripe
        class="subject__stripe"
    />
    <subject-list
        class="subject__body"
        :items="searchAndSortedItems"
        :fieldSet="subjectColNames"
        :is-loaded="isLoaded"
        :imgs="imgs"
        @deleteItem="deleteItem"
    />
    <div ref="observer" class="subject__observer">

    </div>
  </div>
</template>

<script>
import {mapState, mapMutations} from "vuex"
import SubjectHeader from "@/components/Subject/SubjectHeader.vue";
import SubjectList from "@/components/Subject/SubjectList.vue";

export default {
  name: "SubjectInfo",
  data() {
    return {
      isLoaded: false,
      searchQuery: "",
      sortParam: "default",
      sortParamOptions: [],
      selectMethodOfSorting: {
        "nameFirstLetters": this.sortItemsByFirstLettersOfName,
        "NameLastLetters": this.sortItemsByLastLettersIfName,
        "laterFirst": this.sortItemsByLaterTime,
        "earlierFirst": this.sortItemsByEarlierDate,
        "default": arr => arr
      },
      imgs: [],
    }
  },
  mounted() {
    this.fetchData()
      let options = {
        rootMargin: '0px',
        threshold: 1.0
      }
      const callback = (entries, observer) => {
        if (entries[0].isIntersecting) {
          this.fetchData();
        }
      }
      let observer = new IntersectionObserver(callback, options);
      observer.observe(this.$refs.observer)
  },

  components: {
    SubjectHeader, SubjectList
  },
  computed: {
    ...mapState({
      subjectName: state => state.subject.subjectName,
      subjectItems: state => state.subject.subjectItems,
      subjectColNames: state => state.subject.subjectColNames,
    }),

    searchAndSortedItems() {
      const itemsArr = [...this.subjectItems]
      const sort = this.selectMethodOfSorting[this.sortParam]
      return sort(itemsArr, this.selectMainSortField())
    },
  },
  watch: {
    async searchQuery(value) {
      if (value) {
        await this.$store.dispatch("subject/fetchWithSearchWordForItems", value)
      } else {
        await this.fetchData()
      }
    }
  },
  methods: {
    ...mapMutations({
      setSubjectItem: (commit, items) => commit("subject/setSubjectItems", items)
    }),
    async fetchData() {
      if(!this.searchQuery){
        await this.$store.dispatch("subject/fetchSubjectItems")
        setTimeout( () =>{
          this.loadImgs();
        }, 200)

        await this.$store.dispatch("subject/fetchSubjectColumnNames")


        this.sortParamOptions = this.setParamOptions()
        setTimeout(async () => {
          this.isLoaded = true
        }, 1000)
      }
    },
    loadImgs(){
      if (this.subjectItems[0] && this.subjectItems[0].headerImgId) {
        this.subjectItems.forEach(item => {
              this.$load(() => {
                this.img = this.$api.img.downloadImage(item.headerImgId)
                    .then((response) => {
                      const reader = new window.FileReader();
                      reader.readAsDataURL(response.data);
                      reader.onload = () => {
                        this.imgs.push(reader.result);
                      }
                    });
              })
            }
        )
      }
    },
    setSearchQuery(query) {
      this.searchQuery = query
    },
    setSortParam(param) {
      this.sortParam = param
    },
    setParamOptions() {
      const options = [
        {
          name: "За замовчуванням",
          value: "default"
        },
        {
          name: "За алфавітом(А-Я)",
          value: "nameFirstLetters"
        },
        {
          name: "За алфавітом(Я-А))",
          value: "NameLastLetters"
        }
      ]
      let isTimeExist = false
      this.$store.state.subject.subjectColNames.forEach(obj => {
        if (obj.name === "time" || obj.name === "date") {
          isTimeExist = true
        }
      })
      if (isTimeExist) {
        {
          options.push({
            name: "Раніші",
            value: "earlierFirst"
          })
          options.push({
            name: "Пізніші",
            value: "laterFirst"
          })
        }
      }
      return options
    },
    sortItemsByLaterTime(arr) {
      return arr.sort((a, b) => {
        return b.time.localeCompare(a.time)
      })
    },
    sortItemsByEarlierDate(arr) {
      return arr.sort((a, b) => {
        return a.time.localeCompare(b.time)
      })
    },
    sortItemsByFirstLettersOfName(arr, field) {
      return arr.sort((a, b) => {
        return a[field].localeCompare(b[field])
      })
    },
    sortItemsByLastLettersIfName(arr, field) {
      return arr.sort((a, b) => {
        return b[field].localeCompare(a[field])
      })
    },
    deleteItem(id) {
      this.setSubjectItem(this.subjectItems.filter(item => item.id !== id))
    },
    selectMainSortField(){
      if(this.subjectName === "news"){
        return "title"
      } else {
        return "name";
      }
    }
  }
}
</script>

<style scoped>
.subject {

}

.subject__header {
  margin: 20px 30px;
}

.subject__body {
  margin: 20px 50px;
}

.subject__stripe {
  height: 10px;
  margin-bottom: 20px;
}

@media (max-width: 450px) {
  .subject__header {
    margin: 12px;
  }
}

@media (max-width: 520px) {
  .subject__body {
    margin: 10px 30px;
  }
}

@media (max-width: 380px) {
  .subject__body {
    margin: 10px;
  }
}
</style>