package ru.netology

object NoteService {
    private val notes = mutableListOf<Note>()
    private var noteIdValue = 0
    private val comments = mutableListOf<Comment>()
    private var commentId = 0

    fun countNotes(): Int {
        return notes.size
    }

    fun countComments(): Int {
        return comments.size
    }

    fun deleteAll() {
        notes.clear()
        comments.clear()
        noteIdValue = 0
        commentId = 0
    }

    fun add(ownerId: Int, title: String, text: String): Int {
        if (title.isNotEmpty() && text.isNotEmpty() && ownerId > 0) {
            noteIdValue++
            val note = Note(noteIdValue, ownerId, title, text)
            notes += note
            return noteIdValue
        }
        return 0
    }

    fun delete(noteId: Int): Boolean {
        val index = findIndexNote(noteId)
        if (index >= 0) {
            deleteNoteComments(notes[index].id)
            return notes.remove(notes[index])
        }
        return false
    }

    private fun deleteNoteComments(noteId: Int) {
        for (index in 0 until comments.size) {
            if (comments[index].noteId == noteId) {
                comments.remove(comments[index])
            }
        }
    }

    private fun findIndexNote(noteId: Int): Int {
        for (index in 0 until notes.size) {
            if (notes[index].id == noteId) {
                return index
            }
        }
        return -1
    }

    private fun findIndexComment(commentId: Int): Int {
        for (index in 0 until comments.size) {
            if (comments[index].id == commentId) {
                return index
            }
        }
        return -1
    }

    fun edit(noteId: Int, ownerId: Int, title: String, text: String): Boolean {
        val index = findIndexNote(noteId)
        if (index >= 0) {
            if (notes[index].ownerId == ownerId) {
                notes[index] = notes[index].copy(title = title, text = text)
                return true
            }
        }
        return false
    }

    fun get(noteIds: List<Int>, ownerId: Int): MutableList<Note> {
        val noteList = mutableListOf<Note>()
        for (note in notes) {
            for (id in noteIds) {
                if (note.id == id && note.ownerId == ownerId) {
                    noteList.add(note)
                }
            }
        }
        return noteList
    }

    fun getById(noteId: Int, ownerId: Int): Note? {
        val index = findIndexNote(noteId)
        if (index >= 0) {
            if (notes[index].ownerId == ownerId) {
                return notes[index]
            }
        }
        return null
    }

    fun createComment(noteId: Int, ownerId: Int, message: String): Int {
        if (message.isNotEmpty() && ownerId > 0) {
            val index = findIndexNote(noteId)
            if (index >= 0) {
                commentId++
                val comment = Comment(commentId, noteId, ownerId, message)
                comments += comment
                return commentId
            }
        }
        return 0
    }

    fun deleteComment(commentId: Int, ownerId: Int): Boolean {
        val index = findIndexComment(commentId)
        if (index >= 0) {
            if (ownerId == comments[index].ownerId) {
                comments[index].delete = true
                return true
            }
        }
        return false
    }

    fun editComment(commentId: Int, ownerId: Int, message: String): Boolean {
        val index = findIndexComment(commentId)
        if (index >= 0) {
            if (comments[index].ownerId == ownerId) {
                comments[index] = comments[index].copy(message = message)
                return true
            }
        }
        return false
    }

    fun getComments(commentIds: List<Int>, ownerId: Int): MutableList<Comment> {
        val commentList = mutableListOf<Comment>()
        for (comment in comments) {
            for (id in commentIds) {
                if (comment.id == id && comment.ownerId == ownerId) {
                    commentList.add(comment)
                }
            }
        }
        return commentList
    }

    fun getRestoreComment(commentId: Int, ownerId: Int): Boolean {
        val index = findIndexComment(commentId)
        if (index >= 0) {
            if (ownerId == comments[index].ownerId) {
                comments[index].delete = false
                return true
            }
        }
        return false
    }

}