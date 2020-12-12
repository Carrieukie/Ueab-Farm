package com.karis.utamukitchen.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.utamukitchen.R
import com.karis.utamukitchen.ui.adapters.MenuAdapter
import com.karis.utamukitchen.models.Category
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Onclick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,Onclick{
    @Inject
    lateinit var menuList: ArrayList<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewMenu.layoutManager = LinearLayoutManager(this)
        var adapter = MenuAdapter(this)
        recyclerViewMenu.adapter = adapter
        adapter.submitList(menuList)

        imageView_cart.setOnClickListener {
            Intent(applicationContext,CartActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun orderActivity(food: Food) {
        Intent(applicationContext,OrderActivity::class.java).also {
            it.putExtra("food",food)
            startActivity(it)
        }
    }
}