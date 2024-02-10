package one.reevdev.stood.core.data.utils

fun String.encrypt(): ByteArray {
    return Encryptor.encrypt(this)
}

fun ByteArray.decrypt(): String {
    return Encryptor.decrypt(this)
}

fun emptyByteArray() = ByteArray(0)