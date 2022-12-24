export default {
    state: () =>({
      message:null,
    }),
    getters:{

    },
    mutations:{
        setMessage(state, message){
            state.message = message
        }
    },
    actions:{

    },
    namespaced:true
}