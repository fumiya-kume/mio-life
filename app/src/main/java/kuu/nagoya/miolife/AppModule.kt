package kuu.nagoya.miolife

import kuu.nagoya.feature.dashboard.DashboardModuleNavigation
import kuu.nagoya.miolife.navigation.AppModuleNavigation
import kuu.nagoya.miolife.navigation.AppModuleNavigationImpl
import kuu.nagoya.miolife.navigation.DashboardModuleNavigationImpl
import org.koin.dsl.module

val appModule = module {
    factory { AppModuleNavigationImpl() as AppModuleNavigation }
    factory { DashboardModuleNavigationImpl() as DashboardModuleNavigation }
}