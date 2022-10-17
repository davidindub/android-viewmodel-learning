package com.example.viewmodellearning

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.viewmodellearning.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: FirstViewModel
    private lateinit var viewModelFactory: FirstViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                super.onPrepareMenu(menu)
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.firstmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        viewModelFactory = FirstViewModelFactory(FirstFragmentArgs.fromBundle(requireArguments()).clicks)

        viewModel = ViewModelProvider(this, viewModelFactory)[FirstViewModel::class.java]

        viewModel.clicks.observe(viewLifecycleOwner, Observer { clickNumber ->
            binding.firstFragmentTvClicksNum.text = clickNumber.toString()
        })

        viewModel.message.observe(viewLifecycleOwner, Observer { message ->
            binding.firstFragmentTvMessage.text = message
        })

//        set click listeners
        binding.firstFragmentBtnHello.setOnClickListener { viewModel.hello(binding.firstFragmentEtName.text.toString()) }
        binding.firstFragmentBtnGoodbye.setOnClickListener { viewModel.goodbye(binding.firstFragmentEtName.text.toString()) }
        binding.firstFragmentBtnEnd.setOnClickListener { nextFragment() }

        return binding.root
    }

    private fun nextFragment() {
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(viewModel.clicks.value!!))
    }
}