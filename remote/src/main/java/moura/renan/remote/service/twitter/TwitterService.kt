package moura.renan.remote.service.twitter

import io.reactivex.Observable
import io.reactivex.Single
import moura.renan.remote.model.AuthModel
import moura.renan.remote.model.Tweet
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitterService {

    @POST("oauth2/token")
    fun getCredentials(@Query("grant_type") grantType : String = "client_credentials") : Single<AuthModel>

    @GET("1.1/statuses/user_timeline.json")
    fun getTweets(@Query("count") count : String = "10", @Query("screen_name") screenName : String) : Observable<List<Tweet>?>

}