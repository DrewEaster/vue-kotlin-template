class ApiConfig {
    
    apiUriBase() {
        return window.config.api.uriBase
    }
}

const apiConfig = new ApiConfig()

export default apiConfig