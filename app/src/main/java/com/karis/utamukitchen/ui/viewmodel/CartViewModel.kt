package com.karis.utamukitchen.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karis.utamukitchen.mainRepository.MainRepository
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Coroutines

class CartViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {
   var cartFood : MutableLiveData<List<Food>> = MutableLiveData()

    fun getFood(){
        Coroutines.io {
            var foods = mainRepository.getAllFoods()
            cartFood.postValue(foods)
        }
    }

}