package com.karis.utamukitchen.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.karis.utamukitchen.mainRepository.MainRepository

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {
    var ifExists = mainRepository.ifExists
}