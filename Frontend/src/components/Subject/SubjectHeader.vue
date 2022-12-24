<template>
  <div class="header">
    <div class="header__raw raw1">
      <query-sequence
          class="header__seq"
          :query-arr="queryArr"
      />
      <search
          class="header__search"
          v-model.trim="searchQuery"
          placeholder="Search"
      />
    </div>
    <div class="header__raw raw2">
      <filter-select
          class="header__select"
          :options="sortParamOptions"
          v-model="sortParam"
      />
      <router-link
          :to="this.$route.path+'/create'"
          class="header__link"
      >
        <fa
            :icon="['fas','square-plus']"
            size="5x"
            class="header__icon"
        />
      </router-link>
    </div>
  </div>
</template>

<script>
import QuerySequence from "@/components/SubjectCRUDComp/QuerySequence.vue";
export default {
  name: "SubjectHeader",
  components: {
    QuerySequence,
  },
  data() {
    return {
      sortParam:"",
      searchQuery: "",
      queryArr: [
        {
          name: "data",
          isURL: true
        },
        {
          name: this.subjectName,
          isURL: false
        },
      ],

    }
  },
  props: {
    subjectName: [String],
    sortParamOptions:[Array],
  },
  watch:{
    searchQuery(value){
      this.$emit("search", value)
    },
    sortParam(value){
      this.$emit("setSortParam", value)
    }
  }
}
</script>

<style scoped>
.header {
  display: flex;
}

.header__raw {
  display: flex;
  align-items: center;

}

.raw1 {
  flex: 1 1 auto;
  flex-wrap: wrap;
  margin-right: 20px;

}

.raw2 {
  flex: 0 0 20.5%;
  justify-content: space-between;
}

.header__link {
  align-self: center;
}
.header__select{
  margin-right: 15px;
}
.header__seq {
  align-self: center;
  color: #FF533A;
  font-size: 2rem;
  text-transform: uppercase;
  flex: 0 1 38%;
}

.header__search {
  width: 52%;
  font-size: 1.8rem;

}

.header__icon {
  color: wheat;
  transition: 0.3s;
}
.header__icon:hover{
  color: #F5BE71;
}
.header__icon:active{
  color: white;
}

@media (max-width: 1300px) {
  .header__seq{
    margin-left: 5px;
  }
  .header__search{
    width: 70%;
  }
  .raw2{
    flex-direction: row-reverse;
    flex-wrap: wrap;
    justify-content: center;
  }
  .header__select{
    order: 2;
    margin-right: 0;
  }
  .header__link{
    text-align: center;
    flex: 1 1 100%;
    order: 1;
    margin-bottom:10px ;
  }
}
@media (max-width: 900px) {
  .search{
    width: 90%;
    font-size: 1.5rem;
  }
}
@media (max-width: 800px) {
  .header__seq{
    margin-bottom: 15px;
  }

}
@media (max-width: 520px) {
  .header{
    flex-wrap: wrap;
    justify-content: center;
  }
  .header__seq{
    font-size: 1.7rem ;
    margin-bottom: 10px;
  }
  .raw1{
    margin-bottom: 10px;
    margin-right: 0;
  }
  .raw2{
    flex: 1 1 auto;
    flex-wrap: nowrap;
    justify-content: space-between;
  }
  .header__select{
    align-self: flex-end;
    margin-bottom: 15px;
  }
  .select{
    border-radius: 20px;
  }
  .header__link{
    flex: 0 0 auto;
  }
  .search{
    width: 100%;
  }
}
</style>