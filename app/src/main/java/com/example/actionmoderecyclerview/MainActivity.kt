package com.example.actionmoderecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import com.example.actionmoderecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding : ActivityMainBinding
    private val deleteActionModeAdapter : DeleteActionModeAdapter by lazy {
        DeleteActionModeAdapter(object : DeleteActionMode<String>{
            override fun deleteActionMode(actionCallback: ActionMode.Callback) {
                startSupportActionMode(actionCallback)
            }

            override fun complete(completeList: List<String>) {
                deleteActionModeAdapter.submitList(completeList)
            }

            override fun getDeleteTitle(deleteListSize: Int): String =
                "삭제 할 아이템 수 : $deleteListSize"

            override fun getCurrentList(): List<String> =
                deleteActionModeAdapter.currentList
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }

    private fun setView(){
        with(binding){
            testRecyclerView.adapter = deleteActionModeAdapter
            deleteActionModeAdapter.submitList(mutableListOf<String>().apply {
                for (i in 0 until 200){
                    add("data : $i")
                }
            })
        }
    }
}