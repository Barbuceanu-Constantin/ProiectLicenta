package com.barbuceanuconstantin.proiectlicenta

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.barbuceanuconstantin.proiectlicenta.data.BudgetTrackerDatabase
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.dao.BudgetsDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.CategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.MainCategoryDAO
import com.barbuceanuconstantin.proiectlicenta.data.dao.TransactionsDAO
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@RunWith(AndroidJUnit4::class)
class RoomTests {
    private lateinit var budgetTrackerDatabase: BudgetTrackerDatabase
    private lateinit var categoryDao: CategoryDAO
    private lateinit var budgetsDao: BudgetsDAO
    private lateinit var transactionsDao: TransactionsDAO
    private lateinit var mainCategoryDao: MainCategoryDAO

    @Before
    fun setUp() {
        budgetTrackerDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BudgetTrackerDatabase::class.java
        ).build()
        categoryDao = budgetTrackerDatabase.categoryDao()
        budgetsDao = budgetTrackerDatabase.budgetsDao()
        transactionsDao = budgetTrackerDatabase.transactionsDao()
        mainCategoryDao = budgetTrackerDatabase.mainCategoryDao()
    }

    @Test
    fun checkCategoryGetInsertionUpdateDeletion() = runBlocking {
        categoryDao.insertCategory(Categories("Active", "Bacsis"))
        val categories = categoryDao.getAllCategories()
        assert(1 == categories.size)
        assert("Active" == categories[0].mainCategory)
        assert("Bacsis" == categories[0].name)
    }

    @Test
    fun checkMainCategoryGetInsertionUpdateDeletion() = runBlocking  {
        mainCategoryDao.insertMainCategory(MainCategories("Active"))
        mainCategoryDao.insertMainCategory(MainCategories("Pasive"))
        mainCategoryDao.insertMainCategory(MainCategories("Datorii"))
        val categories = mainCategoryDao.getAllMainCategories()
        assert(3 == categories.size)
        assert("Active" == categories[0].name)
        assert("Pasive" == categories[1].name)
        assert("Datorii" == categories[2].name)
    }

    private fun stringToDate(dateString: String): Date {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(dateString, formatter)
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }
    @Test
    fun checkBudgetsGetInsertionUpdateDeletion() = runBlocking {
        categoryDao.insertCategory(Categories("Pasive", "Benzina"))
        var categories = categoryDao.getAllCategories()
        assert(1 == categories.size)
        assert("Pasive" == categories[0].mainCategory)
        assert("Benzina" == categories[0].name)

        categoryDao.insertCategory(Categories("Active", "Bacsis"))
        categories = categoryDao.getAllCategories()
        assert(2 == categories.size)
        assert("Active" == categories[1].mainCategory)
        assert("Bacsis" == categories[1].name)

        val dateString1 = "2024-03-05"
        val date1 = stripTime(stringToDate(dateString1))
        val dateString2 = "2024-04-10"
        val date2 = stripTime(stringToDate(dateString2))
        budgetsDao.insertBudget(Budgets(1, "budget1", 545.0, date1, date2))
        val budgets = budgetsDao.getAllBudgets()
        assert(1 == budgets.size)
        assert(1 == budgets[0].categoryId)
        assert("budget1" == budgets[0].name)
        assert(545.0 == budgets[0].upperThreshold)
        assert(date1 == budgets[0].startDate)
        assert(date2 == budgets[0].endDate)
    }

    @Test
    fun checkTransactionGetInsertionUpdateDeletion() = runBlocking {
        categoryDao.insertCategory(Categories("Pasive", "Benzina"))
        var categories = categoryDao.getAllCategories()
        assert(1 == categories.size)
        assert("Pasive" == categories[0].mainCategory)
        assert("Benzina" == categories[0].name)

        categoryDao.insertCategory(Categories("Active", "Bacsis"))
        categories = categoryDao.getAllCategories()
        assert(2 == categories.size)
        assert("Active" == categories[1].mainCategory)
        assert("Bacsis" == categories[1].name)
    }

    @After
    fun tearDown() {
        budgetTrackerDatabase.close()
    }
}