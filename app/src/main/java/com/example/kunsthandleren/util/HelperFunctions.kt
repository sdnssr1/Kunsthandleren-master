package com.example.kunsthandleren.util

import com.example.kunsthandleren.PurchaseItem
import kotlin.collections.forEach

class HelperFunctions {



}

fun calculateTotalPrice(purchaseItemsList: List<PurchaseItem>): Float {
    var total = 0F
    purchaseItemsList.forEach {

        total += it.getCost()

    }

    return total

}