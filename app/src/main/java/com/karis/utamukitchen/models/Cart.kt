package com.karis.utamukitchen.models

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

data class Cart (
    var time: String? = null,
    var paid: Boolean? = false,
    var food : List<Food>? = null,
    var totalCost: Int? = 0,
    var date: String? = DateTime.now(DateTimeZone.UTC).toString(),
)