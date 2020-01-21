package com.farouk.exomindtest.model.user

import com.farouk.exomindproject.data.model.user.Geo

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)