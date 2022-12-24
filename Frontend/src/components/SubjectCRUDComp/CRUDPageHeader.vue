<template>
  <div class="header">
    <query-sequence
        class="header__seq"
        :query-arr="queryArr"
    />
    <div class="header__icon">
      <fa
          :icon="[icon.prefix, icon.name]"
          :size="pageWidth > 500 ? icon.size : '3x'"
          :class="icon.class"
      />
    </div>
  </div>
</template>

<script>
import QuerySequence from "@/components/SubjectCRUDComp/QuerySequence.vue";

export default {
  name: "ActPageHeader",
  components: {
    QuerySequence
  },
  data(){
    return{
      pageWidth: window.screen.width
    }
  },
  props: {
    queryArr: [Array],
    icon: [Object],
  },
  mounted() {
    window.addEventListener('resize', () => {
      this.pageWidth = window.screen.width
    })
  },


}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  padding: 0 15px 20px;
  transition: 0.5s;
}

.updateIcon {
  color: #46B900;
  transition: 0.3s;
}
.updateIcon:hover{
  color:#346805 ;
}
.updateIcon:active{
  color: #8DFF5C;
}
.createIcon {
  transition: 0.3s;
  color: wheat;
}
.createIcon:hover{
  color: #F5BE71;
}
.createIcon:active{
  color: white;
}

@media (max-width: 800px) {
  .header__seq {
    flex: 1 1 100%;
    font-size: 1.5rem;
  }

  .header__icon {
  }
}

@media (max-width: 500px) {
  .header__seq {
    font-size: 1.25rem;
  }
  .header{
    padding: 0 5px 10px;
  }
}
@media (max-width: 400px) {
  .header__seq {
    font-size: 1rem;
  }
}
@media (max-width: 350px) {
  .header__seq {
    font-size: 0.9rem;
  }
}

</style>