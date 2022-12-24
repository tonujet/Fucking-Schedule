import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router/router"
import UI from "@/components/UI";
import store from "@/store";
import icons from "@/components/Icons";
import blocks from "@/components/Blocks";
import {library} from "@fortawesome/fontawesome-svg-core";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import apiPlugins from "@/plugins/api.js";
import loadPlugin from "@/plugins/load.js";

icons.forEach(icon =>{
    library.add(icon)
})

const app = createApp(App)

UI.forEach(comp =>{
    app.component(comp.name, comp)
})
blocks.forEach(block =>{
    app.component(block.name, block)
})

app
    .use(loadPlugin)
    .use(apiPlugins)
    .use(router)
    .use(store)
    .component("fa", FontAwesomeIcon)
    .mount('#app')
store.$load = app.config.globalProperties.$load;
store.$api = app.config.globalProperties.$api;
