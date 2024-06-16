package com.klawund.ocrintl.storage.internal

import com.klawund.ocrintl.common.api.FileId
import com.klawund.ocrintl.storage.api.FileIdGenerator
import java.util.UUID

class UuidFileIdGenerator: FileIdGenerator {
	override fun generateId(): FileId {
		return FileId(UUID.randomUUID().toString())
	}

	override fun parse(asString: String): FileId {
		val uuid = UUID.fromString(asString)
		return FileId(uuid.toString())
	}
}