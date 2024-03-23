package com.example.mobile1

import java.util.UUID
class Person(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val age: Int
) {

}