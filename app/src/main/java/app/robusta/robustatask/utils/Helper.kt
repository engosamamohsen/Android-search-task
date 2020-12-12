package app.robusta.robustatask.utils

sealed class Helper() {
    companion object {
        val ITEM = 1
        val LOADING = 2
        val SERVER_ERROR = 3
        val SUBMIT = 200
        val RETRY = 500
        val SEARCH = "search"
        val EDIT = "edit"
        val ALLOW_EDIT = "allow_edit"
    }
}