package pe.idat.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.idat.api.databinding.ItemDogBinding

class DogAdapter(val subRazas:List<String>):RecyclerView.Adapter<DogAdapter.SubRazaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubRazaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SubRazaViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(holder: SubRazaViewHolder, position: Int) {
        val subRaza = subRazas[position]
        holder.bind(subRaza,position+1)
    }

    override fun getItemCount(): Int {
        return subRazas.size
    }

    class SubRazaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemDogBinding.bind(view)
//        private val textViewRaza: TextView = view.findViewById(R.id.textSubraza)
//        private val textViewItem: TextView = view.findViewById(R.id.textItem)
//
//        fun bind(raza: String, item:Int) {
//            textViewRaza.text = raza
//            textViewItem.text = "Subraza: "+item.toString()
//        }
        fun bind(subRaza: String, item:Int) {
            binding.textSubraza.text = subRaza
            binding.textItem.text = item.toString()
        }
    }
}