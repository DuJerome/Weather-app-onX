package com.dushanesmith.weather.data.location

import com.google.gson.annotations.SerializedName


data class LocationResults (

  @SerializedName("plus_code" ) var plusCode : PlusCode?          = PlusCode(),
  @SerializedName("results"   ) var results  : ArrayList<Results> = arrayListOf(),
  @SerializedName("status"    ) var status   : String?            = null

)