package nagoya.kuu.miolife.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nagoya.kuu.miolife.iijmio.LoginService


internal class MainViewModel(
    private val contractListLiveDataFactory: ContractListLiveDataFactory,
    private val loginService: LoginService
) : ViewModel() {

    private val loginedMutableLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginedLiveData: LiveData<Boolean> = loginedMutableLiveData

    private val contractListLiveData = contractListLiveDataFactory.create(viewModelScope)
    val contractList: LiveData<ContractListStatus> = contractListLiveData

    fun fetchCouponData() {
        contractListLiveData.refreshData()
    }

    fun login(fragment: Fragment) {
        loginService.launchLoginPage(fragment)
    }
}
