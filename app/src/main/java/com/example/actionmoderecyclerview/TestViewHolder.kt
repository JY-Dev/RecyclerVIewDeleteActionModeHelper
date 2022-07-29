package com.example.actionmoderecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.actionmoderecyclerview.databinding.ItemTestBinding

class TestViewHolder(
    private val binding: ItemTestBinding,
    private val longClick: (String) -> Unit,
    private val click: (String) -> Unit,
    private val isItemSelected : (String) -> Boolean
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.titleTextView.text = item
        binding.root.setOnLongClickListener {
            longClick(item)
            true
        }
        binding.selectedView.visibility = if(isItemSelected(item)) View.VISIBLE else View.GONE
        binding.root.setOnClickListener {
            click(item)
        }
    }
}