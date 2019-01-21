package moura.renan.data

import io.reactivex.Observable
import io.reactivex.Single

interface TweetRemote {

    fun getAuth() : Single<AuthEntity>
    fun getTweets(screenName : String) : Observable<List<Tweet>?>

}