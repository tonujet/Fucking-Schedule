export default function (instance) {
    return {
        readFullSchedule(id){
            return instance.get(`/schedules/full/${id}`)
        }
    }
}