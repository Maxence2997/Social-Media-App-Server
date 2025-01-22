package idv.maxence.idv.maxence2997.socialmediaapp.business

import idv.maxence.idv.maxence2997.socialmediaapp.domain.AuthResponse
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignInParams
import idv.maxence.idv.maxence2997.socialmediaapp.domain.SignUpParams
import idv.maxence.idv.maxence2997.socialmediaapp.util.ApiResponse

interface AuthBusinessService {
  suspend fun signUp(params: SignUpParams): ApiResponse<AuthResponse>
  suspend fun signIn(params: SignInParams): ApiResponse<AuthResponse>
}