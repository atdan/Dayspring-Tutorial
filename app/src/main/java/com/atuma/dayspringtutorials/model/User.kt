package com.atuma.dayspringtutorials.model

data class User(
    var surname: String? = null,
    var firstname: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var matric: String? = null,
    var faculty: String? = null,
    var department: String? = null,
    var image: String? = null
){
    companion object{
        const val FIELD_SURNAME = "surname"
        const val FIELD_FIRST_NAME = "firstname"
        const val FIELD_EMAIL = "email"
        const val FIELD_PHONE = "phone"
        const val FIELD_MATRIC = "matric"
        const val FIELD_DEPARTMENT = "department"

        const val FIELD_FACULTY = "faculty"

        const val FIELD_IMAGE = "image"

    }

}