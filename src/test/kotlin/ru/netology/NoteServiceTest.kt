package ru.netology

import org.junit.After
import org.junit.Test
import org.junit.Assert.*

class NoteServiceTest {

    @After
    fun clear() {
        NoteService.deleteAll()
    }

    @Test
    fun countNotes() {
    }

    @Test
    fun countComments() {
    }

    @Test
    fun addTrue() {
        val expected = 1
        NoteService.add(666, "Заголовок1", "текст заметки1")
        val note = NoteService.getById(1, 666)
        if (note != null) {
            note.print()
            println(note.title)
            println(note.text)
        }
        val actual = NoteService.countNotes()
        assert(expected == actual)
    }

    @Test
    fun addFalseTitle() {
        val expected = 0
        val actual = NoteService.add(0, "", "")
        assert(expected == actual)
    }

    @Test
    fun addFalseText() {
        val expected = 0
        NoteService.add(666, "заголовок", "")
        val actual = NoteService.countNotes()
        assert(expected == actual)
    }

    @Test
    fun deleteTrue() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.add(666, "Заголовок2", "текст заметки2")
        NoteService.createComment(2, 666, "текст комментария")
        val actual = NoteService.delete(2)
        println(actual)
        assertTrue(actual)
    }

    @Test
    fun deleteFalse() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.add(666, "Заголовок2", "текст заметки2")
        val actual = NoteService.delete(33)
        assertFalse(actual)
    }

    @Test
    fun editTrue() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        val actual = NoteService.edit(1, 666, "новый заголовок", "новый текст")
        assertTrue(actual)
    }

    @Test
    fun editFalseOwnerId() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        val actual = NoteService.edit(1, 555, "новый заголовок", "новый текст")
        assertFalse(actual)
    }

    @Test
    fun editFalseNoteId() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        val actual = NoteService.edit(3, 666, "новый заголовок", "новый текст")
        assertFalse(actual)
    }

    @Test
    fun getTrue() {
        val expected = 3
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.add(666, "Заголовок2", "текст заметки2")
        NoteService.add(666, "Заголовок3", "текст заметки3")
        val noteIds = listOf(1, 2, 3)
        val notesList = NoteService.get(noteIds, 666)
        val actual = notesList.size
        assertEquals(expected, actual)
    }

    @Test
    fun getFalse() {
        val expected = 0
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.add(666, "Заголовок2", "текст заметки2")
        NoteService.add(666, "Заголовок3", "текст заметки3")
        val noteIds = listOf(4, 5, 6)
        val notesList = NoteService.get(noteIds, 555)
        val actual = notesList.size
        assertEquals(expected, actual)
    }

    @Test
    fun getByIdNote() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        val note = NoteService.getById(1, 666)
        assertFalse(note == null)
    }

    @Test
    fun getByIdNull() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        val note = NoteService.getById(1, 555)
        assertTrue(note == null)
    }

    @Test
    fun createComment() {
        val expected = 1
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария")
        val actual = NoteService.countComments()
        assert(expected == actual)
    }

    @Test
    fun createCommentFalseId() {
        val expected = 0
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(2, 555, "")
        val actual = NoteService.countComments()
        assert(expected == actual)
    }

    @Test
    fun deleteCommentTrue() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария")
        val actual = NoteService.deleteComment(1, 666)
        assertTrue(actual)
    }

    @Test
    fun deleteCommentFalse() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария")
        val actual = NoteService.deleteComment(2, 666)
        assertFalse(actual)
    }

    @Test
    fun editCommentTrue() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария")
        val actual = NoteService.editComment(1, 666, "новый текст комментария")
        assertTrue(actual)
    }

    @Test
    fun editCommentFalseOwnerId() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария")
        val actual = NoteService.editComment(1, 555, "новый текст комментария")
        assertFalse(actual)
    }

    @Test
    fun editCommentFalseCommentId() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария")
        val actual = NoteService.editComment(3, 555, "новый текст комментария")
        assertFalse(actual)
    }

    @Test
    fun getCommentsTrue() {
        val expected = 3
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария1")
        NoteService.createComment(1, 666, "текст комментария2")
        NoteService.createComment(1, 666, "текст комментария3")
        val commentIds = listOf(1, 2, 3)
        val commentsList = NoteService.getComments(commentIds, 666)
        commentsList[0].print()
        println(commentsList[0].message)
        println(commentsList[0].delete)
        val actual = commentsList.size
        assertEquals(expected, actual)
    }

    @Test
    fun getCommentsFalse() {
        val expected = 0
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария1")
        NoteService.createComment(1, 666, "текст комментария2")
        NoteService.createComment(1, 666, "текст комментария3")
        val commentIds = listOf(1, 2, 3)
        val commentsList = NoteService.getComments(commentIds, 555)
        val actual = commentsList.size
        assertEquals(expected, actual)
    }

    @Test
    fun getRestoreCommentTrue() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария1")
        NoteService.deleteComment(1, 666)
        val actual = NoteService.getRestoreComment(1, 666)
        assertTrue(actual)
    }

    @Test
    fun getRestoreCommentFalse() {
        NoteService.add(666, "Заголовок1", "текст заметки1")
        NoteService.createComment(1, 666, "текст комментария1")
        NoteService.deleteComment(1, 666)
        val actual = NoteService.getRestoreComment(1, 555)
        assertFalse(actual)
    }
}