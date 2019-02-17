package com.shain.messenger.model

/**
 * Created by Shain Singh on 2/2/19.
 */
data class Message(var body: String, var time: Long, var type: Int)


object MessageType {
    const val SEND = 1
    const val RECEIVED = 2
}