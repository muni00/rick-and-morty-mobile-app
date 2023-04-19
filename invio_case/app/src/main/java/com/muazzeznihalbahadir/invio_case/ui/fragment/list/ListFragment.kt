package com.muazzeznihalbahadir.invio_case.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muazzeznihalbahadir.invio_case.R
import com.muazzeznihalbahadir.invio_case.api.Repository
import com.muazzeznihalbahadir.invio_case.databinding.FragmentListBinding
import com.muazzeznihalbahadir.invio_case.viewmodel.SharedViewModel
import com.muazzeznihalbahadir.invio_case.viewmodel.SharedViewModelFactory
import java.lang.Integer.parseInt


class ListFragment : Fragment() {
    private val loadTime: Long = 1000 * 2L //yüklenme süresini 2 saniye olarak aldık
    private var isLoading: Boolean = false //Yüklenme işleminin başlamasını ve bitmesini kontrol eden bool değişkeni
    var nextPage : Int = 1

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

            sharedViewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
                binding.tamSayfaProgress.visibility = if (showLoading) View.VISIBLE else View.GONE
            }
            sharedViewModel.showLoadingLocation.observe(viewLifecycleOwner) { showLoadingLocation ->
                binding.pBar.visibility = if (showLoadingLocation) View.VISIBLE else View.GONE
            }

            //karakter listeleme
            sharedViewModel.listKarakterler.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                        it.body()!!.results.let { it1 -> adapter.setCharacters(it1) }
                    //Log.d("Result", it.body()!!.results.toString())
                } else {
                    Log.d("ResultError", it.code().toString())
                }
            }
            recycclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            recycclerview.adapter = adapter

            //lokasyon listeleme
            sharedViewModel.listLokasyonlar.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                    adapterLocation.setLocation(it.body()!!.results)
                    if (it.body()!!.info.next != null){
                        nextPage =   parseInt(it.body()!!.info.next.substring(46))
                    }else{
                        nextPage = 8
                    }
                   // Log.d("Result", it.body()!!.info.next.substring(46))
                } else {
                    Log.d("ResultError", it.code().toString())
                }
            }
            rcvYatay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rcvYatay.adapter = adapterLocation

            //lokasyon adapterde item click olayı tetiklenmesi
            adapterLocation.onItemClick = {location,position,itemCount ->
                val deger = residentsTrim(location.residents)
                getMultipleCharacters(deger.joinToString(","))
            }

            rcvYatay.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastPosition: Int = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading && totalItemCount == (lastPosition + 1)) {
                        Handler().postDelayed({ //2 saniye sonra
                            Log.d("Next Page",nextPage.toString())
                            if(nextPage>7){
                                // null olunca ne olacağı yazıcak
                            }else{
                                sharedViewModel.getLocationsNew(nextPage)
                            }
                            isLoading = false
                        }, loadTime)
                        isLoading = true //yükleniypr
                    }
                }
            })
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