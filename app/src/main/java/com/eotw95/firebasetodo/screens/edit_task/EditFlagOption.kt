package com.eotw95.firebasetodo.screens.edit_task

enum class EditFlagOption {
    On,
    Off;

    companion object {
        fun getBoolean(option: String): Boolean = option == On.name
    }
}