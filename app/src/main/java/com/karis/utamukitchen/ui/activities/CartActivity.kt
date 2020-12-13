package com.karis.utamukitchen.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
    private lateinit var mbottomSheet: ConstraintLayout
    private lateinit var bottomsheetBehaviour: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recyclerView_Cart.layoutManager = LinearLayoutManager(this)
        val adapter = CartAdapter(this)
        recyclerView_Cart.adapter = adapter

        viewModel.cartFood.observe(this,  {
            adapter.submitList(it)
        })

        mbottomSheet = findViewById(R.id.bottom_sheet)
        bottomsheetBehaviour = BottomSheetBehavior.from(mbottomSheet)
        bottomsheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN

        viewModel.totalPrice.observe(this,{
            when (it) {
                null -> {
                    textView_totalPrice.text = "Ksh 0"
                }
                else -> {
                    textView_totalPrice.text = "Ksh $it"
                }
            }
        })

    }

    override fun orderActivity(food: Food) {
        bottomsheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }
}