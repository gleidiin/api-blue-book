package com.norus.apibluebook.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import java.sql.SQLException
import javax.sql.DataSource

@TestConfiguration
class HikariContainer {

    @Bean
    @Throws(SQLException::class)
    fun dataSource(): DataSource {
        var hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = ContainerConfig.mySQLContainer.getJdbcUrl()
        hikariConfig.username = "user"
        hikariConfig.password = "password"
        hikariConfig.isAllowPoolSuspension = true

        val ds = HikariDataSource(hikariConfig);
        return ds
    }
}