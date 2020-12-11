package com.karis.utamukitchen.utils

import com.karis.utamukitchen.models.Food

interface Onclick {
    fun orderActivity(food: Food)
}