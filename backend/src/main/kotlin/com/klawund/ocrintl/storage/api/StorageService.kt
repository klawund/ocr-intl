package com.klawund.ocrintl.storage.api

import com.klawund.ocrintl.common.api.FileId

interface StorageService {
	fun uploadFile(dto: UploadFileDto): FileUploadedDto
	fun downloadFile(id: FileId): DownloadFileDto
}