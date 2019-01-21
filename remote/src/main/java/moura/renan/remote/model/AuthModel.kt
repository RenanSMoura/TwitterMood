package moura.renan.remote.model

import moura.renan.data.AuthEntity
import moura.renan.remote.mapper.RemoteMapper

data class AuthModel( val token_type : String = "", val access_token : String = "") :
    RemoteMapper<AuthModel, AuthEntity> {
    override fun mapFromModel(model: AuthModel): AuthEntity {
        return AuthEntity(model.token_type,model.access_token)
    }

}