package com.ea.emiratesauction.core.constants.deeplinks

/*
* DeepLinksDestinationsManager  has arraylist of DeepLinksDestinationModel that contains
* all keys available for the deeplink with backstack option for each screen
* */
interface DeepLinksDestinationsManager {
    val destinations: ArrayList<DeepLinksDestinationModel>
}

interface DeepLinksDestinationModel {
    val destinationClass: String
    val backStack: ArrayList<Any>
}