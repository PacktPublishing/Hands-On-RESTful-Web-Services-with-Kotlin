package com.packtpub.alumni.module

import com.packtpub.alumni.handler.CityHandler
import com.packtpub.alumni.handler.UniversityHandler
import org.koin.dsl.module

val handlerModule = module {

    single {
        CityHandler(get())
    }

    single {
        UniversityHandler(get())
    }

}