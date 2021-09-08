package com.proway.coroutines.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.proway.coroutines.view_model.NamesViewModel
import com.proway.coroutines.R
import com.proway.coroutines.databinding.NamesFragmentBinding
import com.proway.coroutines.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*

@AndroidEntryPoint
class NamesFragment : Fragment(R.layout.names_fragment) {

    companion object {
        fun newInstance() = NamesFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: NamesFragmentBinding
    private val listOfNames = listOf("Arthur", "Aline", "Joaquim", "Pedro")

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding = NamesFragmentBinding.bind(itemView)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val retornoAsync = CoroutineScope(Dispatchers.Main).async {
            printNames()
        }

        CoroutineScope(Dispatchers.Main).launch {
            // Irá escutar o retorno da função -> waitSplashScreenTimer()
            val result = retornoAsync.await()
            // Só executará esta parte do código para baixo depois que retornar do waitSplashScreenTImer()
            if (result) {
                binding.namesTextView.text = "ACABOOUUUUU!!!"
            }
        }

        viewModel.getRepositories()
        viewModel.getUsers()
    }

    suspend fun printNames(): Boolean {
        listOfNames.forEach {
            binding.namesTextView.text = it
            delay(500)
        }
        return true
    }
}