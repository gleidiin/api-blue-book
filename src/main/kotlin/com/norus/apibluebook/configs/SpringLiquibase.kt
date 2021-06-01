package com.norus.apibluebook.configs

import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean

@Bean
fun liquibase(): SpringLiquibase? {
    return SpringLiquibase()
}

