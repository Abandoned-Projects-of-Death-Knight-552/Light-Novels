package com.knightshrestha.lightnovels.ui.browse

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.knightshrestha.lightnovels.databinding.FragmentBrowseBinding
import com.knightshrestha.lightnovels.remotedatabase.MyRepo
import com.knightshrestha.lightnovels.remotedatabase.MyViewFactory
import com.knightshrestha.lightnovels.remotedatabase.MyViewModel

class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MyViewModel

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        val view = binding.root

        val repo = MyRepo()
        val vmFactory = MyViewFactory(repo)
        viewModel = ViewModelProvider(this, vmFactory)[MyViewModel::class.java]
        Log.d("Neon Data", "Fetching")

        viewModel.fetchAllData()

        viewModel.neonData.observe(viewLifecycleOwner) {
            if(it != null) {
                Log.d("Neon Data", it.SeriesItem_aggregate.nodes[3].title)



            }
        }



        return view
    }

}