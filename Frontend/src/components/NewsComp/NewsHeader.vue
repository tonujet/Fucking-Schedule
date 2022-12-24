<template>
  <div class="news__header">
    <div class="news__item">
      <search
          class="news__search"
          v-model="searchQuery"
          placeholder="Search"
      />
    </div>

    <down-arrow
        class="news__arrow"
        :sortParams="sortParams"
        @sort="$emit('sort', $event)"
    >
      Дата
    </down-arrow>
  </div>
</template>

<script>
import DownArrow from "@/components/UI/DownArrow.vue";
export default {
  name: "NewsHeader",
  components: {DownArrow},
  data() {
    return {
      searchQuery: "",
    }
  },
  watch:{
    searchQuery(query){
      this.$emit("search", query)
    }
  },
  props:{
    sortParams:[Array],
  }

}
</script>

<style scoped>
.news__header{
  display: flex;
  position: relative;
  height: 75px;
}
.news__search{
  margin: 0 auto;
  width: 600px;
}
.news__item{
  flex: 1 1 auto;
  align-self: center;
  display: flex;
  flex-direction: column;
  align-items: center;

}
.news__arrow{
  position: absolute;
  top: 0;
  right: 20px;
}
@media (max-width: 600px) {
  .news__item{
    margin-right: 100px;
  }
  .news__search{
    font-size: 1rem;
  }

}
@media (max-width: 1000px) {
  .news__search{
    width: 100%;
  }
  .news__item{
    margin-right: 120px;
    display: block;
  }
}
</style>