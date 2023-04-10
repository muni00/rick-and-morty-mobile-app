package com.muazzeznihalbahadir.invio_case.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muazzeznihalbahadir.invio_case.api.Repository
import com.muazzeznihalbahadir.invio_case.databinding.FragmentListBinding
import com.muazzeznihalbahadir.invio_case.viewmodel.SharedViewModel
import com.muazzeznihalbahadir.invio_case.viewmodel.SharedViewModelFactory


class ListFragment : Fragment() {

    private var _binding : FragmentListBinding?=null
    private val binding get() = _binding!!
    private val sharedViewModel : SharedViewModel by activityViewModels {SharedViewModelFactory(Repository())}
    private var adapter = CharacterAdapter()
    private var adapterLocation = LocationAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel.getCharacters(1)
        sharedViewModel.getLocations(1)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {

            sharedViewModel.listKarakterler.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                        it.body()!!.results.let { it1 -> adapter.setCharacters(it1) }
                    //Log.d("Result", it.body()!!.results.toString())
                } else {
                    // buraya circular bar koyabilirsin ya da hata mesajı falan
                    Log.d("ResultError", it.code().toString())
                }
            }

            sharedViewModel.listLokasyonlar.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                    adapterLocation.setLocation(it.body()!!.results)
                   // Log.d("Result", it.body()!!.results.toString())
                } else {
                    // buraya circular bar koyabilirsin ya da hata mesajı falan
                    Log.d("ResultError", it.code().toString())
                }
            }

            recycclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            recycclerview.adapter = adapter

            rcvYatay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rcvYatay.adapter = adapterLocation

            adapterLocation.onItemClick = {a,b ->

                Log.d("NAME", a.name)
                Log.d("POSITION", b.toString())

                var deger = residentsTrim(a.residents)
                println(deger.joinToString(","))
                getMultipleCharacters(deger.joinToString(","))

            }
           // var name :String
           /* adapterLocation.setOnItemClickListener(object :LocationAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    for (i in 0 until rcvYatay.childCount) {
                        val item: View = rcvYatay.getChildAt(i)
                        val viewHolder = rcvYatay.getChildViewHolder(item) as LocationAdapter.LocationViewHolder
                        if (i == position) {
                            viewHolder.itemView.setBackgroundColor(Color.LTGRAY)

                            name = view.txt_lokasyon_ad.text.toString()
                            Log.d("NAME", position.toString())
                            viewHolder.isSelected = true
                        } else {
                            viewHolder.itemView.setBackgroundColor(Color.BLACK)
                            viewHolder.isSelected = false
                        }
                    }
                }

            })*/

            //set on click olaylarını da burada yaz
        }

    }

    fun residentsTrim(karakterDizisi:List<String>): ArrayList<String> {
        val donenList = ArrayList<String>()
        karakterDizisi.forEach{
            val deger = it.substring(42)
            donenList.add(deger)
        }
        return donenList
    }
    private fun getMultipleCharacters(ids:String){
        sharedViewModel.getMultipleCharacters("[$ids]")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}