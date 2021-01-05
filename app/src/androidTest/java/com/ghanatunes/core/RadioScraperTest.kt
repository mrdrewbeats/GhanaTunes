package com.ghanatunes.core

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class RadioScraperTest : TestCase(), StationLoaded {

    lateinit var testRadio:RadioStation
    lateinit var radioScraper: RadioScraper
    var dummyRadios = mutableListOf<RadioStation>().apply {
        add(RadioStation("Test Radio 1","stream link 1"))
        add(RadioStation("Test radio 2","Stream 2"))
    }

    public override fun setUp() {
        super.setUp()
        testRadio = RadioStation("MyTestFm", "MyStreamLink")
        radioScraper = RadioScraper(this)
    }

    @Test
    fun navigateToAnotherPage(){
        var nextPagenumber = 3
        var newPageURl = radioScraper.goToPage(nextPagenumber)
        Assert.assertTrue(newPageURl.contains("page=3"))
    }

    override fun notifyRadioStationLoaded(stationsList: MutableList<RadioStation>) {
        this.dummyRadios = stationsList
    }

}
