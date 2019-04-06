package com.packtpub.alumni.extension

import io.ebean.annotation.Platform
import io.ebean.config.DbMigrationConfig
import io.ebean.config.ServerConfig
import io.ebean.dbmigration.DbMigration
import org.flywaydb.core.Flyway
import javax.sql.DataSource

fun DataSource.generateMigrationFile(platform: Platform, prefix: String): String? {
    val dataSource = this
    val serverConfig = ServerConfig().apply {
        this.migrationConfig = DbMigrationConfig().apply {
            applyPrefix = "V"
        }
        this.dataSource = dataSource
        this.isDefaultServer = false
        this.name = "MigrationDataSource"
    }

    val dbMigration = DbMigration.create().apply {
        setServerConfig(serverConfig)
        addPlatform(platform, prefix)
    }
    return dbMigration.generateMigration()
}

fun DataSource.executeMigration(location: String) {
    val dataSource = this
    val flyway = Flyway
            .configure()
            .dataSource(dataSource)
            .locations(location)
            .load()
    flyway.migrate()
}

