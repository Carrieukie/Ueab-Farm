package com.karis.utamukitchen.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.karis.utamukitchen.mainRepository.MainRepository
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Coroutines

class OrderViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun addFood(food: Food){
        Coroutines.io {
            mainRepository.addFood(food)
        }
    }


}