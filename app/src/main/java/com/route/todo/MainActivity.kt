package com.route.todo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation:BottomNavigationView
    lateinit var addtodo:FloatingActionButton
    val todoListFragment=TodoList()
    val settingsFragment=Settings()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pushFragment(TodoList())
        initViews()
        addtodo.setOnClickListener {
showAddBottomSheet()
        }
        bottomNavigation.setOnItemSelectedListener {item ->
            if (item.itemId==R.id.todo_list){
                pushFragment(todoListFragment)
            }else{
                pushFragment(settingsFragment)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun showAddBottomSheet() {
     val bottomsheetfragment=AddTodoBottomSheet()
        Log.e("in main","afte val")

        bottomsheetfragment.show(supportFragmentManager,"")
        Log.e("in main","afte support")
        bottomsheetfragment.onTodoAddedListener=object :AddTodoBottomSheet.OnTodoAddedListener{
            override fun onTodoAdded() {
                todoListFragment.getTodosListFromDB()
                Log.e("in main","in fun")
            }

        }


        }


    private fun initViews() {
        bottomNavigation=findViewById(R.id.bottom_nav)
        addtodo=findViewById(R.id.floating_btn)
    }

    fun pushFragment(fragment: Fragment){
        Log.e("in main","in fragment"+fragment)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

    }
}