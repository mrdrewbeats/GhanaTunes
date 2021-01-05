package com.example.ghanatunes

import com.ghanatunes.core.RadioScraper
import com.ghanatunes.core.RadioStation
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var testRadio:RadioStation
    lateinit var radioscraper: RadioScraper

    internal fun setup(){
        //Initialize class members

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


}
