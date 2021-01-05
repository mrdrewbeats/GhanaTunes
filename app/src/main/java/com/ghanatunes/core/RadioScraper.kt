package com.ghanatunes.core
import android.os.AsyncTask
import android.util.Log
import org.jsoup.*
import org.jsoup.select.Elements

internal class RadioScraper(stationLoaded: StationLoaded): AsyncTask<Void, Void, String>() {
    private val radioSource:StringBuilder = StringBuilder("https://streema.com/radios/country/Ghana")
    private var radioStations:MutableList<RadioStation> = mutableListOf<RadioStation>()
    private var onRadiosLoadedInterface: StationLoaded = stationLoaded

    fun goToPage(pageNumber: Int = 0): String{
        return "$radioSource?page=$pageNumber"
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

            constructRadioObjectFromHTMLItems(radioItems)

        }catch (e:Exception){
            Log.d("RadioScraper", "Could not parse Radio stations")
        }
    }

    protected fun getImageLinkForRadio(radioStationLink: String): String
    {
        try {
            val radiopage = Jsoup.connect(radioStationLink.replace("https","http")).get()
            val imageDiv = radiopage.getElementsByClass("song-image")
        }
        catch (e:Exception){
            Log.d("RadioScraper","Could not get radio information for $radioStationLink")
        }
        return ""

    }

    protected fun constructRadioObjectFromHTMLItems(radioItems: Elements) {

        radioItems.forEach { n ->
            val radioStationurl = "https://streema.com/${n.attr("data-url")}"
            val radioStationImage = getImageLinkForRadio(radioStationurl)
            //Strip play from div title
            val radioStationName = n.attr("title").substringAfter("Play ")
            val currentRadio = RadioStation(radioStationName, radioStationurl,radioStationImage)
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

        this.onRadiosLoadedInterface.notifyRadioStationLoaded(this.radioStations)
        this.radioStations.forEach{n -> Log.d("RadioScraper", "Radio name${n.name} => radio url ${n.streamUrlLink}")}
    }


}