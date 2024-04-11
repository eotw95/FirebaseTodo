package com.eotw95.firebasetodo.screens.tasks

enum class TaskActionOptions(val title: String) {
    EditTask("EditTask"),
    ToggleFlag("ToggleFlag"),
    DeleteTask("DeleteTask");

    companion object {
        val options: List<String>
            get() = getTitles()

        private fun getTitles(): List<String> {
            val titles = mutableListOf<String>()
            values().forEach { value ->
                titles.add(value.title)
            }
            return titles
        }
    }
}