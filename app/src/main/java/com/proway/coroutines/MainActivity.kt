package com.proway.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proway.coroutines.view.MainFragment
import com.proway.coroutines.view.NamesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, NamesFragment.newInstance())
            .commitNow()
    }
}