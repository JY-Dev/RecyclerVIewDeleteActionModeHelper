package com.example.actionmoderecyclerview

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode

class DeleteActionModeHelper<T> constructor(
    private val deleteMode: DeleteActionMode<T>,
    private val notifyItemChanged: (item: T) -> Unit
) : ActionMode.Callback {
    private val deleteList = mutableListOf<T>()
    var deleteActionMode: ActionMode? = null
    val itemLongClick: (T) -> Unit = { item ->
        if (deleteActionMode == null)
            deleteMode.deleteActionMode(this)
        addDeleteList(item)
    }
    val itemClick: (T) -> Unit = { item ->
        if (deleteActionMode != null)
            addDeleteList(item)
    }
    val isItemSelected: (T) -> Boolean = {
        deleteList.contains(it)
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        val menuInflater = mode.menuInflater
        menuInflater?.inflate(R.menu.delete_mode_menu, menu)
        deleteActionMode = mode
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean =
        true

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuDeleteButton -> {
                deleteMode.complete(getCompleteList())
                mode.finish()
            }
        }
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        deleteActionMode = null
        deleteList.clear()
    }

    private fun addDeleteList(item: T) {
        if (deleteList.contains(item)) {
            deleteList.remove(item)
            if (deleteList.size == 0)
                deleteActionMode?.finish()
        } else
            deleteList.add(item)
        notifyItemChanged(item)
        deleteActionMode?.title = deleteMode.getDeleteTitle(deleteList.size)
    }

    private fun getCompleteList(): List<T> {
        return deleteMode.getCurrentList().toMutableList().apply {
            deleteList.forEach(::remove)
        }
    }

}

interface DeleteActionMode<T> {
    fun deleteActionMode(actionCallback: ActionMode.Callback)
    fun complete(completeList: List<T>)
    fun getDeleteTitle(deleteListSize: Int): String
    fun getCurrentList() : List<T>
}
