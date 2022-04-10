package com.binar.ariefaryudisyidik.challengesilverchapter4.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.binar.ariefaryudisyidik.challengesilverchapter4.R
import com.binar.ariefaryudisyidik.challengesilverchapter4.database.Note
import com.binar.ariefaryudisyidik.challengesilverchapter4.database.NoteRoomDatabase
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.FragmentInputDialogBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.ui.HomeFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.*

class InputDialogFragment : DialogFragment() {

    private var _binding: FragmentInputDialogBinding? = null
    private val binding get() = _binding!!
    var mDb: NoteRoomDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDb = NoteRoomDatabase.getInstance(requireContext())

        binding.btnInput.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val date = dateFormat.format(Date())
            val objectNote = Note(null, date, title, description)
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
                GlobalScope.async {
                    val result = mDb?.noteDao()?.insertNote(objectNote)
                    activity?.runOnUiThread {
                        if (result != 0.toLong()) {
                            Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Failed to save", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dismiss()
                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.container, HomeFragment())
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