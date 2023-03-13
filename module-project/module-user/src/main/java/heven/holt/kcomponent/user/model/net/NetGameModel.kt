package heven.holt.kcomponent.user.model.net

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetGameModel(
    @SerialName("list")
    var list: List<Data>?,
    @SerialName("total")
    var total: Int?
) {
    @Serializable
    data class Data(
        @SerialName("discount")
        var discount: Double?,
        @SerialName("endTime")
        var endTime: Int?,
        @SerialName("grade")
        var grade: Int?,
        @SerialName("id")
        var id: Int?,
        @SerialName("img")
        var img: String?,
        @SerialName("initialPrice")
        var initialPrice: String?,
        @SerialName("label")
        var label: List<String?>?,
        @SerialName("name")
        var name: String?,
        @SerialName("price")
        var price: String?
    )
}

