package com.backgu.assignment.core.api.support.error

class CoreApiException(
    val errorType: ErrorType,
    val data: Any? = null,
) : RuntimeException(errorType.message)
