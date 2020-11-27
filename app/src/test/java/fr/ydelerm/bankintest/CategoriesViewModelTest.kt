package fr.ydelerm.bankintest

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.ydelerm.bankintest.viewmodel.CategoriesViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(AndroidJUnit4::class)
class CategoriesViewModelTest {
    private var app: BankinApplication = getApplicationContext()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun whenInMainCategories_disableUpButton() {
        val categoriesViewModel = CategoriesViewModel(app)
        assertFalse(categoriesViewModel.isDisplayHomeAsUpEnabled())
    }

    @Test
    fun whenInSubCategories_enableUpButton() {
        val categoriesViewModel = CategoriesViewModel(app)
        categoriesViewModel.selectedCategoryId = 17
        assertTrue(categoriesViewModel.isDisplayHomeAsUpEnabled())
    }

    @Test
    fun whenInMainCategories_enableClick() {
        val categoriesViewModel = CategoriesViewModel(app)
        assertNotNull(categoriesViewModel.getCategoryClickListener(app))
    }

    @Test
    fun whenInSubCategories_disableClick() {
        val categoriesViewModel = CategoriesViewModel(app)
        categoriesViewModel.selectedCategoryId = 42
        assertNull(categoriesViewModel.getCategoryClickListener(app))
    }

    @Test
    fun whenErrorOccurred_displayErrorUi() {
        val categoriesViewModel = CategoriesViewModel(app)
        categoriesViewModel.repository = FakeRepositoryImpl(null, null)
        categoriesViewModel.refreshData()
        assertEquals(View.VISIBLE, categoriesViewModel.getErrorUiVisibility().getOrAwaitValue())
    }

    @Test
    fun whenInMainCategories_displayMainCategories() {
        val categoriesViewModel = CategoriesViewModel(app)
        categoriesViewModel.repository =
            FakeRepositoryImpl(
                TestDataHelper().mainCategories(),
                TestDataHelper().subCategories()
            )
        categoriesViewModel.refreshData()
        assertEquals(View.GONE, categoriesViewModel.getErrorUiVisibility().getOrAwaitValue())
        val actualCategories = categoriesViewModel.getCategories().getOrAwaitValue()
        assertEquals(2, actualCategories.size)
        assertEquals("Main 1", actualCategories[0].name)
    }

    @Test
    fun whenInSubCategories_displaySubCategories() {
        val categoriesViewModel = CategoriesViewModel(app)
        categoriesViewModel.repository =
            FakeRepositoryImpl(
                TestDataHelper().mainCategories(),
                TestDataHelper().subCategories()
            )
        categoriesViewModel.selectedCategoryId = 1
        categoriesViewModel.refreshData()
        assertEquals(View.GONE, categoriesViewModel.getErrorUiVisibility().getOrAwaitValue())
        val actualCategories = categoriesViewModel.getCategories().getOrAwaitValue()
        assertEquals(3, actualCategories.size)
        assertEquals("Sub 1 1", actualCategories[0].name)
    }

}