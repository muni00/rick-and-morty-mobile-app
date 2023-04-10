package com.muazzeznihalbahadir.invio_case.ui.fragment.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.muazzeznihalbahadir.invio_case.databinding.FragmentDetailBinding
import com.muazzeznihalbahadir.invio_case.model.Karakter
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DetailFragment : Fragment() {

    private var _binding:FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.apply {
            //
            Picasso.get().load(character.image).fit().centerCrop().into(binding.imgCharacters)
            binding.txtDetailStatus.text = character.status
            binding.txtDetailGender.text = character.gender
            binding.txtDetailSpecies.text = character.species
            binding.txtDetailOrigin.text = character.origin.name
            binding.txtDetailLocation.text = character.location.name
            val deger = episodeTrim(character.episode)
            binding.txtDetailEpisode.text = deger.joinToString(",")
            val outputDate = timeConvert(character)
            binding.txtDetailCreated.text = outputDate
        }
    }

    private fun timeConvert(character: Karakter): String? {
        val inputDate = character.created
        val instant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.parse(inputDate)

        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy , HH:mm:ss", Locale.ENGLISH).withZone(ZoneId.systemDefault())
        val outputDate = formatter.format(instant)
        return outputDate
    }

    fun episodeTrim(EpisodeListesi:List<String>): ArrayList<String> {
        val donenList = ArrayList<String>()
        EpisodeListesi.forEach{
            val deger = it.substring(40)
            donenList.add(deger)
        }
        return donenList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}