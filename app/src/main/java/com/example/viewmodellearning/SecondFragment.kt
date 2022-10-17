package com.example.viewmodellearning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.viewmodellearning.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SecondViewModel
    private lateinit var viewModelFactory: SecondViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        viewModelFactory = SecondViewModelFactory(SecondFragmentArgs.fromBundle(requireArguments()).clicks)

        viewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]

        viewModel.clicks.observe(viewLifecycleOwner, Observer { clickNumber ->
            binding.secondFragmentTvClicks.text = clickNumber.toString()
        })

        binding.secondFragmentBtnBack.setOnClickListener {
            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment(viewModel.clicks.value!!))
        }

        binding.secondFragmentBtnClick.setOnClickListener { viewModel.addClick() }

        // Inflate the layout for this fragment
        return binding.root
    }

}