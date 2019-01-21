package moura.renan.data.service.twitter

import retrofit2.http.GET

interface TwitterService {


    @GET()
    fun getTimeLine()
}