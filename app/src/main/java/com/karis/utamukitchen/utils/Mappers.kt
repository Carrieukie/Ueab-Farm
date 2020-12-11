package com.karis.utamukitchen.utils

import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.models.FoodDataBinding

object Mappers {
    fun toFood(foodDataBinding: FoodDataBinding): Food {
        return Food(
                foodDataBinding.id,
                foodDataBinding.image,
                foodDataBinding.name,
                foodDataBinding.price,
                foodDataBinding.price,
                foodDataBinding.numberOfItem,
                foodDataBinding.category,
                foodDataBinding.description,
        )
    }

    fun toFoodDataBinding(food: Food): FoodDataBinding {
        return FoodDataBinding(
                food.id,
                food.image,
                food.name,
                food.price,
                food.price,
                food.numberOfItem,
                food.category,
                food.description,
        )
    }
}