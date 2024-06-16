package com.klawund.ocrintl.storage.api

import com.klawund.ocrintl.storage.internal.StorageProperties
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.nio.file.Path
import java.nio.file.Paths

@Controller
@RequestMapping("/api/storage")
class StorageController
(storageProperties: StorageProperties) {
	private val fileStorageLocation: Path = Paths.get(storageProperties.uploadDir)
			.toAbsolutePath()
			.normalize()

	// todo move to service
	@PostMapping("/upload")
	fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
		val fileName = file.originalFilename?.let { StringUtils.cleanPath(it) }

		if (fileName == null) {
			return ResponseEntity.badRequest().build()
		}

		val targetLocation = fileStorageLocation.resolve(fileName)
		file.transferTo(targetLocation)

		val fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/storage/download/")
				.path(fileName)
				.toUriString()

		return ResponseEntity.ok(fileDownloadUrl)
	}

	// todo move to service
	@GetMapping("/download/{fileName:.+}")
	fun downloadFile(@PathVariable fileName: String, request: HttpServletRequest): ResponseEntity<Resource> {
		val filePath = fileStorageLocation.resolve(fileName).normalize()

		val resource = UrlResource(filePath.toUri())
		var contentType = request.servletContext.getMimeType(resource.file.absolutePath)

		if (contentType == null) {
			contentType = "application/octet-stream"
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename + "\"")
				.body(resource)
	}
}