package com.example.ghanatunes

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ghanatunes.core.RadioScraper
import com.ghanatunes.core.RadioStation
import com.ghanatunes.core.StationLoaded

class MainContentPage : AppCompatActivity(), View.OnClickListener, StationLoaded {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_control_page)

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

    override fun notifyRadioStationLoaded(stationsList: MutableList<RadioStation>) {
        this.currentRadioStations = stationsList
        Log.d("RadioScraper", "Setting radio station${currentRadioStations.size}")
    }

    var countRadio: Int = 0
    var currentRadioStations : MutableList<RadioStation> = mutableListOf<RadioStation>()
    internal lateinit var radioScraper: RadioScraper
}

