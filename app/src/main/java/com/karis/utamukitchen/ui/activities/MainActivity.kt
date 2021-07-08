package com.karis.utamukitchen.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.karis.utamukitchen.R
import com.karis.utamukitchen.data.firebase.FirebaseUtil
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.ui.adapters.FoodAdapter
import com.karis.utamukitchen.ui.viewmodel.MainViewModel
import com.karis.utamukitchen.utils.Constants.CEREALS
import com.karis.utamukitchen.utils.Constants.FRUITS
import com.karis.utamukitchen.utils.Constants.MEAT
import com.karis.utamukitchen.utils.Constants.MILK
import com.karis.utamukitchen.utils.Constants.OILS
import com.karis.utamukitchen.utils.Constants.VEGETABLES
import com.karis.utamukitchen.utils.Onclick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,Onclick, AdapterView.OnItemSelectedListener{
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var objects : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        objects = arrayOf(
            CEREALS,
            VEGETABLES,
            OILS,
            FRUITS,
            MEAT,
            MILK
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

    override fun onResume() {
        super.onResume()
        FirebaseUtil.callReference(this)
        FirebaseUtil.attachListener()
    }

    override fun onStop() {
        super.onStop()
        FirebaseUtil.detachListener()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.logout){
            FirebaseAuth.getInstance().signOut()
        }

        return super.onOptionsItemSelected(item)
    }

}