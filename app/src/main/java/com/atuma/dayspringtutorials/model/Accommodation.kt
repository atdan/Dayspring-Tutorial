package com.atuma.dayspringtutorials.model

import java.util.*

data class Accommodation(
    var name: String? = null,
    var image: String? = null,
    var images: MutableMap<String, String> = mutableMapOf(),
    var location: String? = null,
    var price: String? = null,
    var agent: String? = null,
    var features: String? = null,
    var time: Date? = null
){

}