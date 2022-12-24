import {createRouter, createWebHistory} from "vue-router"
import Rozklad from "@/pages/Rozklad.vue";
import News from "@/pages/News.vue";
import Data from "@/pages/Data.vue";
import ErrorPage from "@/pages/ErrorPage.vue";
import SubjectInfo from "@/pages/SubjectInfo.vue";
import UpdatePage from "@/pages/UpdatePage.vue";
import CreatePage from "@/pages/CreatePage.vue";


const routes = [
    {
        path: "/rozklad",
        component: Rozklad,
        alias: ["", "/"]
    },
    {
        path: "/news",
        component: News,
    },
    {
        path: "/data",
        component: Data,
    },
    {
        path: "/data/schedules/update/:id",
        alias: ["/data/disciplines/update/:id", "/data/groups/update/:id",
            "/data/students/update/:id", "/data/teachers/update/:id", "/data/departments/update/:id",
            "/data/mews/update/:id", "/data/faculties/update/:id","/data/news/update/:id"],
        component: UpdatePage,
    },
    {
        path: "/data/schedules/create",
        alias: ["/data/disciplines/create", "/data/groups/create",
            "/data/students/create", "/data/teachers/create", "/data/departments/create",
            "/data/mews/create", "/data/faculties/create","/data/news/create"],
        component: CreatePage,
    },
    {
        path: "/:pathMatch(.*)*",
        alias: ["/error"],
        component: ErrorPage,
    },
    {
        path: "/data/schedules",
        alias: ["/data/students", "/data/disciplines", "/data/groups",
            "/data/teachers", "/data/departments",
            "/data/mews", "/data/faculties","/data/news"],
        component: SubjectInfo
    }
]

const router = createRouter({
    routes,
    history: createWebHistory()
})
export default router