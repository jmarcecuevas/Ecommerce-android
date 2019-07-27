package com.marcecuevas.hotelsapp.utils

import android.content.Context

class Font {

    var familyName: String?
    var configurations: MutableMap<String,String>

    companion object{
        val instance = Font()
    }

    init {
        this.familyName = "Gotham"
        this.configurations = HashMap<String,String>()
    }

    fun setFamilyName(context: Context,familyName: String){
        this.familyName = familyName
        val resID = context.resources.getIdentifier(familyName,"array",context.packageName)
        if (resID == 0) {
            this.familyName = null
            return
        }
        val fonts: Array<String> = context.resources.getStringArray(resID)
        for (font in fonts){
            val parts = font.split("\\|".toRegex())
            if (parts.size > 0){
                this.configurations.put(parts.get(0),parts.get(1))
            }
        }
    }

    fun defaultPath(): String? {
        return this.pathForVariable(FontVariable.regular)
    }

    fun pathForVariable(variable: FontVariable): String? {
        return configurations.get(variable.name)
    }
}

enum class FontVariable{
    regular,
    light,
    black,
    bold
}