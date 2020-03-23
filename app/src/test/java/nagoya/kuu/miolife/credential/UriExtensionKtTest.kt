package nagoya.kuu.miolife.credential

import androidx.core.net.toUri
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class UriExtensionKtTest {
    @Test
    fun `空白文字列をからアクセストークンを取ろうとするとNULLが返ってくる`() {
        val url = ""

        val actual = url.toUri().toAccessToken()

        Assert.assertNull(actual)
    }

    @Test
    fun アクセストークンが存在しない場合はnullが返ってくる() {
        val url = "mio-life://top#"

        val actual = url.toUri().toAccessToken()

        Assert.assertNull(actual)
    }

    @Test
    fun アクセストークンが存在しない場合はnullが返ってくる2() {
        val url = "mio-life://top"

        val actual = url.toUri().toAccessToken()

        Assert.assertNull(actual)
    }

    @Test
    fun `正常にアクセストークンを抜き取ることができる`() {
        val url = "mio-life://top#access_token=hoge"
        val expected = "hoge"

        val actual = url.toUri().toAccessToken()

        Assert.assertEquals(expected, actual)
    }
}