package com.lonebytesoft.hamster.bluetoothproximitydemo.server

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface PersonRepository : CrudRepository<Person, Long> {

    fun findByDeviceId(deviceId: String): Optional<Person>

    fun findByTimeUpdatedGreaterThanEqual(time: Long): Collection<Person>

}
