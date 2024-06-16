package com.klawund.ocrintl.storage.internal

import com.klawund.ocrintl.common.api.FileId
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

class ServletContextFileUriGenerator : FileUriGenerator {
	override fun generateUri(id: FileId): URI {
		val uriString = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/storage/files/")
				.path(id.toString())
				.path("/download")
				.toUriString()

		return URI(uriString)
	}
}