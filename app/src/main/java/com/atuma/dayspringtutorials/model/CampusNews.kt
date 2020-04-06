package com.atuma.dayspringtutorials.model

import java.util.*

data class CampusNews(
    var title: String? = null,
    var image: String? = null,
    var author: String? = null,
    var news: String? = null,
    var time: Date? = null
){
    companion object{
        const val FIELD_AUTHOR = "author"
        const val FIELD_TITLE = "title"
        const val FIELD_IMAGE = "image"
        const val FIELD_NEWS = "news"
        const val FIELD_TIME = "time"

    }
}