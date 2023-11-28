package pe.idat.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.idat.api.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val listaActualizada = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.filtrarPorRaza.setOnQueryTextListener(this)
        listarSubRazasView()

    }

    private fun listarSubRazasView(){
        adapter = DogAdapter(listaActualizada)
        binding.listaSubrazas.layoutManager = LinearLayoutManager(this)
        binding.listaSubrazas.adapter = adapter
    }

    private fun listarSubRazas(raza: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<DogsResponse> = RetrofitHelper.consumirApi.getByRaza(raza)
            val listaSubRazas = call.body()

            runOnUiThread(){
                if(call.isSuccessful){
                    val lista = listaSubRazas?.message ?: emptyList()
                    listaActualizada.clear()
                    listaActualizada.addAll(lista)
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this@MainActivity,"Error al consultar en la api",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            listarSubRazas(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }
}
