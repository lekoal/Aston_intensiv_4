package com.lekoal.astonintensiv4.part_2.data

import java.io.Serializable

data class UserInfo(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val imageLink: String
) : Serializable