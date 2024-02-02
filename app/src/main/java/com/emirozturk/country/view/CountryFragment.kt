package com.emirozturk.country.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.emirozturk.country.R
import com.emirozturk.country.databinding.FragmentCountryBinding
import com.emirozturk.country.util.getImageFromUrl
import com.emirozturk.country.util.getProgressBarAsPlaceholder
import com.emirozturk.country.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel
    private lateinit var binding: FragmentCountryBinding
    private var id = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCountryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            id = CountryFragmentArgs.fromBundle(it).id
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(id)
        observeData()
    }

    private fun observeData() {
        viewModel.country.observe(viewLifecycleOwner) {
            it?.let {
                binding.selectedCountry = it
            }
        }
    }
}