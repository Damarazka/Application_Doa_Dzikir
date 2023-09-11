package com.damarazka.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.damarazka.doadandzikirapp.R
import com.damarazka.doadandzikirapp.databinding.ActivityMainBinding
import com.damarazka.myapplication.adapter.ArticleAdapter
import com.damarazka.myapplication.model.ArticleItem
import com.damarazka.myapplication.ui.DetailArticleActivity
import com.damarazka.myapplication.ui.DzikirHarianActivity
import com.damarazka.myapplication.ui.DzikirSetiapSaatActivity
import com.damarazka.myapplication.ui.QauliyahSholatActivity
import com.damarazka.myapplication.ui.pagipetang.PagiPetangActivity
import com.damarazka.myapplication.utils.onItemCallBack


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var dotSliderIndicator = arrayOf<ImageView?>()

    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            for (i in initData().indices) {
                dotSliderIndicator[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.inactive_dot)
                )
            }

            dotSliderIndicator[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this method is from dependencies splash screen API 12
        installSplashScreen()


        // setContentView is use to choose or display layout in activity

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()

        // declare variable to get in touch with view in layout activity_main
        // use findViewById to communicate with the view
        val cardQauliyahShalat = findViewById<CardView>(R.id.card_qauliyah)
        val cardDzikir = findViewById<CardView>(R.id.card_dzikir)
        val cardDzikirHarian = findViewById<CardView>(R.id.card_harian)
        val cardDzikirPagiPetang = findViewById<CardView>(R.id.card_pagipetang)
        // when cardview clicked it will be navigate to other page
        // Intent is use for navigate to other activit by clicking cardView
        cardQauliyahShalat.setOnClickListener {
            // Intent(argument content, which Activity you want to go)
            val intent = Intent(this, QauliyahSholatActivity::class.java)
            // start to go to destination activity
            startActivity(intent)
        }
        cardDzikir.setOnClickListener(this)
        cardDzikirHarian.setOnClickListener(this)
        cardDzikirPagiPetang.setOnClickListener(this)

        val mAdapter = ArticleAdapter()
        mAdapter.setData(initData())
        mAdapter.setOnItemCallback(object : onItemCallBack {
            override fun onItemClick(item: ArticleItem) {
            //navigate to DetailActivity
                val intent = Intent(applicationContext, DetailArticleActivity::class.java)
                //navigate to DetailActivity with datas using putExtra
                intent.putExtra("title",item.titleArticleItem)
                intent.putExtra("content", item.contentArticle)
                intent.putExtra("image", item.imageArticle)
                startActivity(intent)
            }
        })
        binding.vpArticle.registerOnPageChangeCallback(slidingCallback)
        binding.vpArticle.adapter = mAdapter

    }

    private fun setupViewPager() {
        dotSliderIndicator = arrayOfNulls(initData().size)

        for (i in initData().indices) {
            dotSliderIndicator[i] = ImageView(this)
            dotSliderIndicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.inactive_dot)
            )
            val params = LinearLayoutCompat.LayoutParams(35,35)
            params.marginStart = 8
            params.marginEnd = 8
            binding.dots.addView(dotSliderIndicator[i],params)
        }
    }

    private fun initData(): List<ArticleItem> {
        val titleArticle = resources.getStringArray(R.array.arr_title_artikel)
        val contentArticle = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArticle = resources.obtainTypedArray(R.array.arr_img_artikel)

        val listData = mutableListOf<ArticleItem>()
        for (i in titleArticle.indices) {
            val data = ArticleItem(
                titleArticle[i],
                imageArticle.getResourceId(i, 0),
                contentArticle[i],
            )
            listData.add(data)
        }
        imageArticle.recycle()
        return listData
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.card_dzikir -> startActivity(Intent(this, DzikirSetiapSaatActivity::class.java))
            R.id.card_harian -> startActivity(Intent(this, DzikirHarianActivity::class.java))
            R.id.card_pagipetang -> startActivity(Intent(this, PagiPetangActivity::class.java))

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}