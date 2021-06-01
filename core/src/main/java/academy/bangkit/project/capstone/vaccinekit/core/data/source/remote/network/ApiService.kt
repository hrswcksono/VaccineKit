package academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.network

import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.AddVaccineResponse
import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.VaccineResponse
import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.VerifResponse
import retrofit2.http.*

interface ApiService {

    @GET("verification_account_using_nik")
    suspend fun getVerifNik(
        @Query("nik") nik: String
    ): academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.VerifResponse

    @GET("get_vaccine_data")
    suspend fun getVaccine(
        @Query("nik") nik: String
    ): academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.VaccineResponse

    @FormUrlEncoded
    @POST("add_data_vaccine")
    suspend fun postVaccine(
        @Field("nik") nik: String,
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("photo") photo: String,
        @Field("ttl") ttl: String,
        @Field("firstVaccineData") firstVaccineData: String,
        @Field("secondVaccineDate") secondVaccineDate: String,
        @Field("vaccineStatus") vaccineStatus: String
    ): academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.AddVaccineResponse

}