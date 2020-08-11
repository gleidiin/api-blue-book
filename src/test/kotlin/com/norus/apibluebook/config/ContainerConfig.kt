package com.norus.apibluebook.config

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.MySQLContainerProvider
import org.testcontainers.junit.jupiter.Container

object ContainerConfig {

    @Container
    var mySQLContainer: JdbcDatabaseContainer<out JdbcDatabaseContainer<*>> = MySQLContainerProvider().newInstance()
            .withDatabaseName("blue_book")
            .withUsername("user")
            .withPassword("password")
            .withExposedPorts(3306)

    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

        override fun initialize(applicationContext: ConfigurableApplicationContext) {

            mySQLContainer.start()

            val jdbcUrl = mySQLContainer.getJdbcUrl()

            TestPropertyValues.of(
                    "spring.datasource.url=$jdbcUrl",
                    "app.datasource.marketplace.jdbc-url=$jdbcUrl",
                    "spring.datasource.username=user",
                    "spring.datasource.password=password",
                    "spring.jpa.generate-ddl=false",
                    "spring.jpa.database=mysql")
                    .applyTo(applicationContext)
        }
    }


}