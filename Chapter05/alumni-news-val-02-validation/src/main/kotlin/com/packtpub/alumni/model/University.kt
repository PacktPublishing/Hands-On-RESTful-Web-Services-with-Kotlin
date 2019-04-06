package com.packtpub.alumni.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "city")
data class City(
        @Id
        @field:NotBlank
        var id: String,
        @field:NotBlank
        var countryCode: String,
        @field:NotBlank
        var name: String,
        @field:Min(1000)
        @field:Max(9999)
        var areaCode: Int?
)

@Entity
@Table(name = "university")
data class University(
        @Id
        @field:NotBlank
        var id: String,
        @ManyToOne
        var city: City,
        @field:NotBlank
        var name: String,
        @field:NotBlank
        var address: String,
        var webPage: String?
)