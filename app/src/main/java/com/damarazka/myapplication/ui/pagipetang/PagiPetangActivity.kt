package com.damarazka.myapplication.ui.pagipetang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.damarazka.doadandzikirapp.R
import com.damarazka.doadandzikirapp.databinding.ActivityPagiBinding
import com.damarazka.doadandzikirapp.databinding.ActivityPagiPetangBinding

class PagiPetangActivity : AppCompatActivity(), View.OnClickListener{
    private var _binding: ActivityPagiPetangBinding? = null
    private val binding get() = _binding as ActivityPagiPetangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_pagi_petang)

        val btnPagi = findViewById<ImageButton>(R.id.img_btn_dzikirpagi)
        val btnPetang = findViewById<ImageButton>(R.id.img_btn_dzikirpetang)

        btnPagi.setOnClickListener(this)
        btnPetang.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        when (view?.id){
            R.id.img_btn_dzikirpagi -> startActivity(Intent(this,PagiActivity::class.java))
            R.id.img_btn_dzikirpetang -> startActivity(Intent(this,PetangActivity::class.java))
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