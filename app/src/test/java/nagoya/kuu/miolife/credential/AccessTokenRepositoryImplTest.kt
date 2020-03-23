package nagoya.kuu.miolife.credential

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class AccessTokenRepositoryImplTest {

    @Test
    fun `読み書きするテスト`() {
        listOf(
            "",
            "hoge"
        ).forEach {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            Assert.assertNotNull(context)

            val accessTokenRepository = AccessTokenRepositoryImpl(context)

            val targetAccessToken = it

            accessTokenRepository.storeAccessToken(targetAccessToken)

            val actual = accessTokenRepository.loadAccessToken()

            Assert.assertEquals(targetAccessToken, actual)
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("nagoya.kuu.miolife", appContext.packageName)
    }
}