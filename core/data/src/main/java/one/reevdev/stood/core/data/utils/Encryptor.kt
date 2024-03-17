package one.reevdev.stood.core.data.utils

import one.reevdev.stood.core.data.utils.constants.AppConstants
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Encryptor {
    fun encrypt(input: String): ByteArray {
        val keyBytes = generateKeyFromPassword()
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = ByteArray(cipher.blockSize)
        val ivParams = IvParameterSpec(iv)
        val secretKeySpec = SecretKeySpec(keyBytes, "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParams)
        return cipher.doFinal(input.toByteArray(Charsets.UTF_8))
    }

    fun decrypt(encrypted: ByteArray): String {
        val keyBytes = generateKeyFromPassword()
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivSize = cipher.blockSize
        val iv = ByteArray(ivSize)
        val ivParams = IvParameterSpec(iv)
        val secretKeySpec = SecretKeySpec(keyBytes, "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParams)
        val decrypted = cipher.doFinal(encrypted)
        return String(decrypted, Charsets.UTF_8)
    }

    private fun generateKeyFromPassword(): ByteArray {
        val sha256 = MessageDigest.getInstance("SHA-256")
        // Encryption Key for dev stage and production stage is different
        return sha256.digest(AppConstants.getEncryptionKey().toByteArray(Charsets.UTF_8))
    }
}