package com.klawund.ocrintl.storage.internal

import com.klawund.ocrintl.common.api.FileId
import java.net.URI

interface FileUriGenerator {
	fun generateUri(id: FileId): URI
}