import java.io.File
import java.net.URI

object Resources {
    fun read(fileName: String) = File(fileName.toURI()).readText()

    private fun String.toURI(): URI =
        Resources.javaClass.classLoader.getResource(this)?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
}