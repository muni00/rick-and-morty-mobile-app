package com.muazzeznihalbahadir.invio_case.ui.fragment.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.muazzeznihalbahadir.invio_case.R
import com.muazzeznihalbahadir.invio_case.databinding.ItemListBinding
import com.muazzeznihalbahadir.invio_case.model.Karakter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listKarakter = emptyList<Karakter>()

    class CharacterViewHolder(private val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(karakter : Karakter){
            binding.txtIdCharacter.text = karakter.id.toString()
            binding.txtNameCharacter.text = karakter.name
            Picasso.get().load(karakter.image).into(binding.characterImg)
            binding.txtStatus.text = karakter.status
            itemView.setOnClickListener { view ->
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(karakter)
                view.findNavController().navigate(action)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater,parent,false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listKarakter[position])
        setGenderIcon(position, holder)
    }

    private fun setGenderIcon(position: Int, holder: CharacterViewHolder) {
        if (listKarakter[position].gender == "Male") {
            Picasso.get().load(R.drawable.male_gender).into(holder.itemView.img_genders_character)
        } else if (listKarakter[position].gender == "Female") {
            Picasso.get().load(R.drawable.female).into(holder.itemView.img_genders_character)
        } else if (listKarakter[position].gender == "Genderless") {
            Picasso.get().load(R.drawable.genderless).into(holder.itemView.img_genders_character)
        } else {
            Picasso.get().load(R.drawable.unknown).into(holder.itemView.img_genders_character)
        }
    }

    override fun getItemCount(): Int {
        return listKarakter.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacters(karakterler:List<Karakter>){
        listKarakter = karakterler
        notifyDataSetChanged()
    }
}