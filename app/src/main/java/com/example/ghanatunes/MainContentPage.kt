package com.example.ghanatunes

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ghanatunes.internals.RadioStation
import com.ghanatunes.internals.RadioStationsInitilalizer
import java.net.URI
import java.util.logging.Logger

class MainContentPage : AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content_page)

        // possibly long operation
        radioStationContainer = RadioStationsInitilalizer()
        currentRadioStations= radioStationContainer.radiosList

        val button = findViewById<Button>(R.id.button).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //temporary
        if (v?.id?.equals(R.id.button) == true){
            Log.d("aaa","I clicked something from button")
            openOrCreateMediaPlayer()
        }
    }

    fun openOrCreateMediaPlayer(){
        if (this.countRadio <=1){this.countRadio++} else this.countRadio = 0

        val mediaPlayer:MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(currentRadioStations.get(countRadio).streamUrlLink)
            prepareAsync()
            start()
        }
    }

    var countRadio: Int = 0
    lateinit var radioStationContainer: RadioStationsInitilalizer
    lateinit var currentRadioStations : MutableList<RadioStation>
}

