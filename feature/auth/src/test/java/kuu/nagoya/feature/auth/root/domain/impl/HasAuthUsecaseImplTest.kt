package kuu.nagoya.feature.auth.root.domain.impl

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import kuu.nagoya.feature.auth.root.domain.AccessTokenReadonlyRepository
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecase
import kuu.nagoya.feature.auth.root.domain.HasAuthUsecaseResponse
import kuu.nagoya.resources.FlowObserver
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal class HasAuthUsecaseImplTest : Spek({

    val accessTokenReadonlyRepository: AccessTokenReadonlyRepository = mock()

    val hasAuthUsecase: HasAuthUsecase = HasAuthUsecaseImpl(accessTokenReadonlyRepository)

    beforeEachGroup {
        reset(
            accessTokenReadonlyRepository
        )
    }

    describe("アクセストークンが保存されている場合") {
        context("正常に保存されている場合") {
            runBlocking {
                val dummyAccessToken = "hogehoge"

                whenever(accessTokenReadonlyRepository.loadAccessToken()).thenReturn(
                    dummyAccessToken
                )

                val actualFlow = hasAuthUsecase.execute()

                val actual = FlowObserver.of(actualFlow)

                it("Loading の後に結果が返ってくる") {
                    Assert.assertArrayEquals(
                        arrayOf(
                            HasAuthUsecaseResponse.Loading,
                            HasAuthUsecaseResponse.HasAuth
                        ),
                        actual.values.toTypedArray()
                    )
                }
            }
        }

        context("異常系") {

            context("アクセストークンが空文字のときは認証されていない扱いする") {
                runBlocking {
                    val dummyAccessToken = ""
                    whenever(accessTokenReadonlyRepository.loadAccessToken()).thenReturn(
                        dummyAccessToken
                    )

                    val actualFlow = hasAuthUsecase.execute()

                    val actual = FlowObserver.of(actualFlow)

                    it("Loading の後に結果が返ってくる") {
                        Assert.assertArrayEquals(
                            arrayOf(
                                HasAuthUsecaseResponse.Loading,
                                HasAuthUsecaseResponse.NotHasAuth
                            ),
                            actual.values.toTypedArray()
                        )
                    }
                }
            }

            context("アクセストークンの読み取りで例外が発生した場合は認証されてない扱いにする") {
                runBlocking {

                    val errorMessage = "hoghoge"

                    whenever(accessTokenReadonlyRepository.loadAccessToken()).thenThrow(
                        NullPointerException(errorMessage)
                    )

                    val actualFlow = hasAuthUsecase.execute()

                    val actual = FlowObserver.of(actualFlow)

                    it("Loading の後に結果が返ってくる") {
                        Assert.assertArrayEquals(
                            arrayOf(
                                HasAuthUsecaseResponse.Loading,
                                HasAuthUsecaseResponse.Error(errorMessage)
                            ),
                            actual.values.toTypedArray()
                        )
                    }
                }
            }
        }
    }
})

