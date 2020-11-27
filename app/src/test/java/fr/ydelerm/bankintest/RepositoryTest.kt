package fr.ydelerm.bankintest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.ydelerm.bankintest.repository.RepositoryImpl
import fr.ydelerm.bankintest.vo.Status
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(AndroidJUnit4::class)
class RepositoryTest {
    private var app: BankinApplication = getApplicationContext()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun beforeTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun whenErrorOccurred_statusIsError() {
        val repositoryImpl = RepositoryImpl(app)
        repositoryImpl.localDataSource = FakeLocalDataSource(null, null)
        repositoryImpl.networkDataSource = FakeNetworkDataSource(null)

        repositoryImpl.refreshData()

        assertEquals(Status.ERROR, repositoryImpl.getRequestStatus().getOrAwaitValue())
    }

    @Test
    fun whenCategoriesAreRetrieved_theyAreWrittenInRepo() {
        val repositoryImpl = RepositoryImpl(app, AndroidSchedulers.mainThread())
        repositoryImpl.localDataSource = FakeLocalDataSource(null, null)
        val expectedCategories = TestDataHelper().categories()
        repositoryImpl.networkDataSource = FakeNetworkDataSource(expectedCategories)

        runBlocking {
            repositoryImpl.refreshData()
        }

        val insertedCategories =
            (repositoryImpl.localDataSource as FakeLocalDataSource).getInsertedCategories()

        assertEquals(expectedCategories?.size, insertedCategories.size)
        assertEquals(expectedCategories!![0].id, insertedCategories[0].id)

        repositoryImpl.getRequestStatus().getOrAwaitValue()

        assertEquals(Status.SUCCESS, repositoryImpl.getRequestStatus().getOrAwaitValue())
    }

    @Test
    fun whenAskingForCategories_TheyAreTakenFromTheDataSource() {
        val repositoryImpl = RepositoryImpl(app, AndroidSchedulers.mainThread())
        val expectedCategories = TestDataHelper().mainCategories()
        val expectedSubCategories = TestDataHelper().subCategories()
        repositoryImpl.localDataSource = FakeLocalDataSource(
            expectedCategories,
            expectedSubCategories
        )
        repositoryImpl.networkDataSource = FakeNetworkDataSource(expectedCategories)

        runBlocking {
            repositoryImpl.refreshData()
        }

        val actualCategories = repositoryImpl.getCategories().getOrAwaitValue()
        assertEquals(expectedCategories?.size, actualCategories.size)
        assertEquals(expectedCategories!![0].id, actualCategories[0].id)

        val actualSubCategories = repositoryImpl.getSubCategories(42).getOrAwaitValue()
        assertEquals(expectedSubCategories?.size, actualSubCategories.size)
        assertEquals(expectedSubCategories!![0].id, actualSubCategories[0].id)
    }


}