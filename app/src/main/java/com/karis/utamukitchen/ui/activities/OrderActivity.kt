package com.karis.utamukitchen.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.karis.utamukitchen.R
import com.karis.utamukitchen.databinding.ActivityOrderBinding
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.viewmodel.OrderViewModel
import com.karis.utamukitchen.utils.Mappers
import com.yy.mobile.rollingtextview.CharOrder.Alphabet
import com.yy.mobile.rollingtextview.RollingTextView
import com.yy.mobile.rollingtextview.strategy.Strategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_content.*


@AndroidEntryPoint
class OrderActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {

    private val viewModel by viewModels<OrderViewModel>()
    lateinit var rollingTextView: RollingTextView
    lateinit var food:Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityOrderBinding =  DataBindingUtil.setContentView(
            this,
            R.layout.activity_order
        )
        food = intent.getParcelableExtra<Food>("food")!!
        binding.numberpickerMainPicker.minValue = 1
        binding.numberpickerMainPicker.maxValue = 20
        binding.numberpickerMainPicker.setOnValueChangedListener(this)
        rollingTextView = findViewById(R.id.alphaBetView)
        rollingTextView.animationDuration = 400L
        rollingTextView.charStrategy = Strategy.NormalAnimation()
        rollingTextView.addCharOrder(Alphabet)
        rollingTextView.animationInterpolator = AccelerateDecelerateInterpolator()
        rollingTextView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finsih
            }
        })
        rollingTextView.setText((binding.numberpickerMainPicker.value * food?.price!!).toString())
        Glide.with(this)
            .load(food?.image)
            .circleCrop()
            .into(binding.imageViewFood)

        binding.food = food?.let { Mappers.toFoodDataBinding(it) }

        binding.btnAdd.setOnClickListener {
            if (food != null) {
                viewModel.addFood(food)
            }

            Intent(applicationContext, CartActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        rollingTextView.setText("Ksh. ${newVal * food?.price!!}")
    }
}