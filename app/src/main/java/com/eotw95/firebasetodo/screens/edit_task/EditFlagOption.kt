package com.eotw95.firebasetodo.screens.edit_task

enum class EditFlagOption {
    On,
    Off;

    companion object {
        fun getBoolean(option: String): Boolean = option == On.name
        fun getOptions(): List<String> {
            val titles = mutableListOf<String>()
            values().forEach {
                titles.add(it.name)
            }
            return titles
        }
        fun getByCheckedState(state: Boolean): EditFlagOption {
            return if (state) On else Off
        }
    }
}