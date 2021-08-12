package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myposts.databinding.ActivityViewPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPostBinding
    var postId= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postId= intent.getIntExtra("POST_ID",0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getPost()
        getComment()

    }
    fun    getPost(){
        var retrofit= ApiClient.buildService(ApiInterface::class.java)
        var request= retrofit.getPostById(postId)
        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    binding.tvPostTitle.text= response.body()?.title
                    binding.tvPostBody.text= response.body()?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun getComment(){
        var retrofit=ApiClient.buildService(ApiInterface::class.java)
        var request= retrofit.getCommentPostbyId(postId)
        request.enqueue(object : Callback<List<Comments>?> {
            override fun onResponse(
                call: Call<List<Comments>?>,
                response: Response<List<Comments>?>
            ) {
                if(response.isSuccessful){
                    var comments= response.body()
                    if (comments!=null){
                        var myAdapter= RvAdapterComments(comments)
                        binding.rvComments.adapter= myAdapter
                        binding.rvComments.layoutManager= LinearLayoutManager(baseContext)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comments>?>, t: Throwable) {
               Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}