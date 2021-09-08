package com.proway.coroutines.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.coroutines.R
import com.proway.coroutines.databinding.MainFragmentBinding
import com.proway.coroutines.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val numberOfRegistersPassed = CoroutineScope(Dispatchers.Main).async {
            checkNames()
        }
        CoroutineScope(Dispatchers.Main).launch {
            val registers = numberOfRegistersPassed.await()
            binding.numberTextView.text = "Number of names found was $registers"
        }

        viewModel.getRepositories()
        viewModel.getUsers()
    }

    suspend fun checkNames(): Int {
        val listOfNames = listOf("Tatiane", "Maria", "José", "Cristina")
        listOfNames.forEach {
            binding.message.text = it
            delay(2000)
        }
        return  listOfNames.size
    }

}