package com.harsh.fullscreenbottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.harsh.fullscreenbottomsheet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  lateinit var bi: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bi = DataBindingUtil.setContentView(this, R.layout.activity_main)

    init()
  }

  private fun init() {
    bi.openBottomSheet.setOnClickListener {
      val bottomSheet = BottomSheet()
      bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }
  }
}
