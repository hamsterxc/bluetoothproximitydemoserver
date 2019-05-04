package com.lonebytesoft.hamster.bluetoothproximitydemo.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/api")
class ApiController
@Autowired
constructor(
        val personRepository: PersonRepository
) {

    private companion object {
        val DATA_EXPIRY_MILLIS = TimeUnit.MINUTES.toMillis(10)
    }

    @PostMapping("/person")
    fun addPersonInformation(
            @RequestBody personInformationRequest: PersonInformationRequest
    ): PersonInformationResponse {
        val person = personRepository.findByDeviceId(personInformationRequest.device)
                .orElseGet { Person() }

        person.timeUpdated = System.currentTimeMillis()
        person.deviceId = personInformationRequest.device
        person.personId = personInformationRequest.identity
        person.nearbyDeviceIds = personInformationRequest.nearby

        personRepository.save(person)

        return PersonInformationResponse(
                device = person.deviceId
        )
    }

    @GetMapping("/nearby/{deviceId}")
    fun getNearbyDevices(
            @PathVariable deviceId: String
    ): NearbyResponse {
        val selfNearby = personRepository.findByDeviceId(deviceId)
                .map { it.nearbyDeviceIds }
                .orElseGet { emptyList() }

        val timeStart = System.currentTimeMillis() - DATA_EXPIRY_MILLIS
        val visibleFrom = personRepository.findByTimeUpdatedGreaterThanEqual(timeStart)

        val nearbyItems = visibleFrom
                .filter { it.deviceId in selfNearby }
                .filter { deviceId in it.nearbyDeviceIds }
                .map { NearbyItem(
                        device = it.deviceId,
                        identity = it.personId
                ) }

        return NearbyResponse(
                device = deviceId,
                nearby = nearbyItems
        )
    }

}
