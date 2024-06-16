package com.klawund.ocrintl.storage.internal

import com.klawund.ocrintl.common.api.FileId
import com.klawund.ocrintl.storage.api.*
import java.nio.file.Files
import java.nio.file.Path

class LocalStorageService(
		private val storageLocation: Path,
		private val idGenerator: FileIdGenerator,
		private val uriGenerator: FileUriGenerator,
		private val repository: FileRepository
) : StorageService {
	override fun uploadFile(dto: UploadFileDto): FileUploadedDto {
		val metadata = FileMetadata(dto.name, dto.bytes.size, dto.contentType, dto.language)

		val id = idGenerator.generateId()
		val path = resolvePath(id)

		val file = File(id, metadata, path)
		repository.save(file)

		Files.write(path, dto.bytes)

		return FileUploadedDto(id, uriGenerator.generateUri(id))
	}

	override fun downloadFile(id: FileId): DownloadFileDto {
		val fileOptional = repository.findById(id)

		if (fileOptional.isEmpty) {
			throw FileNotFoundException()
		}

		val file = fileOptional.get()

		return DownloadFileDto(file.metadata, uriGenerator.generateUri(file.id))
	}

	private fun resolvePath(id: FileId): Path {
		return storageLocation.resolve(id.key)
	}
}