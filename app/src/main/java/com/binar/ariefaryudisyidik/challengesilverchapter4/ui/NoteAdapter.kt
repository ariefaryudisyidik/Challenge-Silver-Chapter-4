package com.binar.ariefaryudisyidik.challengesilverchapter4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.ariefaryudisyidik.challengesilverchapter4.database.Note
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.ItemNoteBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.dialog.DeleteDialogFragment
import com.binar.ariefaryudisyidik.challengesilverchapter4.dialog.EditDialogFragment

class NoteAdapter(private val listNote: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = (holder.itemView.context as MainActivity)
        val mBundle = Bundle()

        holder.binding.tvItemDate.text = listNote[position].date
        holder.binding.tvItemTitle.text = listNote[position].title
        holder.binding.tvItemDescription.text = listNote[position].description
        holder.binding.ivEdit.setOnClickListener {
            mBundle.putParcelable("note", listNote[position])
            val editDialogFragment = EditDialogFragment()
            editDialogFragment.arguments = mBundle
            editDialogFragment.show(parent.supportFragmentManager, "EditDialogFragment")
        }
        holder.binding.ivDelete.setOnClickListener {
            mBundle.putParcelable("note", listNote[position])
            val deleteDialogFragment = DeleteDialogFragment()
            deleteDialogFragment.arguments = mBundle
            deleteDialogFragment.show(parent.supportFragmentManager, "DeleteDialogFragment")
        }
    }

    override fun getItemCount() = listNote.size
}
