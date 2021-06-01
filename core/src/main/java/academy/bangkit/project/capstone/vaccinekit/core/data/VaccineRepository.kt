package academy.bangkit.project.capstone.vaccinekit.core.data

import academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.RemoteDataSource
import academy.bangkit.project.capstone.vaccinekit.core.domain.model.Vaccine
import academy.bangkit.project.capstone.vaccinekit.core.domain.model.Verification
import academy.bangkit.project.capstone.vaccinekit.core.domain.repository.IVaccineRepository
import academy.bangkit.project.capstone.vaccinekit.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VaccineRepository(private val remoteDataSource: academy.bangkit.project.capstone.vaccinekit.core.data.source.remote.RemoteDataSource) : IVaccineRepository {

    override fun getVaccineData(nik: String): Flow<Vaccine> {
        return remoteDataSource.getVaccineData(nik).map {
            DataMapper.mapResponseToDomain(it)
        }
    }

    override fun getVerification(nik: String): Flow<Verification> {
        return remoteDataSource.getVerifNik(nik).map {
            DataMapper.mapVerifResponseToDomain(it)
        }
    }

    override fun addVaccineData(
        nik: String,
        name: String,
        address: String,
        photos: String,
        ttl: String,
        firstVaccineData: String,
        secondVaccineDate: String,
        vaccineStatus: String
    ) = remoteDataSource.postVaccineData(
        nik,
        name,
        address,
        photos,
        ttl,
        firstVaccineData,
        secondVaccineDate,
        vaccineStatus
    )


}