package com.packtpub.alumni.module

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.ApplicationConfig
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.koin.core.module.Module
import org.koin.dsl.module
import javax.sql.DataSource

@KtorExperimentalAPI
val dataSourceModule: Module = module {

    single<ApplicationConfig> {
        HoconApplicationConfig(ConfigFactory.load())
    }

    single<DataSource> {
        buildDataSource(get())
    }

}

@KtorExperimentalAPI
private fun buildDataSource(config: ApplicationConfig): HikariDataSource {
    val hikariConfig = HikariConfig()
    hikariConfig.poolName = config.propertyOrNull("dataSource.poolName")?.getString()
    hikariConfig.jdbcUrl = config.propertyOrNull("dataSource.url")?.getString()
    hikariConfig.username = config.propertyOrNull("dataSource.username")?.getString()
    hikariConfig.password = config.propertyOrNull("dataSource.password")?.getString()
    hikariConfig.driverClassName = config.propertyOrNull("dataSource.driverClassName")?.getString()

    hikariConfig.addDataSourceProperty("cachePrepStmts", "true")
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250")
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

    return HikariDataSource(hikariConfig)
}

