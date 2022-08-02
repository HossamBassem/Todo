package com.route.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation:BottomNavigationView
    lateinit var addtodo:FloatingActionButton
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
                pushFragment(TodoList())
            }else{
                pushFragment(Settings())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun showAddBottomSheet() {
     val bottomsheetfragment=addTodoBottomSheet()
        bottomsheetfragment.show(supportFragmentManager,"")
    }

    private fun initViews() {
        bottomNavigation=findViewById(R.id.bottom_nav)
        addtodo=findViewById(R.id.floating_btn)
    }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

    }
}