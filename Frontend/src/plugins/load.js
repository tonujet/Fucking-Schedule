export default {
    install(app) {
        app.config.globalProperties.$load = async (action, errorHandler) => {
            try {
                await action()
            } catch (error) {
                if (errorHandler) {
                    errorHandler()
                } else {
                    app.config.globalProperties.$router.push("/error")
                    app.config.globalProperties.$store.commit("error/setMessage", error.response.data)
                    console.error(error.response.data)
                }
            }
        }
    }
}