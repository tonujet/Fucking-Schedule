import instance from "@/api/instance";
import subjectModule from "@/api/subject";
import scheduleDaysModule from "@/api/scheduleDays";
import timeModule from "@/api/time";
import imageModule from "@/api/image";

export default {
    subject: subjectModule(instance),
    scheduleDays: scheduleDaysModule(instance),
    time:timeModule(instance),
    img:imageModule(instance),
}