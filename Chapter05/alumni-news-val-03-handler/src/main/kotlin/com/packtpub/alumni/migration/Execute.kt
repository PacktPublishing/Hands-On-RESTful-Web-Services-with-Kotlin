package com.packtpub.alumni.migration

import com.packtpub.alumni.extension.executeMigration
import com.packtpub.alumni.module.dataSourceModule
import io.ktor.util.KtorExperimentalAPI
import org.koin.Logger.slf4jLogger
import org.koin.core.context.startKoin
import javax.sql.DataSource

@KtorExperimentalAPI
fun main() {
    val koin = startKoin {
        slf4jLogger()
        modules(dataSourceModule)
    }.koin

    val dataSource = koin.get<DataSource>()
    dataSource.executeMigration("classpath:/dbmigration/h2")
}
