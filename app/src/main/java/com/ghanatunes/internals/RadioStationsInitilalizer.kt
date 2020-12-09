package com.ghanatunes.internals

sealed class RadioStationsInitilalizer {
    lateinit var radiosList : MutableList<RadioStation>

    init {
        radiosList.add(RadioStation("TestRadio1", "TestValue"))
    }

    open fun getRadioStation () : List<RadioStation> {
        return radiosList
    }

}