package com.ea.emiratesauction.core.utilities.deeplinks

class DeepLinksUtilities {
    companion object {
        fun getClassFromPackageName(className: String): Class<*> {
            return Class.forName(className.removePrefix("class "))
        }
    }

}