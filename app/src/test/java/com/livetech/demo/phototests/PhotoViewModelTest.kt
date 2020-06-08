package com.livetech.demo.phototests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.livetech.demo.ui.PhotoViewModel
import com.livetech.demo.utils.CoroutineTestRule
import com.livetech.demo.utils.observeForTesting
import com.livetech.demo.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class PhotoViewModelTest {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repo: FakePhotoRepo
    lateinit var photoViewModel: PhotoViewModel

    @Before()
    fun setup() {
        repo = FakePhotoRepo(coroutinesTestRule.testDispatcherProvider)
        photoViewModel = PhotoViewModel(repo)
    }

    @After()
    fun cleanup() {
        coroutinesTestRule.testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `photo list searching test`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            pauseDispatcher {
                //given
                val totalPage = 66810
                //when
                photoViewModel.doSearching("google")
                //then
                photoViewModel.loading.observeOnce {
                    assertEquals(it, true)
                }
                resumeDispatcher()
                photoViewModel.photoData.observeForTesting {
                    assertEquals(photoViewModel.totalPages, totalPage)
                }
            }

        }

    }

    @Test
    fun `photo list load next page test`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            //given
            val nextPage = 2;
            val totalPage = 66810
            //when
            photoViewModel.doSearching("google")
            photoViewModel.loadNextPage()
            //then
            photoViewModel.photoData.observeForTesting {
                assertEquals(photoViewModel.page, nextPage)
                assertEquals(photoViewModel.totalPages, totalPage)
            }
        }
    }

}