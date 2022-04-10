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
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.FragmentEditDialogBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.ui.HomeFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.*

class EditDialogFragment : DialogFragment() {

    private var _binding: FragmentEditDialogBinding? = null
    private val binding get() = _binding!!
    var mDb: NoteRoomDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDb = NoteRoomDatabase.getInstance(requireContext())
        val mBundle = this.arguments
        val objectNote = mBundle?.getParcelable<Note>("note")!!

        binding.edtTitle.setText(objectNote.title)
        binding.edtDescription.setText(objectNote.description)

        binding.btnUpdate.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val date = dateFormat.format(Date())
            var isValid = true

            if (title.isEmpty()) {
                binding.tilTitle.error = "Field can't be empty"
                isValid = false
            } else {
                binding.tilTitle.error = null
                binding.tilTitle.isErrorEnabled = false
            }

            if (description.isEmpty()) {
                binding.tilDescription.error = "Field can't be empty"
                isValid = false
            } else {
                binding.tilDescription.error = null
                binding.tilDescription.isErrorEnabled = false
            }

            if (isValid) {
                objectNote.date = date
                objectNote.title = title
                objectNote.description = description

                GlobalScope.async {
                    val result = mDb?.noteDao()?.updateNote(objectNote)
                    activity?.runOnUiThread {
                        if (result != 0) {
                            Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT)
                                .show()
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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}