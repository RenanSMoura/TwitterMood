package moura.renan.remote.service.google

import retrofit2.http.POST

interface GoogleService {


    @POST
    fun analyze()
}