package com.example.ideasai.core.testing.data

import com.example.ideasai.core.data.model.Idea

object TestData {

    val ideas = listOf(
        Idea(
            name = "name_1",
            topic = "topic_1"
        ),
        Idea(
            name = "name_2",
            topic = "topic_2"
        ),
        Idea(
            name = "name_3",
            topic = "topic_3"
        )
    )

    val about = "about"
    val purpose = "purpose"
    val topic = "topic_1"
    val language = "english"

    val errorMessage = "Not Found"

}