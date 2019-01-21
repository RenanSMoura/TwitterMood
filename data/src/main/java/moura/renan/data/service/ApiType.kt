package moura.renan.data.service

sealed class ApiType {
    data class TwitterAPI(
        val apiKey: String,
        val apiSecret: String,
        val authTokenSource: AccessTokenDataSource
    ) : ApiType()

    data class GoogleApi(val authTokenSource: AccessTokenDataSource) : ApiType()
}

interface AccessTokenDataSource {

    fun getTwitterAccessToken(): String?


    fun getGoogleAccessToken(): String?
}