package com.norus.apibluebook.configs

import io.r2dbc.h2.H2ConnectionConfiguration

import io.r2dbc.h2.H2ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories


@Configuration
@EnableR2dbcRepositories
internal class R2DBCConfiguration : AbstractR2dbcConfiguration() {
    @Bean
    override fun connectionFactory(): H2ConnectionFactory {
        return H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("jdbc:h2:~/test")
                        .username("sa")
                        .build()
        )
    }
}