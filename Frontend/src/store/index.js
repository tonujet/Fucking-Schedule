import {createStore} from "vuex"
import SubjectInfoModule from "@/store/SubjectInfoModule";
import UserModule from "@/store/UserModule";
import ErrorModule from "@/store/ErrorModule";

export default createStore({
    modules:{
        subject: SubjectInfoModule,
        user: UserModule,
        error: ErrorModule
    }
})