package com.packtpub.alumni.module

import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import javax.sql.DataSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@KtorExperimentalAPI
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataSourceModuleTest {

    @Nested
    inner class ConfigTest {
        private val testDatabaseLocation: String = "/tmp/alumni_db_test"
        private lateinit var koinApplication: KoinApplication
        private lateinit var context: Koin

        @BeforeEach
        fun `initialize koin application`() {
            System.setProperty("dataSource.location", testDatabaseLocation)
            koinApplication = koinApplication {
                modules(dataSourceModule)
            }
            context = koinApplication.koin
        }

        @Test
        fun `config must be override-able`() {
            with(context) {
                val applicationConfig: ApplicationConfig = get()
                val locationConfig = applicationConfig.propertyOrNull("dataSource.location")?.getString()

                assertNotNull(locationConfig)
                assertEquals(locationConfig, testDatabaseLocation)
            }
        }

        @Test
        fun `config must be mock-able`() {
            with(context) {
                declareMock<ApplicationConfig> {
                    given(this.propertyOrNull(anyString())).willReturn(null)
                }

                val applicationConfig: ApplicationConfig = get()
                val locationConfig = applicationConfig.propertyOrNull("dataSource.location")?.getString()

                assertNull(locationConfig)
            }
        }

        @AfterEach
        fun `close koin application`() {
            koinApplication.close()
        }
    }

    @Nested
    inner class DataSourceTest {
        private val testDatabaseLocation: String = "/tmp/alumni_db_test2"
        private lateinit var koinApplication: KoinApplication
        private lateinit var context: Koin
        private lateinit var dataSource: DataSource

        @BeforeEach
        fun `initialize koin application`() {
            System.setProperty("dataSource.location", testDatabaseLocation)
            koinApplication = koinApplication {
                modules(dataSourceModule)
            }
            context = koinApplication.koin
            dataSource = context.get()
        }

        @Test
        fun `connection should be open`() {
            val isConnectionClosed = dataSource.connection.isClosed
            assertFalse(isConnectionClosed)
        }

        @AfterEach
        fun `close koin application`() {
            dataSource.connection.close()
            koinApplication.close()
        }

    }

}