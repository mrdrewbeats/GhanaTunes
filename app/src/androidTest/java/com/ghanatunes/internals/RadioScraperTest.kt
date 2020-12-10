package com.ghanatunes.internals

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class RadioScraperTest : TestCase() {

    lateinit var testRadio:RadioStation
    lateinit var radioScraper: RadioScraper

    public override fun setUp() {
        super.setUp()
        testRadio = RadioStation("MyTestFm", "MyStreamLink")
        radioScraper = RadioScraper()
    }

    fun testGetRadioSource() {}

    @Test
    fun testGoToPage() {

        var itemslist = radioScraper.getRadiosFromPage()
        Assert.assertNotNull(itemslist)
    }

    @Test
    fun navigateToAnotherPage(){

        var nextPagenumber = 3
        var newPageURl = radioScraper.goToPage(nextPagenumber)
        Assert.assertTrue(newPageURl.contains("page=3"))
    }

    @Test
    fun radioIsGeneratedFromWebsite()
    {
        var radiosOfPage1 = radioScraper.getRadiosFromPage(1)
    }
}