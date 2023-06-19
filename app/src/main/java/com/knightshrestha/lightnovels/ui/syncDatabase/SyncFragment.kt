package com.knightshrestha.lightnovels.ui.syncDatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.knightshrestha.lightnovels.R
import com.knightshrestha.lightnovels.database.SeriesItem
import com.knightshrestha.lightnovels.database.ViewModel
import com.knightshrestha.lightnovels.databinding.FragmentSyncBinding
import com.knightshrestha.lightnovels.remotedatabase.MyRepo
import com.knightshrestha.lightnovels.remotedatabase.MyViewFactory
import com.knightshrestha.lightnovels.remotedatabase.MyViewModel

class SyncFragment : Fragment() {


    private var _binding: FragmentSyncBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var apiViewModel: MyViewModel
    private lateinit var dbViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSyncBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repo = MyRepo()
        val vmFactory = MyViewFactory(repo)
        apiViewModel = ViewModelProvider(this, vmFactory)[MyViewModel::class.java]
        dbViewModel = ViewModelProvider(this)[ViewModel::class.java]

        binding.btnSyncDatabase.setOnClickListener {
            binding.tvLogs.text = getString(R.string.SyncFrag_Log_text)
            apiViewModel.fetchAllData()

            apiViewModel.neonData.observe(viewLifecycleOwner) {
                if (it != null) {
                    for ((index, item) in it.SeriesItem_aggregate.nodes.withIndex()) {
                        binding.tvLogs.text = (index + 1).toString() + " / " + it.SeriesItem_aggregate.nodes.size.toString()

                        binding.pbProgress.progress =
                            ((index + 1).toDouble() / it.SeriesItem_aggregate.nodes.size * 100).toInt()
                        binding.tvRemoteTitle.text = item.title

                        dbViewModel.getLocalSeries(item.seriesID)
                        dbViewModel.specificBook.observe(viewLifecycleOwner) { localBook ->
                            if (localBook != null) {
                                binding.tvLocalTitle.text = localBook.title
                                val titleCheck = item.title == localBook.title
                                val thumbCheck = item.thumbnail == localBook.thumbnail
                                val plotCheck = item.synopsis == localBook.synopsis
                                val authorCheck = item.author == localBook.author
                                val aliasCheck = item.alias == localBook.alias

                                val anyFalse =
                                    !titleCheck || !thumbCheck || !plotCheck || !authorCheck || !aliasCheck

                                if (anyFalse) {
                                    dbViewModel.updateLocalItem(
                                        SeriesItem(
                                            title = item.title,
                                            thumbnail = item.thumbnail,
                                            synopsis = item.synopsis,
                                            genres = item.genres,
                                            download = localBook.download,
                                            author = item.author,
                                            alias = item.alias,
                                            seriesID = item.seriesID
                                        )
                                    )
                                }

                            } else {
                                dbViewModel.insertLocalItem(
                                    SeriesItem(
                                        title = item.title,
                                        thumbnail = item.thumbnail,
                                        synopsis = item.synopsis,
                                        genres = item.genres,
                                        download = item.download,
                                        author = item.author,
                                        alias = item.alias,
                                        seriesID = item.seriesID
                                    )
                                )
                            }
                        }


                    }
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}