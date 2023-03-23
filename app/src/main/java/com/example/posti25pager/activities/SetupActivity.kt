package com.example.posti25pager.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.posti25pager.articles.ArticlesActivity
import com.example.posti25pager.databinding.ActivitySetupBinding
import com.example.posti25pager.tools.*

class SetupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetupBinding
    private lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref=getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)

        setupButtons()
    }

    private fun setupButtons() {
        var btn:Button
        binding.btnArticles.setOnClickListener {
            startActivity(Intent(this, ArticlesActivity::class.java))
            finish()
        }
        btn=binding.btnSuffelOrder
        btn.text="סדר פוסטים:"+System.lineSeparator()+"אקראי"
        btn.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_SUFFEL).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn=binding.btnGradeOrder
        btn.text="סדר פוסטים:"+System.lineSeparator()+"לפי הדירוגים שלך"
        btn.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_GRADE).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn=binding.btnTimeOrder
        btn.text="סדר פוסטים:"+System.lineSeparator()+"לפי זמן פרסום"
        btn.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn=binding.btnRecommenderOrder
        btn.text="סדר פוסטים:"+System.lineSeparator()+"לפי מומלצים"
        btn.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_RECOMMENDED).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn=binding.btnPostNumDown
        btn.text="סדר פוסטים:"+System.lineSeparator()+"לפי מספרי פוסטים יורד"
        btn.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_POST_NUMBER_DOWN).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn=binding.btnPostNumUp
        btn.text="סדר פוסטים:"+System.lineSeparator()+"לפי מספרי פוסטים עולה"
        btn.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_POST_NUMBER_UP).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    fun logi(message: String) {
        Log.i("gg", message)
    }
}