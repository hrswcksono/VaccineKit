package academy.bangkit.project.capstone.vaccinekit.core.utils

import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.LoginUserResponse
import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.NIKBarcodeResponse
import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.VaccineResponse
import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.response.VerifResponse
import academy.bangkit.project.capstone.vaccinekit.core.domain.model.LoginUser
import academy.bangkit.project.capstone.vaccinekit.core.domain.model.NIKBarcode
import academy.bangkit.project.capstone.vaccinekit.core.domain.model.Vaccine
import academy.bangkit.project.capstone.vaccinekit.core.domain.model.Verification

object DataMapper {
    fun mapResponseToDomain(it: VaccineResponse) = Vaccine(
        nik = it.nik,
        name = it.name,
        photo = it.photo,
        vaccineStatus = it.vaccineStatus,
        ttl = it.ttl,
        address = it.address,
        barcode = it.barcode,
        firstVaccineDate = it.firstVaccineDate,
        secondVaccineDate = it.secondVaccineDate,
        date = it.date
    )

    fun mapVerifResponseToDomain(it: VerifResponse) = Verification(
        message = it.message,
        status = it.status
    )

    fun mapNIKBarcode(it: NIKBarcodeResponse) = NIKBarcode(
        barcode = it.barcode,
        verification = it.verification
    )

    fun mapLoginUser(it: LoginUserResponse) = LoginUser(
        statusVerification = it.statusVerification
    )
}