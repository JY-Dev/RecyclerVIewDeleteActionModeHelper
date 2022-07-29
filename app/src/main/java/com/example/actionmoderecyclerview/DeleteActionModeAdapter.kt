package com.example.actionmoderecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.actionmoderecyclerview.databinding.ItemTestBinding

class DeleteActionModeAdapter(deleteMode: DeleteActionMode<String>) :
    ListAdapter<String, TestViewHolder>(diffUtil) {
    private val deleteActionModeHelper: DeleteActionModeHelper<String> =
        DeleteActionModeHelper(deleteMode){
            notifyItemChanged(currentList.indexOf(it))
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(
            ItemTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            deleteActionModeHelper.itemLongClick,
            deleteActionModeHelper.itemClick,
            deleteActionModeHelper.isItemSelected
        )
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}