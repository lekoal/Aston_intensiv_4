package com.lekoal.astonintensiv4.part_2.data

object InitialUsers {
    fun get() = listOf(
        UserInfo(
            id = 1,
            name = "Владимир",
            surname = "Баранов",
            phone = "89019846658",
            imageLink = "https://loremflickr.com/150/200/dog"
        ),
        UserInfo(
            id = 2,
            name = "Мария",
            surname = "Максимова",
            phone = "89217465384",
            imageLink = "https://loremflickr.com/150/200/cat"
        ),
        UserInfo(
            id = 3,
            name = "Иван",
            surname = "Свешников",
            phone = "89005492038",
            imageLink = "https://loremflickr.com/150/200/bear"
        ),
        UserInfo(
            id = 4,
            name = "Вероника",
            surname = "Смирнова",
            phone = "89456638452",
            imageLink = "https://loremflickr.com/150/200/rabbit"
        )
    )
}