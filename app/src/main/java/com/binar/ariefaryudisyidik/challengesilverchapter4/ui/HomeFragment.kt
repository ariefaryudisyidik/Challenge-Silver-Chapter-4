package com.binar.ariefaryudisyidik.challengesilverchapter4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.ariefaryudisyidik.challengesilverchapter4.R
import com.binar.ariefaryudisyidik.challengesilverchapter4.database.NoteRoomDatabase
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.FragmentHomeBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.dialog.InputDialogFragment
import com.binar.ariefaryudisyidik.challengesilverchapter4.helper.Preferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mDb: NoteRoomDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDb = NoteRoomDatabase.getInstance(requireContext())
        fetchData()

        binding.tvUsername.text = "Welcome, ${Preferences().getLoggedInUser(requireContext())}!"
        binding.tvLogout.setOnClickListener {
            Preferences().clearLoggedInUser(requireContext())
            it.findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        binding.fabAdd.setOnClickListener {
            InputDialogFragment().show(parentFragmentManager, "InputDialogFragment")
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        GlobalScope.launch {
            val listNote = mDb?.noteDao()?.getAllNotes()
            activity?.runOnUiThread {
                listNote?.let {
                    binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvNotes.setHasFixedSize(true)
                    binding.rvNotes.adapter = NoteAdapter(it)
                }
            }
        }
    }
}