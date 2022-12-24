<template>
  <div class="news__blocks">
    <div class="news__load" v-if="!isLoaded">
      <fa
          :icon="['fas', 'spinner']"
          spin
          size="3x"
          style="color: white"
          class="news__icon"
      />
    </div>
    <div v-show="isLoaded" class="list">
      <transition-group name="list">
        <news-info
            class="news__block"
            v-for="item in news"
            :key="item.id"
            :item="item"
        />
      </transition-group>
      <div ref="observer">

      </div>
    </div>

  </div>

</template>

<script>
import newsInfo from "@/components/NewsComp/NewsInfo.vue";

export default {
  components: {
    newsInfo,
  },
  emits: ["load"],
  name: "NewsBlocks",
  props: {
    news: {
      type: Array,
      default: () => []
    },
    isLoaded: {
      type: Boolean,
      default: false,
    },
    searchQuery:{
      type:String,
      required:true
    }
  },
  mounted() {


  },
  watch: {
    isLoaded(value) {
      let options = {
        rootMargin: '0px',
        threshold: 1.0
      }
      const callback = (entries, observer) => {
        if (entries[0].isIntersecting) {
          this.$emit("load")
        }
      }
      if (value && !this.searchQuery) {
        let observer = new IntersectionObserver(callback, options);
        observer.observe(this.$refs.observer)
      }
    }
  }
}
</script>

<style scoped>
.news__load {
  margin: 22vmin auto;
  text-align: center;
}

.news__icon {
  margin: 0 auto;
}

.news__block {
  margin: 0 10px 20px;
  flex: 1 1 48.65%;
}

.news__blocks {
}
.list{
  display: flex;
  flex-wrap: wrap;
}
.list-move,
.list-enter-active,
.list-leave-active {
  transition: all 0.7s ease;
}

.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateY(50px);
}

@media (max-width: 700px) {
  .news__block {
    flex: 1 1 auto;
    margin: 0 5px 20px;
  }
}
</style>