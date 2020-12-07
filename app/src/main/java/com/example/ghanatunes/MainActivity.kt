package com.example.ghanatunes

import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<ConstraintLayout>(R.id.myCoordinatorLayout)
        val r: Runnable = object : Runnable {
            override fun run() {
                Snackbar.make(view, "Printing  text after delay", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        val mHandler = Handler();
        mHandler.postDelayed(r, 5000)

    }
}
