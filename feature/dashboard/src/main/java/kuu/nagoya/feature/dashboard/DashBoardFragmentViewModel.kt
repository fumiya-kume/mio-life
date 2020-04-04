package kuu.nagoya.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kuu.nagoya.feature.dashboard.entity.SimEntity

internal class DashBoardFragmentViewModel : ViewModel() {
    private val simListMutableLiveData = MutableLiveData<List<SimEntity>>()

    val simListLiveData: LiveData<List<SimEntity>> = simListMutableLiveData

    init {
        simListMutableLiveData.value = listOf(
            SimEntity(
                id = 0,
                simCode = "hdoxxxxxxxx",
                phoneNumber = "080-1234-5678",
                couponUse = false
            ),
            SimEntity(
                id = 1,
                simCode = "hdoxxxxxxxx",
                phoneNumber = "080-1234-5678",
                couponUse = false
            ),
            SimEntity(
                id = 3,
                simCode = "hdoxxxxxxxx",
                phoneNumber = "080-1234-5678",
                couponUse = false
            )
        )
    }
}