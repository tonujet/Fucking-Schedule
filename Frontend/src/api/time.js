export default function (instance) {
    return {
        readStartsTime(){
            return instance.get(`time/startsTime`)
        },
        readFullTime(){
            return instance.get(`time/fulltime`)
        },
        readBreakTime(){
            return instance.get("time/breakTime")
        }
    }
}