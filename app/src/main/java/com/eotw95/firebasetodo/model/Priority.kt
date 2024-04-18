package com.eotw95.firebasetodo.model

enum class Priority {
    None,
    Low,
    Medium,
    High;

    companion object {
        fun getOptions(): List<String> {
            val titles = mutableListOf<String>()
            values().forEach {
                titles.add(it.name)
            }
            return titles
        }
        fun getByName(name: String): Priority {
            values().forEach { priority ->
                if (priority.name == name) return priority
            }
            return None
        }
    }
}