package com.example.ghanatunes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_control_page)

        //hideSystemUserInterface()
        //changeActivityAfterDelay()
    }

    private fun changeActivityAfterDelay(){
        val view = findViewById<CoordinatorLayout>(R.id.activity_main_corrdinatorLayout)
        val r: Runnable = Runnable {
           val intent = Intent(this, MainContentPage::class.java)
            this.startActivity(intent)
        }

        val mHandler = Handler();
        mHandler.postDelayed(r, 500)
    }

    private fun hideSystemUserInterface(){
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE

                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}
