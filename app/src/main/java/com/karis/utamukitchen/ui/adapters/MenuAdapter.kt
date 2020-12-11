package com.karis.utamukitchen.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karis.utamukitchen.R
import com.karis.utamukitchen.models.Category
import com.karis.utamukitchen.utils.Onclick
import kotlinx.android.synthetic.main.categoriesitem.view.*


class MenuAdapter(private var onclick: Onclick) : ListAdapter<Category, MenuAdapter.ViewHolder>(
    DIFF_CALLBACK
){

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.categoriesitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val category = getItem(position)

        holder.itemView.textViewCategory.text = category.category
        holder.itemView.recyclerViewCategory.layoutManager = LinearLayoutManager(context);
        var adapter = FoodAdapter(onclick)
        holder.itemView.recyclerViewCategory.adapter = adapter
        adapter.submitList(category.categoryList)
        setFadeAnimation(holder.itemView);

        holder.itemView.setOnClickListener {
        }
    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}