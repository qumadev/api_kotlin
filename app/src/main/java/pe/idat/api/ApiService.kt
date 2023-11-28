package pe.idat.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("breed/{raza}/list")
    suspend fun getByRaza(@Path("raza") raza: String) : Response<DogsResponse>
}