package com.ghanatunes.internals

open class RadioStationsInitilalizer {
    // class should verifya
    var radiosList : MutableList<RadioStation> = mutableListOf<RadioStation>()

    init {

        populateRadioStations()
    }

    fun populateRadioStations() {
        // verify if radio stations can be loaded, on failure, fall back to a default local radio station
        radiosList.add(RadioStation("Ghana Radio", "http://streaming.radio.co:80/s92f890821/listen"))
        radiosList.add(RadioStation("Ghana Waves", "http://stream.zenolive.com/yp9sds3kkq5tv"))
    }

}