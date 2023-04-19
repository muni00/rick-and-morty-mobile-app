package com.muazzeznihalbahadir.invio_case.ui.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muazzeznihalbahadir.invio_case.R
import com.muazzeznihalbahadir.invio_case.databinding.ItemListLokasyonBinding
import com.muazzeznihalbahadir.invio_case.model.Lokasyon

class LocationAdapter :RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var listLokasyon = emptyList<Lokasyon>()
    var onItemClick : ((Lokasyon,position:Int,itemCount : Int)->Unit)? = null
    var selectedPosition = RecyclerView.NO_POSITION

    inner class LocationViewHolder(private val binding: ItemListLokasyonBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(lokasyon: Lokasyon, position: Int, selectedPosition: Int){

            binding.txtLokasyonAd.text = lokasyon.name
            if (position == selectedPosition) {
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple))
            } else {
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.soft_black))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListLokasyonBinding.inflate(layoutInflater,parent,false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(listLokasyon[position], position, selectedPosition)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listLokasyon[position],position,listLokasyon.size)
            notifyItemChanged(selectedPosition)
            selectedPosition = position
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return listLokasyon.size
    }

    fun setLocation(lokasyonlar:List<Lokasyon>){
        listLokasyon = lokasyonlar
        notifyDataSetChanged()
    }

}