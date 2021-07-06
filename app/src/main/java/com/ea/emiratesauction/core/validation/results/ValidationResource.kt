package com.ea.emiratesauction.core.validation.results

/**
 * ValidationResource that hold all types of results
 * */
sealed class ValidationResource {
    /**
     * Get all Results for all Rules as a bulk
     * */
    data class GroupResult(val results: List<String>) : ValidationResource()

    /**
     * Get First Result for The First Invalid Rul
     * */
    data class SingleResult(val result: String) : ValidationResource()

    /**
     * Empty In Case Of Valid Result
     * */
    object Empty : ValidationResource()
}