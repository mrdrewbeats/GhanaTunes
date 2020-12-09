package com.example.ghanatunes

import com.ghanatunes.internals.RadioStation
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var testRadio:RadioStation
    @BeforeAll fun setup(){
        testRadio = RadioStation("MyTestFm", "MyStreamLink")
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun canCreateRadioStation(){

    }
}
