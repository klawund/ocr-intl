package com.klawund.ocrintl.storage.internal

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(StorageProperties::class)
class StorageConfiguration