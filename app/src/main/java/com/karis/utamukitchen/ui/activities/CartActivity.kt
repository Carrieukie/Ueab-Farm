package com.karis.utamukitchen.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Canvas
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.karis.utamukitchen.R
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.adapters.CartAdapter
import com.karis.utamukitchen.ui.viewmodel.CartViewModel
import com.karis.utamukitchen.utils.Onclick
import com.yy.mobile.rollingtextview.CharOrder
import com.yy.mobile.rollingtextview.RollingTextView
import com.yy.mobile.rollingtextview.strategy.Strategy
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.android.synthetic.main.activity_cart.*


@AndroidEntryPoint
class CartActivity : AppCompatActivity(), Onclick, NumberPicker.OnValueChangeListener  {

    private val viewModel by viewModels<CartViewModel>()
    private lateinit var mbottomSheet: ConstraintLayout
    private lateinit var bottomsheetBehaviour: BottomSheetBehavior<ConstraintLayout>
    private lateinit var numberpickerMainPicker: NumberPicker
    private lateinit var rollingTextViewTotalPrice: RollingTextView
    private lateinit var rollingTextViewOrderSize: RollingTextView
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recyclerView_Cart.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter(this)
        recyclerView_Cart.adapter = adapter
        viewModel.cartFood.observe(this, {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        mbottomSheet = findViewById(R.id.bottom_sheet)
        bottomsheetBehaviour = BottomSheetBehavior.from(mbottomSheet)
        bottomsheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
        viewModel.totalPrice.observe(this, {
            when (it) {
                null -> {
                    textView_totalPrice.text = "Ksh 0"
                }
                else -> {
                    textView_totalPrice.text = "Ksh $it"
                }
            }
        })


        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.delete(adapter.getFoodAt(viewHolder.adapterPosition))
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addBackgroundColor(
                            ContextCompat.getColor(
                                this@CartActivity, R.color.starbuck_green
                            )
                        )
                        .addActionIcon(R.drawable.ic_round_delete)
                        .create()
                        .decorate()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )

                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView_Cart)

    }

    override fun orderActivity(food: Food) {
        viewModel.food = food
        bottomsheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        findViewById<TextView>(R.id.txtItemPriceEdit).text = (viewModel.food.price).toString()
        findViewById<TextView>(R.id.textViewNameEdit).text = viewModel.food.name
        findViewById<TextView>(R.id.text_description).text = viewModel.food.description
        var imageview  = findViewById<ImageView>(R.id.imageViewFoodEdit)
        Glide.with(this)
                .load(food?.image)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageview)
        findViewById<TextView>(R.id.btn_edit).setOnClickListener {
            viewModel.update()
            bottomsheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
        }
        numberpickerMainPicker = findViewById(R.id.numberpicker_edit_picker)
        numberpickerMainPicker.minValue = 1
        numberpickerMainPicker.maxValue = 20
        numberpickerMainPicker.value = viewModel.food.numberOfItem!!
        numberpickerMainPicker.setOnValueChangedListener(this)
        rollingTextViewTotalPrice = findViewById(R.id.alphaBetViewTotalPriceEdit)
        rollingTextViewTotalPrice.animationDuration = 1000L
        rollingTextViewTotalPrice.charStrategy = Strategy.NormalAnimation()
        rollingTextViewTotalPrice.addCharOrder(CharOrder.Alphabet)
        rollingTextViewTotalPrice.animationInterpolator = AccelerateDecelerateInterpolator()

        rollingTextViewTotalPrice.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finish
            }
        })
        rollingTextViewTotalPrice.setText((numberpickerMainPicker.value * viewModel.food?.price!!).toString())

        rollingTextViewOrderSize = findViewById(R.id.alphaBetViewOrdersizeEddit)
        rollingTextViewOrderSize.animationDuration = 400L
        rollingTextViewOrderSize.charStrategy = Strategy.NormalAnimation()
        rollingTextViewOrderSize.addCharOrder(CharOrder.Alphabet)
        rollingTextViewOrderSize.animationInterpolator = AccelerateDecelerateInterpolator()

        rollingTextViewOrderSize.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                //finish
            }
        })

        rollingTextViewOrderSize.setText((numberpickerMainPicker.value * viewModel.food?.quantity!!).toString())
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        viewModel.food.numberOfItem = newVal
        rollingTextViewTotalPrice.setText((newVal * viewModel.food.price!!).toString())
        rollingTextViewOrderSize.setText((newVal * viewModel.food.quantity!!).toString())
    }

    override fun onBackPressed() {
        if (bottomsheetBehaviour.state != BottomSheetBehavior.STATE_HIDDEN){
            bottomsheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
        }else{
            super.onBackPressed()
        }
    }
}