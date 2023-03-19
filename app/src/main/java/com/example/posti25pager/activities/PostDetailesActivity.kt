package com.example.posti25pager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posti25pager.databinding.ActivityPostDetailesBinding

class PostDetailesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}