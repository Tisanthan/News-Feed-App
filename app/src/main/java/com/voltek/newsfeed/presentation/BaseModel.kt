package com.voltek.newsfeed.presentation

/**
 * Base ViewModel class.
 *
 * @param subscriber function, that will be called when model updates
 */
abstract class BaseModel(val subscriber: (BaseModel) -> Unit) {

    /**
     * Call this method to render new model state
     */
    fun update() {
        subscriber.invoke(this@BaseModel)
    }
}
