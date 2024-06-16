package com.klawund.ocrintl.storage.internal

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("ocr-intl.storage")
data class StorageProperties @ConstructorBinding constructor(val uploadDir: String)