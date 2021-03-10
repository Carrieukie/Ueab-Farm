package com.karis.utamukitchen.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karis.utamukitchen.di.MenuModule
import com.karis.utamukitchen.mainRepository.MainRepository
import com.karis.utamukitchen.models.Category
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Constants
import java.lang.reflect.Array
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {
    var ifExists = mainRepository.ifExists

    var foodslist  = MenuModule.provides_menu()

    var foods = MutableLiveData<List<Food>>()

    init {
        foods.postValue(foodslist.filter { it.category == Constants.BREAKFAST })
    }

    fun setFilter(filter : String){
        foods.postValue(foodslist.filter { it.category == filter })

    }

}