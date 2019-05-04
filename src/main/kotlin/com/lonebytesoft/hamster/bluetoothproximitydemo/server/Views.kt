package com.lonebytesoft.hamster.bluetoothproximitydemo.server

data class PersonInformationRequest(
        var device: String = "",
        var identity: String = "",
        var nearby: Collection<String> = emptyList()
)

data class PersonInformationResponse(
        var device: String
)

data class NearbyResponse(
        var device: String,
        var nearby: Collection<NearbyItem>
)

data class NearbyItem(
        var device: String,
        var identity: String
)
