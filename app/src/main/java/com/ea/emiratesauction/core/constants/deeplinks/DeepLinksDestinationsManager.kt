package com.ea.emiratesauction.core.constants.deeplinks

interface DeepLinksDestinationsManager {
    val destinations: ArrayList<DeepLinksDestinationModel>
}

interface DeepLinksDestinationModel {
    val destinationClass: String
    val backStack: ArrayList<Any>
}