package com.karis.utamukitchen.utils

import com.karis.utamukitchen.models.Food

interface Onclick {
    fun orderActivity(food: Food)
}
/*

package com.karis.utamukitchen.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.NumberPicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.karis.utamukitchen.R
import com.karis.utamukitchen.databinding.ActivityOrderBinding
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.viewmodel.OrderViewModel
import com.yy.mobile.rollingtextview.CharOrder.Alphabet
import com.yy.mobile.rollingtextview.RollingTextView
import com.yy.mobile.rollingtextview.strategy.Strategy
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrderActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {

    private val viewModel by viewModels<OrderViewModel>()
    lateinit var rollingTextViewTotalPrice: RollingTextView
    lateinit var rollingTextViewOrderSize: RollingTextView

    lateinit var food:Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityOrderBinding =  DataBindingUtil.setContentView(this, R.layout.activity_order)
        food = intent.getParcelableExtra<Food>("food")!!
        viewModel.food = food
        binding.viewmodel = viewModel
        initializeViews(binding)
        Glide.with(this)
            .load(food?.image)
            .circleCrop()
            .into(binding.imageViewFood)


        binding.btnAdd.setOnClickListener {
            if (food != null) {
                viewModel.addFood(food)
            }

            Intent(applicationContext, CartActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun initializeViews(binding: ActivityOrderBinding) {
        binding.numberpickerMainPicker.minValue = 1
        binding.numberpickerMainPicker.maxValue = 20
        binding.numberpickerMainPicker.setOnValueChangedListener(this)
        rollingTextViewTotalPrice = binding.alphaBetViewTotalPrice
        rollingTextViewTotalPrice.animationDuration = 400L
        rollingTextViewTotalPrice.charStrategy = Strategy.NormalAnimation()
        rollingTextViewTotalPrice.addCharOrder(Alphabet)
        rollingTextViewTotalPrice.animationInterpolator = AccelerateDecelerateInterpolator()

        rollingTextViewTotalPrice.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finsih
            }
        })
        rollingTextViewTotalPrice.setText((binding.numberpickerMainPicker.value * food?.price!!).toString())

        rollingTextViewOrderSize = binding.alphaBetViewOrdersize
        rollingTextViewOrderSize.animationDuration = 400L
        rollingTextViewOrderSize.charStrategy = Strategy.CarryBitAnimation()
        rollingTextViewOrderSize.addCharOrder(Alphabet)
        rollingTextViewOrderSize.animationInterpolator = AccelerateDecelerateInterpolator()

        rollingTextViewOrderSize.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finsih
            }
        })
        rollingTextViewOrderSize.setText((binding.numberpickerMainPicker.value * food?.price!!).toString())

    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        rollingTextViewOrderSize.setText("Ksh ${newVal * food.quantity!!}")
            rollingTextViewTotalPrice.setText((newVal * food.price!!).toString())
    }
}
 */