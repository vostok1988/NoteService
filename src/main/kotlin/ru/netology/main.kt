package ru.netology

fun main() {

    println(NoteService.add(666, "Заголовок 1", "Текст заметки 1"))
    println(NoteService.add(666, "Заголовок 2", "Текст заметки 2"))
    println(NoteService.delete(0))
    println(NoteService.countNotes())
    NoteService.deleteAll()
    println(NoteService.countNotes())

    println(NoteService.edit(2, 666, "Изменил заголовок", "Изменил текст"))
    NoteService.getById(1, 666)?.print()
    NoteService.getById(2, 666)?.print()
    NoteService.createComment(2, 666, "Коммент 1")
    NoteService.createComment(2, 666, "Коммент 2")
    NoteService.createComment(2, 666, "Коммент 3")
    println(NoteService.countComments())
    NoteService.deleteComment(4, 666)
    println(NoteService.countComments())
}





