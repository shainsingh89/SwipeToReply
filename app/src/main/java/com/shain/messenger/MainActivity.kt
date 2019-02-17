package com.shain.messenger

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.shain.messenger.model.Message
import com.shain.messenger.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var messageList = ArrayList<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        val adapter = MessageAdapter(this)
        recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        val messageSwipeController = MessageSwipeController(this, object : SwipeControllerActions {
            override fun showReplyUI(position: Int) {
                showQuotedMessage(messageList[position])
            }
        })

        val itemTouchHelper = ItemTouchHelper(messageSwipeController)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        send_button.setOnClickListener {
            if (!edit_message.text.trim().isEmpty()) {
                hideReplyLayout()
                mainActivityViewModel.addMessage(edit_message.text.trim().toString())
                edit_message.text.clear()
            }
        }

        mainActivityViewModel.getDisplayMessage().observe(this, Observer<List<Message>> { messages ->
            messageList.clear()
            messageList.addAll(messages)
            adapter.setMessages(messages)
        })

        cancelButton.setOnClickListener {
            hideReplyLayout()
        }
    }

    private fun hideReplyLayout() {
        reply_layout.visibility = View.GONE
    }

    private fun showQuotedMessage(message: Message) {
        edit_message.requestFocus()
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager?.showSoftInput(edit_message, InputMethodManager.SHOW_IMPLICIT)
        txtQuotedMsg.text = message.body
        reply_layout.visibility = View.VISIBLE

    }

}
