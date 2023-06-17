package com.example.depremtakibi.presentation.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.depremtakibi.R
import com.example.depremtakibi.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateBack()
        onBackPressed()

    }

    private fun navigateBack() {
        binding.infoToolbar.setNavigationOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToHomeFragment()
            findNavController().navigate(action)
        }


    }

    private fun onBackPressed() =
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_infoFragment_to_homeFragment)
        }


}