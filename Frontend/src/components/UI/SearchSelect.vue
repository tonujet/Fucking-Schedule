<template>
  <v-select
      :options="options"
      :value="currentOption"
      class="search"
      style="background: #2f3136; border-radius: 10px"
  />
</template>

<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css"

export default {
  components: {
    vSelect
  },
  data() {
    return {}
  },
  props: {
    options: {
      type: Array,
      default: () => []
    },
    currentOption: {
      type: Object
    }
  },
  name: "SearchSelect",
  methods: {}
}

</script>

<style>
.search {
  width: 100%;
  --vs-search-input-color: #BEFF7D;
  --vs-controls-color: #BEFF7D;
  --vs-border-radius: 10px;
  --vs-font-size: 1.2rem;


  --vs-search-input-placeholder-color: #737373;
  --vs-dropdown-bg: #40444b;
  --vs-dropdown-color: #FF5B5B;
  --vs-dropdown-option-color: #BEFF7D;
  --vs-dropdown-option-padding: 8px 20px;
  --vs-actions-padding: 5px 20px 0px 0px;
  --vs-selected-bg: #40444b;
  --vs-selected-color: #BEFF7D;


  --vs-dropdown-option--active-bg: #2f3136;
  --vs-dropdown-option--active-color: #8DFF5C;
  --vs-transition-duration: 0.3s;
}
.vs__selected-options {
  flex-wrap: nowrap;
  max-width: calc(100% - 40px); /* change this to `- 40px` if you're supporting a `clearable` field; I was not */
}

.vs__selected {
  white-space: nowrap;
  overflow: hidden;
}



</style>