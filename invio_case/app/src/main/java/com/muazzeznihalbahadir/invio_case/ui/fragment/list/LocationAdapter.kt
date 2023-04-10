package com.muazzeznihalbahadir.invio_case.ui.fragment.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muazzeznihalbahadir.invio_case.databinding.ItemListLokasyonBinding
import com.muazzeznihalbahadir.invio_case.model.Karakter
import com.muazzeznihalbahadir.invio_case.model.Lokasyon
import kotlinx.android.synthetic.main.item_list_lokasyon.view.*

class LocationAdapter :RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var listLokasyon = emptyList<Lokasyon>()
   // private lateinit var mListener : onItemClickListener
    var onItemClick : ((Lokasyon,position:Int)->Unit)? = null

   /* interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }*/

    class LocationViewHolder(private val binding: ItemListLokasyonBinding):RecyclerView.ViewHolder(binding.root){ //,listener: onItemClickListener

      /*  init {
            binding.txtLokasyonAd.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }*/

        fun bind(lokasyon: Lokasyon){
            binding.txtLokasyonAd.text = lokasyon.name
        }
        var isSelected: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListLokasyonBinding.inflate(layoutInflater,parent,false)
        return LocationViewHolder(binding) //,mListener
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(listLokasyon[position])

        holder.itemView.setOnClickListener {

            //   //  val deger = residentsTrim(listLokasyon[position].residents)
            //   //  Log.d("Lokasyondaki karakterler :", deger.size.toString())
            //   //  println(deger)

            onItemClick?.invoke(listLokasyon[position],position)
        }
    }
    override fun getItemCount(): Int {
        return listLokasyon.size
    }

   // @SuppressLint("NotifyDataSetChanged")
    fun setLocation(lokasyonlar:List<Lokasyon>){
        listLokasyon = lokasyonlar
        notifyDataSetChanged()
    }


}