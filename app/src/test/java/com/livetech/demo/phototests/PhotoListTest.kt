package com.livetech.demo.phototests

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.livetech.demo.utils.CoroutineTestRule
import com.livtech.common.core.models.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class PhotoListTest {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repo: FakePhotoRepo

    @Before()
    fun setup() {
        repo = FakePhotoRepo(TestCoroutineScope(), coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun fetch_photos_success() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            repo.getPhotos(HashMap()).observeForever { resource ->
                run {
                    when (resource) {
                        is Resource.Loading -> {
                            assert(resource.message!!.equals("Loading", true))
                        }
                        is Resource.Success -> {
                            val total = resource.data!!.total
                            Log.d("Total", "total $total")
                            assert(total.equals("668095", true))
                        }
                    }
                }
            }
        }
    }
}