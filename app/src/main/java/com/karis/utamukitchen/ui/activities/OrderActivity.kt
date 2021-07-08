package com.karis.utamukitchen.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.NumberPicker
import android.widget.TextView
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
            finish()
        }

    }

    private fun initializeViews(binding: ActivityOrderBinding) {
        findViewById<TextView>(R.id.txtCupPrice).text = (food.price).toString()
        binding.numberpickerMainPicker.minValue = 1
        binding.numberpickerMainPicker.maxValue = 20
        binding.numberpickerMainPicker.setOnValueChangedListener(this)
        rollingTextViewTotalPrice = binding.alphaBetViewTotalPrice
        rollingTextViewTotalPrice.animationDuration = 1000L
        rollingTextViewTotalPrice.charStrategy = Strategy.NormalAnimation()
        rollingTextViewTotalPrice.addCharOrder(Alphabet)
        rollingTextViewTotalPrice.animationInterpolator = AccelerateDecelerateInterpolator()

        rollingTextViewTotalPrice.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finish
            }
        })
        rollingTextViewTotalPrice.setText((binding.numberpickerMainPicker.value * food?.price!!).toString())

        rollingTextViewOrderSize = binding.alphaBetViewOrdersize
        rollingTextViewOrderSize.animationDuration = 400L
        rollingTextViewOrderSize.charStrategy = Strategy.NormalAnimation()
        rollingTextViewOrderSize.addCharOrder(Alphabet)
        rollingTextViewOrderSize.animationInterpolator = AccelerateDecelerateInterpolator()

        rollingTextViewOrderSize.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finish
            }
        })

        rollingTextViewOrderSize.setText((binding.numberpickerMainPicker.value * food?.quantity!!).toString())
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        viewModel.quantity = newVal
        rollingTextViewTotalPrice.setText((newVal * food.price!!).toString())
        rollingTextViewOrderSize.setText((newVal * food.quantity!!).toString())
    }

    fun send(view: View) {}


}