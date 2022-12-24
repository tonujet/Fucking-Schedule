export default function (instance) {
    return {
        downloadImage(id){
            return instance.get(`img/${id}` , {responseType:"blob"})
        },
        uploadImage(newsId, payload ){
            return instance.post(`img/create?newsId=${newsId}`, payload)
        },
        updateImage(newsId, payload){
            return instance.post(`img/update?newsId=${newsId}`, payload)
        },
    }
}