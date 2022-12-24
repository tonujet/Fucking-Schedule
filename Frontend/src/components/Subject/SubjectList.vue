<template>
  <div class="subject__items">
    <div class="subject__load" v-if="!isLoaded">
      <fa
          :icon="['fas', 'spinner']"
          spin
          size="3x"
          style="color: white"
          class="news__icon"
      />
    </div>
    <transition-group name="list" v-else>
      <subject-item
          v-for="(item, index) in items"
          :key="item.id"
          :fieldSet="fieldSet"
          :item="item"
          :img="imgs[index]"
          class="subject__item"
          @deleteItem="deleteItem"
      />
    </transition-group>
  </div>
</template>

<script>
import SubjectItem from "@/components/Subject/SubjectItem.vue";

export default {
  name: "SubjectList",
  props: {
    items: {
      type: Array,
      required: true,
    },
    fieldSet: {
      type: Array,
      required: true
    },
    isLoaded:{
      type:Boolean,
      default:false
    },
    imgs:[Array],
  },
  components: {
    SubjectItem
  },
  methods:{
    deleteItem(id){
      this.$emit("deleteItem", id)
    }
  }
}
</script>

<style scoped>
.subject__items {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

.subject__item {
  flex: 0 1 30%;
  margin: 10px 10px 20px;
}
.subject__load{
  margin: 130px auto;
}
.list-move,
.list-enter-active,
.list-leave-active {
  transition: all 0.2s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateY(50px);
}
</style>