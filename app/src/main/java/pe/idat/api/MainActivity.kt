package pe.idat.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import pe.idat.api.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val traerDatos = RetrofitHelper.consumirApi.getByRaza("bulldog")

        traerDatos.enqueue(object : Callback<DogsResponse> {
            override fun onResponse(call: Call<DogsResponse>, response: Response<DogsResponse>) {
                binding.resultado.text = response.body().toString()
            }

            override fun onFailure(call: Call<DogsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error al consultar en la api",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
