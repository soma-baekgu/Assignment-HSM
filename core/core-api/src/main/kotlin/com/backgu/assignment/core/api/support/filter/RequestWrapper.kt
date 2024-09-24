package my.study.springlogging.global.filters

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.util.StreamUtils
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

class RequestWrapper(
    request: HttpServletRequest,
) : HttpServletRequestWrapper(request) {
    private val cachedInputStream: ByteArray

    init {
        val requestInputStream = request.inputStream
        cachedInputStream = StreamUtils.copyToByteArray(requestInputStream)
    }

    override fun getInputStream(): ServletInputStream =
        object : ServletInputStream() {
            private val cachedBodyInputStream: InputStream = ByteArrayInputStream(cachedInputStream)

            override fun isFinished(): Boolean =
                try {
                    cachedBodyInputStream.available() == 0
                } catch (e: IOException) {
                    e.printStackTrace()
                    false
                }

            override fun isReady(): Boolean = true

            override fun setReadListener(readListener: ReadListener): Unit = throw UnsupportedOperationException()

            override fun read(): Int = cachedBodyInputStream.read()
        }
}
