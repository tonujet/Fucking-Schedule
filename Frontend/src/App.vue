<template>
  <div class="app">
    <div class="app__header">
      <My-header/>
    </div>
    <div class="app__content">
      <router-view v-slot="{Component}">
        <transition name="route" mode="out-in">
          <component :is="Component"></component>
        </transition>
      </router-view>
    </div>
    <div class="app_footer">
      <My-footer/>
    </div>
  </div>
</template>
<script>
import MyHeader from "@/components/My-header.vue";
import MyFooter from "@/components/My-footer.vue";
export default {
  components:{
    MyHeader, MyFooter
  },
  created() {
    this.$store.state.subject.subjectName = localStorage.getItem("subject")
  },

}
</script>
<style>
*{
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
html, body{
  height: 100%;
}
.app{
  display: flex;
  flex-direction: column;
  height: 100%;
}
.app__content{
  flex: 1 1 auto;
  background: #1e1e1e;
}
.app_footer{
  flex: 0 0 auto;
  padding: 5px;
  background: #2d2d30;
  background: #252526;
}
.app__header{
  background: #252526;
  background: #252526;
  padding: 10px 60px;
}
/*route transition*/
.route-enter-from{
  opacity: 0;
  transform: translateX(100px) ;
}
.route-enter-active{
  transition: all 0.3s ease-out;
}
.route-leave-to{
  opacity: 0;
  transform: translateX(-100px);
}
.route-leave-active{
  transition: all 0.3s ease-in;
}
@media (max-width: 1000px) {
  .app__header{
    padding: 10px 20px;
  }
}
</style>