package nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository

interface ContractReadonlyRepository {
    fun hasContractId(contractId: String): Boolean
}