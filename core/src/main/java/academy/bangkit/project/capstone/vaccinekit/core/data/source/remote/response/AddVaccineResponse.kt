package academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AddVaccineResponse(

	@field:SerializedName("vaccineStatus")
	val vaccineStatus: String,

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("nik")
	val nik: String,

	@field:SerializedName("firstVaccineDate")
	val firstVaccineDate: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("secondVaccineDate")
	val secondVaccineDate: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("ttl")
	val ttl: String
)
