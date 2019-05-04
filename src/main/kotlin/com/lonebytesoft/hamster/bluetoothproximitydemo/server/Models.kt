package com.lonebytesoft.hamster.bluetoothproximitydemo.server

import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Person(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(nullable = false)
        var timeUpdated: Long = 0,

        @Column(nullable = false)
        var deviceId: String = "",

        @Column(nullable = false)
        var personId: String = "",

        @ElementCollection
        var nearbyDeviceIds: Collection<String> = emptyList()

)
