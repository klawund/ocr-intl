package com.klawund.ocrintl.storage.api

import com.klawund.ocrintl.common.api.FileId

interface FileIdGenerator {
	fun generateId(): FileId
	fun parse(asString: String): FileId
}