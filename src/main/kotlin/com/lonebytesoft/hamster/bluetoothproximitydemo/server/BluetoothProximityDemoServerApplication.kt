package com.lonebytesoft.hamster.bluetoothproximitydemo.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
open class BluetoothProximityDemoServerApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(BluetoothProximityDemoServerApplication::class.java, *args)
}
