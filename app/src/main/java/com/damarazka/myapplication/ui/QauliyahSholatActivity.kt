package com.damarazka.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.damarazka.doadandzikirapp.databinding.ActivityQauliyahSholatBinding
import com.damarazka.myapplication.adapter.DoaDzikirAdapter
import com.damarazka.myapplication.model.DataDoaDzikir

class QauliyahSholatActivity : AppCompatActivity() {

    private var _binding: ActivityQauliyahSholatBinding? = null
    private val binding get() = _binding as ActivityQauliyahSholatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        _binding = ActivityQauliyahSholatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(DataDoaDzikir.listDataQauliyah)
        binding.rvQauliyahSholat.adapter = mAdapter
        binding.rvQauliyahSholat.layoutManager = LinearLayoutManager(this)

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