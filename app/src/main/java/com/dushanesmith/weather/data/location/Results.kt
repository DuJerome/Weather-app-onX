package com.dushanesmith.weather.data.location

import com.google.gson.annotations.SerializedName


data class Results (

  @SerializedName("address_components" ) var addressComponents : ArrayList<AddressComponents> = arrayListOf(),
  @SerializedName("formatted_address"  ) var formattedAddress  : String?                      = null,
  @SerializedName("geometry"           ) var geometry          : Geometry?                    = Geometry(),
  @SerializedName("place_id"           ) var placeId           : String?                      = null,
  @SerializedName("plus_code"          ) var plusCode          : PlusCode?                    = PlusCode(),
  @SerializedName("types"              ) var types             : ArrayList<String>            = arrayListOf()

)