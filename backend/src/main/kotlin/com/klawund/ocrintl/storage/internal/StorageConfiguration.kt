package com.klawund.ocrintl.storage.internal

import com.klawund.ocrintl.storage.api.FileIdGenerator
import com.klawund.ocrintl.storage.api.StorageService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Paths

@Configuration
@EnableConfigurationProperties(StorageProperties::class)
class StorageConfiguration {
	@Bean
	fun fileIdGenerator(): FileIdGenerator {
		return UuidFileIdGenerator()
	}

	@Bean
	fun fileUriGenerator(): FileUriGenerator {
		return ServletContextFileUriGenerator()
	}

	@Bean
	fun fileRepository(): FileRepository {
		TODO("implement postgresql repository")
	}

	@Bean
	fun storageService(
			storageProperties: StorageProperties,
			idGenerator: FileIdGenerator,
			uriGenerator: FileUriGenerator,
			repository: FileRepository
	): StorageService {
		val storageLocation = Paths.get(storageProperties.baseDir).toAbsolutePath().normalize()
		return LocalStorageService(storageLocation, idGenerator, uriGenerator, repository)
	}
}