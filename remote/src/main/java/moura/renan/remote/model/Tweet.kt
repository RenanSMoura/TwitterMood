package moura.renan.remote.model

import moura.renan.remote.mapper.RemoteMapper
import  moura.renan.data.Tweet as TweetData

data class Tweet(val text : String = "") : RemoteMapper<Tweet, TweetData> {

    override fun mapFromModel(model: Tweet): TweetData {
        return TweetData(model.text)
    }

}