package com.emirozturk.country.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirozturk.country.R
import com.emirozturk.country.adapter.CountryAdapter
import com.emirozturk.country.databinding.FragmentFeedBinding
import com.emirozturk.country.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    lateinit var binding: FragmentFeedBinding;
    private lateinit var viewModel: FeedViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refleshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        observeData()
    }

    protected fun observeData() {
        viewModel.countries.observe(viewLifecycleOwner) {
            it?.let {
                binding.recyclerView.visibility = View.VISIBLE
                adapter.updateList(it)
            }
        }

        viewModel.countryError.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.textError.visibility = View.VISIBLE
                    return@observe
                }
                binding.textError.visibility = View.GONE
            }
        }

        viewModel.countryLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.progressCountryLoading.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.textError.visibility = View.GONE
                    return@observe
                }
                binding.progressCountryLoading.visibility = View.GONE
            }
        }
    }
}