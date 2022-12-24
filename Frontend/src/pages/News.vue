<template>
  <div class="news">
    <news-header
        class="news__header"
        :sortParams="sortParams"
        @search="setQuery"
        @sort="setSortParam"
    />
    <news-blocks
        class="news__blocks"
        :news="sortedAndSearchNews"
        :isLoaded="isLoaded"
        :search-query="searchQuery"
        @load="fetchNews"
    />
  </div>
</template>

<script>
import NewsInfo from "@/components/NewsComp/NewsInfo.vue";
import NewsBlocks from "@/components/NewsComp/NewsBlocks.vue";
import NewsHeader from "@/components/NewsComp/NewsHeader.vue";

export default {
  name: "News",
  components: {
    NewsInfo, NewsBlocks, NewsHeader
  },
  methods: {
    setQuery(query) {
      this.searchQuery = query
    },
    setSortParam(sortParam) {
      this.sortParam = sortParam
    },
    fetchNews() {
      setTimeout(() =>{
        this.$store.dispatch("subject/fetchSubjectItems")
      }, 200)
      setTimeout(() =>{
        this.isLoaded = true
      }, 1000)
    },
  },
  data() {
    return {
      isLoaded: false,
      searchQuery: "",
      sortParams: ["oldDateFirst", "lastDateFirst"],
      sortParam: "oldDateFirst",
      observer:null
    }
  },
  props: {
    type: Array
  },
  computed: {
    sortedAndSearchNews() {
      let newsArr = [...this.$store.state.subject.subjectItems]
      if (this.sortParam === this.sortParams[1]) {
        newsArr = newsArr.sort((a, b) => {
          return new Date(b.dateCreated) - new Date(a.dateCreated)
        })
      } else {
        newsArr = newsArr.sort((a, b) => {
          return new Date(a.dateCreated) - new Date(b.dateCreated)
        })
      }
      return newsArr
    },
  },
  mounted() {
    this.$store.commit("subject/setSubjectName", "news")
    this.$store.commit("subject/setLimit", 0)
    this.fetchNews();

  },
  watch:{
    async searchQuery(value){
      if(value){
        await this.$store.dispatch("subject/fetchWithSearchWordForItems", value)
      } else{
        await this.fetchNews()
      }
    }
  }
}
</script>

<style scoped>
.news {
  padding: 30px;
}

.news__header {
  margin: 0 10px 20px;
}

@media (max-width: 700px) {
  .news {
    padding: 10px;
  }
}


</style>