package com.klawund.ocrintl.storage.api

import com.klawund.ocrintl.common.api.FileId
import com.klawund.ocrintl.common.api.Language
import java.nio.file.Path

data class File(
		val id: FileId,
		val metadata: FileMetadata,
		val physicalFile: Path
)

data class FileMetadata(
		val name: String,
		val contentLengthInBytes: Int,
		val contentType: ContentType,
		val language: Language
)

enum class ContentType {

}