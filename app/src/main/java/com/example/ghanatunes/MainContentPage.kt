package com.example.ghanatunes

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import java.net.URI
import java.util.logging.Logger

class MainContentPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content_page)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val button = findViewById<Button>(R.id.button).setOnClickListener{view ->

            val mediaPlayer:MediaPlayer? = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource("https://mmg.streamguys1.com/AdomFM-mp3?key=93fe20a16f78c70991fa726b9ca9c19c49f7329a3ad6144500e8fe7f3b8dadbafa6e84e66e84f9d149b17181fcf7194f")
                prepare()
                start()
            }
        }
    }
}

