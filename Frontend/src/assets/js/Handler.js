const emailReg = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
const letterReg = /\p{L}/u
const numbersReg = /[0-9]/
const telReg = /.{18,}/
const letterNumberReg = /^[a-zA-Z0-9_.-]*$/
const timeReg = /^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/
const emptyErrorMessage = "Fill this field"
const tooLongTextError = "Text is too long"


let handlers = new Map([
    ["email", emailHandler],
    ["time", timeHandler],
    ["course", courseHandler],
    ["classroom", stringNumberHandler],
    ["password", passwordHandler],
    ["phone", phoneHandler],
    ["surname", stringHandler],
    ["shortName", stringNumberHandler],
    ["date", dateHandler],
])

export function selectHandler(name, text, currentError) {
    try {
        if ((currentError === emptyErrorMessage) && !Boolean(text)) {
            throw currentError
        }
        let func = handlers.get(name)
        if (func) {
            func(text)
        }
    } catch (err) {
        return err
    }
    return ""
}

export function selectSubmitHandler(name, text, emptyPermissions) {
    try {
        if(!emptyPermissions.includes(name)){
            emptyErrorHandler(text)
        }
        let func = handlers.get(name)
        if (func) {
            func(text)
        }
    } catch (err) {
        return err
    }
    return ""

}

function emailHandler(email) {
    if (email && !email.match(emailReg)) {
        throw "Email is incorrect"
    }

}

function passwordHandler(password) {
    if (password && password.length < 8) {
        throw "Password has to contain minimum 7 characters"
    }
}

function phoneHandler(tel) {
    if (tel && !tel.match(telReg)) {
        throw "Please finish entering the phone number"
    }
}

function stringHandler(name) {
    if (name && numbersReg.test(name) && name.match(letterNumberReg)) {
        throw "Numbers  are forbidden"
    }
    let letterCheck = true;
    if(name){
        [...name].forEach(letter => {
            if (!letterReg.test(letter)) {
                letterCheck = false
            }
        })
    }
    if (name && !letterCheck) {
        throw "Such characters are forbidden"
    }
}

function stringNumberHandler(name) {
    let letterCheck = true;
    if(name){
        [...name].forEach(letter => {
            if (!letterReg.test(letter)) {
                letterCheck = false
            }
        })
    }
    if (name && !letterCheck && !letterNumberReg.test(name)) {
        throw "Such characters are forbidden"
    }
}

function dateHandler(date) {
    if (date && new Date() < new Date(date)) {
        throw "Please reduce date"
    }
}

function timeHandler(time) {
    if (time && !timeReg.test(time)) {
        throw "Time is incorrect"
    }
}

function lengthHandler(text) {
    if (text.length > 25) {
        throw tooLongTextError
    }
}

function emptyErrorHandler(text) {
    if (!Boolean(text)) {
        throw emptyErrorMessage
    }
}

function courseHandler(text){
    if (text && !/[1-6]/.test(text) && text.length === 1) {
        throw "Course should be between 1 and 6"
    }
}

