package com.ea.emiratesauction.core.constants.network.hosts

import com.ea.emiratesauction.BuildConfig.BASE_URL

/**
 * The Identifiers of the different base urls which is used across the whole app
 *
 * @param baseUrl The string value of the base url
 */
enum class Host(val baseUrl:String) {
    DEFAULT_BASE(BASE_URL)
}