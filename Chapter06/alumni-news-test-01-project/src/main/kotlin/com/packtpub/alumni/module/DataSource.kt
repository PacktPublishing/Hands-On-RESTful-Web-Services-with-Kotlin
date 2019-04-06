package com.packtpub.alumni.module

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ebean.Database
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig
import io.ktor.config.ApplicationConfig
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module
import javax.sql.DataSource

@KtorExperimentalAPI
val dataSourceModule = module {

    single<ApplicationConfig> {
        HoconApplicationConfig(ConfigFactory.load())
    }

    single<DataSource> {
        buildDataSource(get())
    }

    single {
        buildEbeanDatabase(get(), "EbeanDataSource", true)
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

private fun buildEbeanDatabase(dataSource: DataSource, name: String, default: Boolean): Database {
    val ebeanConfig = DatabaseConfig().apply {
        this.name = name
        setDataSource(dataSource)
        isDefaultServer = default
    }

    return DatabaseFactory.create(ebeanConfig)
}
