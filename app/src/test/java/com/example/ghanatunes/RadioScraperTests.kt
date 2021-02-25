package com.example.ghanatunes

import org.junit.Test
import org.junit.Assert.*
import com.ghanatunes.core.*

class RadioScraperTests {
    val myFakeWebSite = "My fake website"

    @Test
    fun can_initializeRadioConnector(){
        val myRadioConnector = RadioConnector(myFakeWebSite)
        assertEquals(myRadioConnector, myFakeWebSite)
    }

}