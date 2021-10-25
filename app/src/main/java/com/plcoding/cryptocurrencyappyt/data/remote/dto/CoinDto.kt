package com.plcoding.cryptocurrencyappyt.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.plcoding.cryptocurrencyappyt.domain.model.Coin

data class CoinDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)
// CoinDto -> Coin
fun CoinDto.toCoin(): Coin{
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}