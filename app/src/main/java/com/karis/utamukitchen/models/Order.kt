package com.karis.utamukitchen.models

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

data class Order(
        var orderId: Int,
        var foodId: Int,
        var userId: Int,
        var quantity:Int,
        var price: Int,
        var date: String? = DateTime.now(DateTimeZone.UTC).toString(),
)