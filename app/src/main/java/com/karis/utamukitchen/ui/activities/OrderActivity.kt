package com.karis.utamukitchen.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.karis.utamukitchen.R
import com.karis.utamukitchen.data.room.Converters
import com.karis.utamukitchen.databinding.ActivityOrderBinding
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.viewmodel.OrderViewModel
import com.karis.utamukitchen.utils.Mappers
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_content.*

@AndroidEntryPoint
class OrderActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {

    private val viewModel by viewModels<OrderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityOrderBinding =  DataBindingUtil.setContentView(this, R.layout.activity_order)
        val food = intent.getParcelableExtra<Food>("food")
        binding.numberpickerMainPicker.minValue = 1
        binding.numberpickerMainPicker.maxValue = 20
        binding.numberpickerMainPicker.setOnValueChangedListener(this)
        Glide.with(this)
            .load(food?.image)
            .circleCrop()
            .into(binding.imageViewFood)

        binding.food = food?.let { Mappers.toFoodDataBinding(it) }

        binding.btnAdd.setOnClickListener {
            if (food != null) {
                viewModel.addFood(food)
            }

            Intent(applicationContext,CartActivity::class.java).also {
                it.putExtra("food",food)
                startActivity(it)
            }
        }

    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {

    }
}