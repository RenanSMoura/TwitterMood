package moura.renan.data.service.google

import retrofit2.http.POST

interface GoogleService {


    @POST
    fun analyze()
}