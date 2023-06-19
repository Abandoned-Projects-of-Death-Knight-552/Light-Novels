package com.knightshrestha.lightnovels.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.knightshrestha.lightnovels.database.ViewModel
import com.knightshrestha.lightnovels.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        viewModel.allBooks.observe(viewLifecycleOwner) {
            val remoteList = it
            val localList = it.filter { it.isInLibrary }
            binding.tvRemoteItemCount.text = remoteList.size.toString() + " Items in Remote Database"
            binding.tvLocalItemCount.text = localList.size.toString() + " Items in Local Database"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}