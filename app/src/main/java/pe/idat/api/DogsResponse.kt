package pe.idat.api

import com.google.gson.annotations.SerializedName

data class DogsResponse(@SerializedName("status")
                        var status: String,
                        @SerializedName("message")
                        var message: List<String>)