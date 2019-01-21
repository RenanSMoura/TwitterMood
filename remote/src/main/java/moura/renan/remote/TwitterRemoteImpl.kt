package moura.renan.remote

import io.reactivex.Observable
import io.reactivex.Single
import moura.renan.data.AuthEntity
import moura.renan.data.TweetRemote
import moura.renan.remote.model.AuthModel
import moura.renan.remote.model.Tweet
import moura.renan.remote.service.twitter.TwitterService
import moura.renan.data.Tweet as TweetData

class TwitterRemoteImpl constructor(private val service : TwitterService) : TweetRemote {
    override fun getAuth(): Single<AuthEntity> {
        return service.getCredentials().map {
            AuthModel().mapFromModel(it)
        }
    }

    override fun getTweets(screenName : String): Observable<List<TweetData>?> {

        return service.getTweets(screenName = screenName).map { list ->
            list.map {
                Tweet().mapFromModel(it)
            }
        }

    }

}