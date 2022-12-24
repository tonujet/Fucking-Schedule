import subjectColNames from "@/mocks/subjectColNames";


export default {
    state: () => ({
        subjectName: "",
        subjectItems: [],
        subjectColNames: [],
        limit: 0
    }),
    getters: {

    },
    mutations: {
        setSubjectName(state, subjectName) {
            state.subjectName = subjectName
        },
        setSubjectItems(state, items) {
            state.subjectItems = items
        },
        setSubjectColNames(state, colNames) {
            state.subjectColNames = colNames
        },
        setLimit(state, limit) {
            state.limit = limit
        }
    },
    actions: {
        fetchSubjectItems({state, commit}) {
            state.limit+=6
            this.$load(async () => {
                commit("setSubjectItems", (await this.$api.subject.readSubjectItems(state.subjectName, state.limit)).data)
            })
            console.log(state.subjectItems)
        },
        fetchWithSearchWordForItems({state, commit}, query) {
            this.$load(async () => {
                commit("setSubjectItems", (await this.$api.subject.searchSubjectItems(
                    state.subjectName, state.limit, query)).data
                )
            })
        },
        fetchSubjectColumnNames({state, commit}) {
            this.$load(async () => {
                commit("setSubjectColNames", (await this.$api.subject.readOptions()).data[state.subjectName])
            })
        },

    },
    namespaced: true
}