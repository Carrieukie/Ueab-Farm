package com.karis.utamukitchen.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.utamukitchen.R
import com.karis.utamukitchen.models.Category
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.adapters.FoodAdapter
import com.karis.utamukitchen.ui.viewmodel.MainViewModel
import com.karis.utamukitchen.utils.Constants
import com.karis.utamukitchen.utils.Constants.BREAKFAST
import com.karis.utamukitchen.utils.Constants.MAIN
import com.karis.utamukitchen.utils.Constants.SIDES
import com.karis.utamukitchen.utils.Onclick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,Onclick, AdapterView.OnItemSelectedListener{
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var objects : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        objects = arrayOf(
            BREAKFAST,
            MAIN,
            SIDES
        )

        val spinneradapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )

        spin.adapter = spinneradapter

        spin.onItemSelectedListener = this

        val adapter = foodAdapter()

        viewModel.foods.observe(this, {
            adapter.submitList(it)
        })

        imageView_cart.setOnClickListener {
            Intent(applicationContext, CartActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun foodAdapter(): FoodAdapter {
        recyclerViewMenu.layoutManager = LinearLayoutManager(this)
        val adapter = FoodAdapter(this)
        recyclerViewMenu.adapter = adapter
        return adapter
    }

    override fun orderActivity(food: Food) {
        Intent(applicationContext, OrderActivity::class.java).also {
            it.putExtra("food", food)
            startActivity(it)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setFilter(objects[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        viewModel.setFilter(objects[0])
    }

}