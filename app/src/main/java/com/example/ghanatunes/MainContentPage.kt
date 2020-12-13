package com.example.ghanatunes

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ghanatunes.internals.RadioStation
import com.ghanatunes.internals.RadioStationsInitilalizer
import com.ghanatunes.internals.StationLoaded
import java.net.URI
import java.util.logging.Logger

class MainContentPage : AppCompatActivity(), View.OnClickListener, StationLoaded {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content_page)

        val r = RadioScraper(this)
        r.execute()

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

    override fun setRadiosAfterLoadingSuccessful(stationsList: MutableList<RadioStation>) {
        this.currentRadioStations = stationsList
        Log.d("RadioScraper", "Setting radio station${currentRadioStations.size}")
    }

    var countRadio: Int = 0
    lateinit var radioStationContainer: RadioStationsInitilalizer
    var currentRadioStations : MutableList<RadioStation> = mutableListOf<RadioStation>()
    lateinit var radioScraper: RadioScraper
}

