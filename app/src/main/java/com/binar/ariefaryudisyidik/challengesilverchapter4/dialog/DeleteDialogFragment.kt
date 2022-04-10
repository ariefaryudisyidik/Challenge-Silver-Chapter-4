package com.binar.ariefaryudisyidik.challengesilverchapter4.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import com.binar.ariefaryudisyidik.challengesilverchapter4.R
import com.binar.ariefaryudisyidik.challengesilverchapter4.database.Note
import com.binar.ariefaryudisyidik.challengesilverchapter4.database.NoteRoomDatabase
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.FragmentDeleteDialogBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.ui.HomeFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DeleteDialogFragment : DialogFragment() {

    private var _binding: FragmentDeleteDialogBinding? = null
    private val binding get() = _binding!!
    var mDb: NoteRoomDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDb = NoteRoomDatabase.getInstance(requireContext())
        val mBundle = this.arguments
        val objectNote = mBundle?.getParcelable<Note>("note")!!

        binding.btnDelete.setOnClickListener {
            GlobalScope.async {
                val result = mDb?.noteDao()?.deleteNote(objectNote)
                activity?.runOnUiThread {
                    if (result != 0) {
                        Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Failed to delete", Toast.LENGTH_SHORT).show()
                    }
                }
                dismiss()
                val finalHost = NavHostFragment.create(R.navigation.main_navigation)
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.container, finalHost, "HomeFragment")
                    setPrimaryNavigationFragment(finalHost)
                    commit()
                }
            }
        }
        binding.btnCancel.setOnClickListener { dismiss() }
        isCancelable = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}