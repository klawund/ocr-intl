package com.klawund.ocrintl.storage.api

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/api/storage")
class StorageController(
		private val storageService: StorageService,
		private val idGenerator: FileIdGenerator
) {
	@PostMapping("/files")
	fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
		TODO("implement MultipartFormData to receive file and Language as fields")
	}

	@GetMapping("/files/{fileId}/download")
	fun downloadFile(@PathVariable fileId: String, request: HttpServletRequest): ResponseEntity<Resource> {
		val id = idGenerator.parse(fileId)
		val downloadDto = storageService.downloadFile(id)

		val resource = UrlResource(downloadDto.resourceId)

		val metadata = downloadDto.metadata

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(metadata.contentType.toString()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.name + "\"")
				.body(resource)
	}
}