package com.karis.utamukitchen.models

import javax.inject.Inject

data class Category (
    var category : String? = null,
    var categoryList: ArrayList<Food>? = null
)