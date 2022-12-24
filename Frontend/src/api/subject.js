export default function (instance) {
    return {
        readSubjectItems(name, limit) {
            return instance.get(`${name}?limit=${limit}`)
        },
        readSubjectItem(name, id) {
            return instance.get(`${name}/${id}`)
        },
        searchSubjectItems(name, limit, query) {
            return instance.get(`${name}/search?query=${query}&limit=${limit}`)
        },
        deleteSubjectItem(name, id) {
            return instance.delete(`${name}/delete/${id}`)
        },
        updateSubjectItem(name, payload) {
            return instance.put(`${name}/update`, payload)
        },
        createSubjectItem(name,payload) {
            return instance.post(`${name}/create`, payload)
        },
        readOptions() {
            return instance.get(`options`)
        },
    }
}