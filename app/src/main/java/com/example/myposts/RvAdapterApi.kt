package com.example.myposts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RvAdapterApi(var context: Context, var postsList: List<Post>): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.post_layout,parent,false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPosts= postsList.get(position)
        holder.userId.text= currentPosts.userId.toString()
        holder.title.text= currentPosts.title
        holder.body.text= currentPosts.body
        holder.cdPosts.setOnClickListener {
            var intent = Intent(context, ViewPostActivity::class.java)
            intent.putExtra("POST_ID",currentPosts.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return postsList.size
    }
}
class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var userId= itemView.findViewById<TextView>(R.id.tvUserId)
    var title= itemView.findViewById<TextView>(R.id.tvTitle)
    var body = itemView.findViewById<TextView>(R.id.tvBody)
    var cdPosts= itemView.findViewById<CardView>(R.id.cdPosts)

}