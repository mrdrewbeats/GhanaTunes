package com.ghanatunes.internals
import android.os.AsyncTask
import android.os.Handler
import android.os.NetworkOnMainThreadException
import android.util.Log
import org.jsoup.*
import org.jsoup.select.Elements

class RadioScraper(stationLoaded: StationLoaded): AsyncTask<Void, Void, String>() {
    val radioSource:StringBuilder = StringBuilder("https://streema.com/radios/country/Ghana")
    var radioStations:MutableList<RadioStation>
    lateinit var onRadiosLoadedInterface: StationLoaded

    init {
        radioStations = mutableListOf<RadioStation>()
        onRadiosLoadedInterface = stationLoaded
    }

    fun goToPage(pageNumber: Int = 0): String{
        return "${radioSource.toString()}?page=$pageNumber"
    }

    protected fun getRadiosFromPage(pageNumber: Int = 1): Unit
    {
        try {
            val pageURL = goToPage(pageNumber)
            val doc = Jsoup.connect(pageURL).get()
            val itemsList:Elements = doc.getElementsByClass("items-list")
            val radioItems = itemsList[0].getElementsByAttributeStarting("data-role")

            val childrenCount = itemsList[0].childrenSize()
            if (childrenCount == 0)
                throw  IllegalAccessError("Could not find elements in $pageNumber")

            ConstructRadioObjectFromHTMLItems(radioItems)

        }catch (e:Exception){
            Log.d("RadioScraper", "Could not parse Radio stations")
        }
    }

    private fun ConstructRadioObjectFromHTMLItems(radioItems: Elements) {

        radioItems.forEach { n ->
            var radioStationurl = n.attr("data-url")

            //Strip play from div title
            var radioStationName = n.attr("title").substringAfter("Play ")
            val currentRadio = RadioStation(radioStationName, radioStationurl)

            // avoid add station if it exists
            if (radioStations.contains(currentRadio))
                return

            radioStations.add(currentRadio)
        }
    }

    override fun doInBackground(vararg params: Void?): String {
        for (i in 1..10){
            getRadiosFromPage(i)
        }
        Log.d("RadioScraper", "Loading Radios")
        return ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d("RadioScraper", "Finished Loading Radios")
        this.onRadiosLoadedInterface.setRadiosAfterLoadingSuccessful(this.radioStations)
    }


}