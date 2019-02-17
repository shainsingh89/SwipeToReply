package com.shain.messenger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shain.messenger.model.Message
import com.shain.messenger.model.MessageType
import kotlinx.android.synthetic.main.send_message_row.view.*
import java.util.*

/**
 * Created by Shain Singh on 2/2/19.
 */
class MessageAdapter(private val context: Context) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var messageList = ArrayList<Message>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(context).inflate(viewType, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.txtSendMsg.text = message.body
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewType(messageList.get(position))
    }

    private fun getItemViewType(message: Message): Int {
        return if (message.type == MessageType.SEND)
            R.layout.send_message_row
        else
            R.layout.received_message_row

    }

    fun setMessages(albumList: List<Message>) {
        this.messageList = albumList as ArrayList<Message>
        notifyDataSetChanged()
    }


    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtSendMsg = view.txtBody!!
    }
}