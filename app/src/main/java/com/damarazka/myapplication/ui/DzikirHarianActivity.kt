package com.damarazka.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.damarazka.doadandzikirapp.R
import com.damarazka.doadandzikirapp.databinding.ActivityDzikirHarianBinding
import com.damarazka.myapplication.adapter.DoaDzikirAdapter
import com.damarazka.myapplication.model.DoaDzikirItem

class DzikirHarianActivity : AppCompatActivity() {
    private var _binding: ActivityDzikirHarianBinding? = null
    private val binding get() = _binding as ActivityDzikirHarianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_dzikir_harian)
        _binding = ActivityDzikirHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        providingDzikirDatas()
        initView()
    }

    private fun initView() {
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(providingDzikirDatas())
        binding.rvDzikirHarian.adapter = mAdapter
        binding.rvDzikirHarian.layoutManager = LinearLayoutManager(this)
    }

    private fun providingDzikirDatas(): ArrayList<DoaDzikirItem> {
        // data set of dzikir harian is located in string.xml
        // we need to get string-array from string.xml put into a variable
        // resources is a variable from AppCompat which getting access to Resource directory
        // from resources we can call resoutce directory like as drawable, layout, values (string, theme, color)
        //so, now variable titleDzikir containing datas as Array String arr_dzikir_doa_harian
    val titleDzikir = resources.getStringArray(R.array.arr_dzikir_doa_harian)
        val arabicDzikir = resources.getStringArray(R.array.arr_lafaz_dzikir_doa_harian)
        val translateDzikir = resources.getStringArray(R.array.arr_terjemah_dzikir_doa_harian)

        val listData = arrayListOf<DoaDzikirItem>()
        for (i in titleDzikir.indices){
            val data = DoaDzikirItem(
            titleDzikir[i],
            arabicDzikir[i],
            translateDzikir[i]
            )
            listData.add(data)
        }
        return listData
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