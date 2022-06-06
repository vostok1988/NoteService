package ru.netology

data class Note(val id: Int, val ownerId: Int, val title: String, val text: String) {

    fun print() {
        println("ID: $id / ПОЛЬЗОВАТЕЛЬ: $ownerId / ЗАГОЛОВОК: $title / ТЕКСТ: $text")
    }
}