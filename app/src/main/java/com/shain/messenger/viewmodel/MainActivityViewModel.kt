package com.shain.messenger.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shain.messenger.model.Message
import com.shain.messenger.model.MessageType
import com.shain.messenger.utils.SampleMessages

/**
 * Created by Shain Singh on 2/2/19.
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var messagesList: MutableList<Message> = ArrayList()
    private val messagesListLiveData = MutableLiveData<List<Message>>()

    init {
        messagesList.addAll(SampleMessages.getSampleMessages())
        messagesListLiveData.value = messagesList
    }

    fun addMessage(body: String) {
        messagesList.add(Message(body, System.currentTimeMillis(), MessageType.SEND))
        messagesList.add(Message(body, System.currentTimeMillis(), MessageType.RECEIVED))
        messagesListLiveData.value = messagesList
    }

    fun getDisplayMessage(): LiveData<List<Message>> {
        return messagesListLiveData
    }

}