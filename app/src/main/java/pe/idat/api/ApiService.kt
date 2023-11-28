package pe.idat.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("breed/{raza}/list")
    fun getByRaza(@Path("raza") raza: String) : Call<DogsResponse>
}