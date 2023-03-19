package com.example.posti25pager.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.posti25pager.databinding.ActivityPostDetailesBinding
import com.example.posti25pager.models.Post
import com.example.posti25pager.tools.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PostDetailesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailesBinding
    lateinit var pref: SharedPreferences
    lateinit var currentPost: Post
    var movingMode=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        currentPost = loadCurrentPost()
        pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, currentPost.postNum).apply()
        movingMode = pref.getString(SHARPREF_MOVING_BACKGROUND, FALSE).toString()
        setMovingModeTextBtn()
        drawHeadline()
        btnSetting()
       }

    private fun setMovingModeTextBtn() {
        if (movingMode== FALSE){
            binding.movingModeBtn.text="סטטי"
        }else{
            binding.movingModeBtn.text="דינמי"
        }
    }

    private fun btnSetting() {
        val btn=binding.movingModeBtn
        btn.setOnClickListener {
            if (movingMode== FALSE){
                movingMode= TRUE
                pref.edit().putString(SHARPREF_MOVING_BACKGROUND, TRUE).apply()
            } else{
                movingMode= FALSE
                pref.edit().putString(SHARPREF_MOVING_BACKGROUND, FALSE).apply()
            }
            setMovingModeTextBtn()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.helpBtn.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
            finish()
        }
        binding.definitionBtn.setOnClickListener {
            startActivity(Intent(this, SetupActivity::class.java))
            finish()

        }
    }

    fun loadCurrentPost(): Post {
        val gson = Gson()
        val json: String? = pref.getString(SHARPREF_CURRENT_POST, null)
        val type: Type = object : TypeToken<Post>() {}.type
        val post: Post = gson.fromJson(json, type)
        return post
    }

    private fun drawHeadline() {
//        val st = "  פוסט מספר: " + currentPost.postNum.toString()
//        val st = " מספר: " + currentPost.postNum.toString()
        val st =  currentPost.postNum.toString()
        binding.postNumber.text = st
        // logi("PostDetailsActivity  233  post=$currentPost    \n post.postText.size= ${currentPost.postText.size}")
        drawPostText()
    }

    private fun drawPostText() {
        val combineText = currentPost.postText.joinToString(separator = "\n")
        binding.textPost.text = combineText
        binding.textPost.setHorizontallyScrolling(false)

    }
    fun logi(message: String) {
        Log.i("gg", message)
    }

}


