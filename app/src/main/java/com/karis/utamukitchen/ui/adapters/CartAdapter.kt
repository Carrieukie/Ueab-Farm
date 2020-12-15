package com.karis.utamukitchen.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karis.utamukitchen.R
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Onclick
import kotlinx.android.synthetic.main.cartitems.view.*


class CartAdapter(private var onclick: Onclick) : ListAdapter<Food, CartAdapter.ViewHolder>(
        DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.cartitems, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = getItem(position)

        setFadeAnimation(holder.itemView)
        holder.itemView.textViewCartName.text = food.name.toString()
        holder.itemView.textViewPieces.text = "${food.quantity.toString()} pcs"
        holder.itemView.textViewItemTotal.text = "Ksh. ${food.price?.times(food.numberOfItem!!)}"
        holder.itemView.textViewNumberOfItems.text = "x ${food.numberOfItem}"
        holder.itemView.textViewPriceCart.text = "Ksh ${food.price}"
        Glide.with(holder.itemView.context).load(food.image).circleCrop().into(
                holder.itemView.imageViewcart
        )
        holder.itemView.setOnClickListener {
            onclick.orderActivity(food)
        }
    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

    fun getFoodAt(adapterPosition: Int): Food {
        return  getItem(adapterPosition)
    }

    override fun submitList(list: List<Food>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.numberOfItem == newItem.numberOfItem
    }
}
