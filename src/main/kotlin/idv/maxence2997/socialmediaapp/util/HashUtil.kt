package idv.maxence.idv.maxence2997.socialmediaapp.util

import idv.maxence.idv.maxence2997.socialmediaapp.config.HashConfig
import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

fun hashPassword(password: String): String {
  val hMacKey = SecretKeySpec(HashConfig.hashKey.toByteArray(), HashConfig.algorithm)
  val hMac = Mac.getInstance(HashConfig.algorithm)
  hMac.init(hMacKey)
  return hex(hMac.doFinal(password.toByteArray()))
}