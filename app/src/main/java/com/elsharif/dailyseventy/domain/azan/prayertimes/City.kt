package com.elsharif.dailyseventy.domain.azan.prayertimes

class City {
    var country: String? = null
    var name: String? = null
    var lat = 0.0
    var lng = 0.0

    constructor()
    constructor(country: String?, name: String?) {
        this.country = country
        this.name = name
    }

    constructor(country: String?, name: String?, lat: Double, lng: Double) {
        this.country = country
        this.name = name
        this.lat = lat
        this.lng = lng
    }

    override fun toString(): String {
        return "City{" +
                "countryCode='" + country + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}'
    }
}