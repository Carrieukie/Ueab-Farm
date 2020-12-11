package com.karis.utamukitchen.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karis.utamukitchen.mainRepository.MainRepository
import com.karis.utamukitchen.models.Food

class CartViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {
   var cartFood : MutableLiveData<List<Food>> = MutableLiveData()

    fun getFood(){
        var foods = mainRepository.getAllFoods().value
        cartFood.postValue(foods)
    }

}