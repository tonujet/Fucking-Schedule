export default {
    schedules: [
        {name: "name", type: "String", nullable:false},
        {name: "teacher", type: "ENUM", data:"teachers",nullable:true},
        {name: "discipline", type: "ENUM", data:"disciplines", nullable:true},
        {
            name: "dayOfWeek",
            type: "ENUM",
            data: "daysOfWeek",
            nullable:true
        },
        {name: "time", type: "String",nullable:true},
        {name: "classroom", type: "String", nullable:true},
        {name: "group", type: "ENUM", data: "groups", nullable:true}
    ],
    disciplines: [
        {name: "name", type: "String", nullable:false}
    ],
    groups: [
        {name: "name", type: "String", nullable:false},
        {name: "department", type: "ENUM", data:"departments",nullable:true},
        {name: "course", type: "String", nullable:true},
    ],
    students: [
        {name: "name", type: "String", nullable:false},
        {name: "group", type: "ENUM", data: "groups",nullable:true},
        {name: "email", type: "String", nullable:false},
        {name: "phone", type: "String", nullable:false},
    ],
    teachers: [
        {name: "name", type: "String", nullable:false},
        {name: "surname", type: "String", nullable:false},
        {name: "email", type: "String", nullable:false},
        {name: "phone", type: "String", nullable:false},
    ],
    departments: [
        {name: "name", type: "String", nullable:false},
        {name: "short name", type: "String", nullable:false},
        {name: "faculty", type: "ENUM", data: "faculties", nullable:true},
    ],
    news: [
        {name: "title", type: "String",nullable:false},
        {name: "description", type: "String", nullable:false},
        {name: "text", type: "Text", nullable:false},
        {name: "date", type: "Date", nullable:false},
        {name: "imgSrc", type: "String", nullable:false},
    ],
    faculties: [
        {name: "name", type: "String", nullable:false},
        {name: "short name", type: "String", nullable:false},
    ],
}