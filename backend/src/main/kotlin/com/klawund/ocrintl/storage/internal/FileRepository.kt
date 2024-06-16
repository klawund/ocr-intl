package com.klawund.ocrintl.storage.internal

import com.klawund.ocrintl.common.api.FileId
import com.klawund.ocrintl.storage.api.File
import java.util.*

interface FileRepository {
	fun save(file: File)
	fun findById(id: FileId): Optional<File>
}