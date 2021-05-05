package com.red.models

import java.time.LocalDateTime

data class Transaction(
    var id: Int? = null,
    var userId: Int? = null,
    var name: String = "",
    var totalCost: Double = 0.0,
    var currency: String = "HUF",
    var date: LocalDateTime = LocalDateTime.now(),
    var categories: Array<Int>,
    var elements: Array<Int>,
    var type: String? = null
)
