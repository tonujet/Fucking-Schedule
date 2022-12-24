<template>
  <first-layer-block class="item">
    <div class="item__body">
      <div
          class="item__field field"
          v-for="field in fieldSet"
      >
      <span class="field__name">
          {{ field.name }}:
      </span>
        <subject-value
            :item-name="field.name"
            :field="item[field.name]"
            :name="field.name"
            :item="item"
            :img="img"
        />
      </div>
    </div>
    <div class="item__icons">
      <fa
          :icon="['fas','trash']"
          size="2x"
          class="item__icon icon1"
          style=""
          @click="deleteItem"
      />
      <router-link :to="this.$route.path+'/update/'+this.item.id" class="item__link">
        <fa
            :icon="['fas','pencil']"
            size="2x"
            class="item__icon icon2"
        />
      </router-link>
    </div>
  </first-layer-block>
</template>

<script>
import SubjectValue from "@/components/Subject/SubjectValue.vue";

export default {
  name: "subjectItem",
  props: {
    item: [Object],
    fieldSet: {
      type: Array,
      required: true,
    },
    img:[String],
  },
  methods: {
    deleteItem() {
      this.$api.subject.deleteSubjectItem(this.$store.state.subject.subjectName, this.item.id)
      this.$emit("deleteItem", this.item.id)
    }
  },
  components: {
    SubjectValue,
  },
}

</script>

<style scoped>
.item {
  padding: 10px 15px;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  min-width: 250px;
  overflow-x: auto;
}

.item__body {
  flex: 1 1 auto;
}

.item__field {

}

.field {
  margin-bottom: 12px;
  font-size: 1.5rem;
}

.field__name {
  margin-right: 5px;
  text-transform: capitalize;
  color: #FFB5BA;
}

.item__icons {
  justify-content: space-between;
  display: flex;
}

.item__link {
  display: block;
}

.item__icon {

}

.icon1 {
  color: #FF533A;
  transition: 0.3s;
}

.icon1:hover {
  color: #FF3B0D;
}

.icon1:active {
  color: #FF0A00;
}

.icon2 {
  color: #46B900;
  transition: 0.3s;
}

.icon2:hover {
  color: #77FF06;
}

.icon2:active {
  color: #3F8E03;
}

@media (max-width: 400px) {
  .field {
    font-size: 1.3rem;
  }
}

</style>