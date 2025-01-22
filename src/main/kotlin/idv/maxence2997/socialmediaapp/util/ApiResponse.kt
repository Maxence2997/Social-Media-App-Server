package idv.maxence.idv.maxence2997.socialmediaapp.util

import io.ktor.http.*

sealed class ApiResponse<T>(
  val code: HttpStatusCode = HttpStatusCode.OK,
  val data: T,
) {
  class Success<T>(data: T) : ApiResponse<T>(data = data)
  class Error<T>(
    code: HttpStatusCode,
    data: T
  ) : ApiResponse<T>(
    code, data
  )
}