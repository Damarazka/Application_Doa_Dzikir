package com.damarazka.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.damarazka.doadandzikirapp.R
import com.damarazka.doadandzikirapp.databinding.ActivityDetailArticleBinding
import com.damarazka.doadandzikirapp.databinding.ActivityMainBinding

class DetailArticleActivity : AppCompatActivity() {
    private var _binding : ActivityDetailArticleBinding? = null
    private val binding get() = _binding as ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        _binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titleArticle = intent.getStringExtra("title")
        val contentArticle = intent.getStringExtra("content")
        val imageArticle =  intent.getIntExtra("image",1)

        binding.apply {
            tvDetailTitle.text = titleArticle
            tvDetailContent.text = contentArticle
            imgDetailArticle.setImageResource(imageArticle)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}