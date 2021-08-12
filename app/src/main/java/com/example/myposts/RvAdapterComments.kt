package com.example.myposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapterComments(var commentsList: List<Comments>):RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_layout,parent,false)
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComment= commentsList.get(position)
        holder.tvCommentName.text= currentComment.name
        holder.tvCommentEmail.text= currentComment.email
        holder.tvCommentBody.text= currentComment.body
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

}
class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var tvCommentName= itemView.findViewById<TextView>(R.id.tvCommentName)
    var tvCommentEmail= itemView.findViewById<TextView>(R.id.tvCommentEmail)
    var tvCommentBody= itemView.findViewById<TextView>(R.id.tvCommentBody)
}