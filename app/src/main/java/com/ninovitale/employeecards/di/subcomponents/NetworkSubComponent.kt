package com.ninovitale.employeecards.di.subcomponents

import com.ninovitale.employeecards.api.GistsApi
import dagger.Subcomponent

@Subcomponent
interface NetworkSubComponent {
    val api: GistsApi

    @Subcomponent.Factory
    interface Factory {
        fun create(): NetworkSubComponent
    }
}