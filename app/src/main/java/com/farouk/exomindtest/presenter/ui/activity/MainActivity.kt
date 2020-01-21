package com.farouk.exomindtest.presenter.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.farouk.exomindtest.ExomindApplication
import com.farouk.exomindtest.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ExomindApplication.appComponent.inject(this)
        ExomindApplication.instance=this

    }


}
