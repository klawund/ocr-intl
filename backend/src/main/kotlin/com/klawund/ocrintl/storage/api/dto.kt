package com.klawund.ocrintl.storage.api

import com.klawund.ocrintl.common.api.FileId
import com.klawund.ocrintl.common.api.Language
import java.net.URI

data class UploadFileDto(val name: String, val bytes: ByteArray, val contentType: ContentType, val language: Language)

data class FileUploadedDto(val id: FileId, val resourceId: URI)

data class DownloadFileDto(val metadata: FileMetadata, val resourceId: URI)