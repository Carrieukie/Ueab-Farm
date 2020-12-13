package com.karis.utamukitchen.ui.viewmodel

import androidx.databinding.Bindable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karis.utamukitchen.mainRepository.MainRepository
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Coroutines

class OrderViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    var quantity = 1
    var food: Food? = null

    fun addFood(food: Food){
        food.numberOfItem = quantity
        Coroutines.io {
            mainRepository.addFood(food)
        }
    }


}