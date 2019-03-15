package com.packtpub.alumni.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "city")
data class City(
        @Id
        var id: String,
        var countryCode: String,
        var name: String,
        var areaCode: Int?
)

@Entity
@Table(name = "university")
data class University(
        @Id
        var id: String,
        @ManyToOne
        var city: City,
        var name: String,
        var address: String,
        var webPage: String?
)