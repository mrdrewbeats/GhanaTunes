package com.example.ghanatunes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.translationMatrix
import com.bumptech.glide.Glide
import com.example.ghanatunes.databinding.MediaControlPageBinding
import com.example.ghanatunes.databinding.MediaPlayerBottomSheetBinding
import com.ghanatunes.core.RadioScraper
import com.ghanatunes.core.RadioStation
import com.ghanatunes.core.StationLoaded
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.media_control_page.view.*
import kotlinx.android.synthetic.main.media_player_bottom_sheet.view.*
import kotlinx.coroutines.flow.callbackFlow
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(), StationLoaded {

    lateinit var loadedRadios:MutableList<RadioStation>
    private lateinit var radioScraper: RadioScraper
    lateinit var mainActivityCoordinatorLayout: ConstraintLayout
    lateinit var playStop: ImageView
    lateinit var previousButton: ImageView
    lateinit var nextButton: ImageView
    lateinit var buttomSheetRadioName: TextView
    lateinit var radioImage: ImageView
    lateinit var  linearLayout_bottomSheet: LinearLayout
    var bottomSheetViewBehavior: BottomSheetBehavior<View>? = null
    var radioStationNumber:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MediaControlPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //bottomSheetViewBehavior =BottomSheetBehavior.from(binding.mediaControlPageRoot.include.bottomSheet_linear_layout)
        initializeUIControls(binding)

    }

    private fun initializeUIControls(mediaControlPageBinding: MediaControlPageBinding) {
        //bottomRadioName.text = "Hello world"
        val bottomSheetBehavior = BottomSheetBehavior.from(mediaControlPageBinding.testingLayout.include)
        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                   BottomSheetBehavior.STATE_EXPANDED ->{
                       d("BottomSheet","Do Something here")
                       //make collapsed lineaer layout disappear
                       var collapsedMediaControls = mediaControlPageBinding.include.bottomSheetLinearLayout.collapsedMediaControls

                       collapsedMediaControls.visibility = View.GONE
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        var collapsedMediaControls = mediaControlPageBinding.include.bottomSheetLinearLayout.collapsedMediaControls
                        collapsedMediaControls.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        d("BottomSheet","STATE_DRAGGING")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        d("BottomSheet","STATE_HALF_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        d("BottomSheet","STATE_HIDDEN")
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        d("BottomSheet","STATE_SETTLING")
                    }
                }

            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
//        bottomSheetBehavior.setPeekHeight(true)
        //buttomSheetRadioName = mediaControlPageBinding.include.bottomSheetLinearLayout.bottom_radio_name
//        buttomSheetRadioName.setOnClickListener{
//            bottomSheetBehavior.apply {
//                isHideable = true
//                peekHeight = 500
//                setState(BottomSheetBehavior.STATE_HALF_EXPANDED)
//            }
//            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
//        }

    }

    private fun goToNextPage(){
        if(radioStationNumber == loadedRadios.size){
            radioStationNumber = 0
        }

        if (radioStationNumber < loadedRadios.size-1){
            ++radioStationNumber
            displayRadioStation(this.loadedRadios[radioStationNumber])
        }
    }

    private fun goToPreviousPage(){

        if(radioStationNumber <= 0){
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

        displayRadioStation(stationsList[radioStationNumber])
    }

    private fun displayRadioStation(currentRadioStation: RadioStation) {
        //radioName.text = currentRadioStation.name
        Glide.with(this)
            .load(currentRadioStation.imageLink)
            .into(radioImage)
    }
}
