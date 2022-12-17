package com.example.carshowroom

import java.io.Serializable

data class CarData(var carName: String ?= null, var carPrice: String ?= null, var carMileage: String ?= null,
                   var ownerCount: String ?= null, var carEngine: String ?= null, var carImage: ArrayList<String> ?= null,
                   var carDescription: String ?= null) : Serializable { }
