package com.karis.utamukitchen.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.utamukitchen.R
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.adapters.CartAdapter
import com.karis.utamukitchen.ui.adapters.MenuAdapter
import com.karis.utamukitchen.ui.viewmodel.CartViewModel
import com.karis.utamukitchen.ui.viewmodel.OrderViewModel
import com.karis.utamukitchen.utils.Onclick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_cart.*

@AndroidEntryPoint
class CartActivity : AppCompatActivity(), Onclick {

    private val viewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recyclerView_Cart.layoutManager = LinearLayoutManager(this)
        val adapter = CartAdapter(this)
        recyclerView_Cart.adapter = adapter

        viewModel.cartFood.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.getFood()


    }

    override fun orderActivity(food: Food) {
    }
}