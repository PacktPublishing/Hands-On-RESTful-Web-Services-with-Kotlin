package com.packtpub.alumni.ebean

import com.packtpub.alumni.module.dataSourceModule
import io.ebean.Database
import io.ktor.util.KtorExperimentalAPI
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

@KtorExperimentalAPI
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersistenceTest {

    private val testDatabaseLocation: String = "/tmp/alumni_db_test3"
    private lateinit var koinApplication: KoinApplication
    private lateinit var context: Koin
    private lateinit var database: Database

    @BeforeEach
    fun `initialize koin application`() {
        System.setProperty("dataSource.location", testDatabaseLocation)
        koinApplication = koinApplication {
            modules(dataSourceModule)
        }
        context = koinApplication.koin
        database = context.get()
    }

    @Test
    fun `Database should able to do insert`() {

    }

    @AfterEach
    fun `shutdown application`() {
        database.shutdown(true, true)
        koinApplication.close()
    }


}