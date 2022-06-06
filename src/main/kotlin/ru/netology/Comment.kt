package ru.netology

data class Comment(val id: Int, val noteId: Int, val ownerId: Int, val message: String, var delete: Boolean = false) {

    fun print() {
        println("id: $id / noteId: $noteId / ownerId: $ownerId / message: $message")
    }
}

