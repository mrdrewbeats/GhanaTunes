package com.ghanatunes.internals
import android.util.Log
import org.jsoup.*
import org.jsoup.select.Elements

class RadioScraper {
    val radioSource:StringBuilder = StringBuilder("https://streema.com/radios/country/Ghana")
    lateinit var radioStations:MutableList<RadioStation>

    init {
        radioStations = mutableListOf<RadioStation>()
    }

    fun goToPage(pageNumber: Int = 0): StringBuilder{
        return radioSource.append("?page=$pageNumber")
    }

    open fun getRadiosFromPage(pageNumber: Int = 1): Any
    {
        var pageURL = goToPage(pageNumber)
        var doc = Jsoup.connect(pageURL.toString()).get()
        var itemsList:Elements = doc.getElementsByClass("items-list")
        var radioItems = itemsList.get(0).getElementsByAttributeStarting("data-role")

        var childrenCount = itemsList.get(0).childrenSize()
        if (childrenCount == 0)
            throw  IllegalAccessError("Could not find elements in $pageNumber")

        var radistation : String

        //ConstructRadioStations from website
        radioItems.forEach{ n ->

            var radioStatiourl  = n.attr("data-url")
            var radioStationName = n.attr("title").substringAfter("Play ")
            val currentRadio = RadioStation(radioStationName,radioStatiourl)
            radioStations.add(currentRadio)
        }

        var size = radioStations.count()
        return radioItems
    }


}