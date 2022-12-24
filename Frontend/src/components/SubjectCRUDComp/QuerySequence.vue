<template>
  <div class="raw">
    <div
        class="raw__item"
        v-for="(query, index) in queryArr"
    >
      <router-link
          v-if="query.isURL"
          :to="query.name==='data'?'/data':'/data/'+query.name"
          class="raw__link"
          :class="'link'+(index+1)"
      >
        {{ query.name }}
      </router-link>
      <div
          v-else
          :class="'link'+(index+1)"
      >
        {{query.name}}
      </div>
      <div
          class="raw__slash"
          v-if="queryArr.length !== index+1"
      >
        /
      </div>
    </div>

    <!--    <div class="row__item item1">-->
    <!--      <router-link to="/data" class="row__link link1">DATA</router-link>-->
    <!--    </div>-->
    <!--    <div class="row__slash">/</div>-->
    <!--    <div class="row__item item2">-->
    <!--      <router-link :to="subjectUrl" class="row__link link2">{{ subject }}</router-link>-->
    <!--    </div>-->
    <!--    <div class="row__slash">/</div>-->
    <!--    <div class="row__item item3">UPDATE</div>-->
    <!--    <div class="row__slash">/</div>-->
    <!--    <div class="row__item item4">ID={{ this.item.id }}  </div>-->
  </div>
</template>

<script>
import {useRoute} from "vue-router";

export default {
  name: "QuerySequence",
  props: {
    item: [Object],
    queryArr: [Array],
  },
  computed: {

  }
}
</script>

<style scoped>
.raw {
  display: flex;
  color: wheat;
  font-size: 2rem;
  align-items: center;
  text-transform: uppercase;
}

.raw__slash {
  font-size: 4rem;
  margin: 0 7px;
  color: white;
}

.raw__item {
  display: flex;
  align-items: center;
}

.raw__link:hover {
  color: #FF1D30;
}

.raw__link:active {
  color: red;
}

.link1 {
  color: #FF3538;
}

.link2 {
  color: #FF4A4D;
}

.link3 {
  color: #FF7674;
}

.link4 {
  color: #FF858F;
}
@media (max-width: 500px) {
  .raw__slash{
    font-size: 2rem;
  }
}
</style>