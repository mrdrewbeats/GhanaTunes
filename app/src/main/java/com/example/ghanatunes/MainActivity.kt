package com.example.ghanatunes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ghanatunes.core.RadioScraper
import com.ghanatunes.core.RadioStation
import com.ghanatunes.core.StationLoaded


class MainActivity : AppCompatActivity(), StationLoaded {

    lateinit var loadedRadios:MutableList<RadioStation>
    internal lateinit var radioScraper: RadioScraper
    lateinit var mainActivityCoordinatorLayout: ConstraintLayout
    lateinit var playStop: ImageView
    lateinit var previousButton: ImageView
    lateinit var nextButton: ImageView
    lateinit var radioName: TextView

    var radioStationNumber:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_control_page)

        initializeUIControls()

        mainActivityCoordinatorLayout = findViewById<ConstraintLayout>(R.id.media_control_page_root)
        radioScraper = RadioScraper(this)
        radioScraper.execute()

        //hideSystemUserInterface()
        //changeActivityAfterDelay()
    }

    private fun initializeUIControls() {
        radioName = findViewById(R.id.radio_name)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        playStop = findViewById(R.id.play_stop_button)


        nextButton.setOnClickListener(View.OnClickListener {
            goToNextPage()
        })

        previousButton.setOnClickListener {
            goToPreviousPage()
        }
    }

    private fun goToNextPage(){
        ++radioStationNumber

        if(radioStationNumber == loadedRadios.size){
            radioStationNumber = 0
        }

        displayRadioStation(this.loadedRadios.get(radioStationNumber))
    }

    private fun goToPreviousPage(){


        if(radioStationNumber == 0){
            radioStationNumber = loadedRadios.size -1
        }

        --radioStationNumber
        displayRadioStation(this.loadedRadios.get(radioStationNumber))
    }

    private fun changeActivityAfterDelay(){

        val r: Runnable = Runnable {
           val intent = Intent(this, MainContentPage::class.java)
            this.startActivity(intent)
        }

        val mHandler = Handler()
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

    override fun notifyRadioStationLoaded(stationsList: MutableList<RadioStation>) {
        this.loadedRadios = stationsList
        Snackbar.make(mainActivityCoordinatorLayout,"Finished loading radios",Snackbar.LENGTH_LONG).show()

        displayRadioStation(stationsList.get(radioStationNumber))
    }

    private fun displayRadioStation(currentRadioStation: RadioStation) {
        radioName.text = currentRadioStation.name
    }

}
