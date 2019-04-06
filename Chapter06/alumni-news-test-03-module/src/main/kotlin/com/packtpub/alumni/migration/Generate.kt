package com.packtpub.alumni.migration

import com.packtpub.alumni.extension.generateMigrationFile
import com.packtpub.alumni.module.dataSourceModule
import io.ebean.annotation.Platform
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
    dataSource.generateMigrationFile(Platform.H2, "h2")
}
