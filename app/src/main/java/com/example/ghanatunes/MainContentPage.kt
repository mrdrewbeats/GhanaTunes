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

        val button = findViewById<Button>(R.id.button).setOnClickListener{view ->

            val mediaPlayer:MediaPlayer? = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource("https://stream.zenolive.com/sfqmtnszzk5tv")
                prepare()
                start()
            }
        }
    }
}

