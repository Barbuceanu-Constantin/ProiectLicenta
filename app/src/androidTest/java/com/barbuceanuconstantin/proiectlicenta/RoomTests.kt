package com.barbuceanuconstantin.proiectlicenta

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.barbuceanuconstantin.proiectlicenta.data.BudgetTrackerDatabase
import com.barbuceanuconstantin.proiectlicenta.data.Budgets
import com.barbuceanuconstantin.proiectlicenta.data.Categories
import com.barbuceanuconstantin.proiectlicenta.data.MainCategories
import com.barbuceanuconstantin.proiectlicenta.data.Transactions
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
    fun checkCategoryGetInsertUpdateDelete() = runBlocking {
        //insert, getAll check
        categoryDao.insertCategory(Categories("Active", "Bacsis"))
        var categories = categoryDao.getAllCategories()
        assert(1 == categories.size)
        assert("Active" == categories[0].mainCategory)
        assert("Bacsis" == categories[0].name)
        assert(1 == categories[0].id)

        //update check
        categoryDao.updateCategory(Categories(1,"Pasive", "Benzina"))
        categories = categoryDao.getAllCategories()
        assert(1 == categories.size)
        assert("Pasive" == categories[0].mainCategory)
        assert("Benzina" == categories[0].name)
        assert(1 == categories[0].id)

        //delete check
        categoryDao.deleteCategoryByNameAndPrincipal("Benzina","Pasive")
        categories = categoryDao.getAllCategories()
        assert(0 == categories.size)

        categoryDao.insertCategory(Categories("Active", "Bacsis"))
        categoryDao.insertCategory(Categories("Pasive", "Benzina"))
        categories = categoryDao.getAllCategories()
        assert(2 == categories.size)
        categoryDao.deleteAllEntriesFromCategories()
        assert(2 == categories.size)
    }

    @Test
    fun checkMainCategoryGetInsertUpdateDelete() = runBlocking  {
        //insert, getAll check
        mainCategoryDao.insertMainCategory(MainCategories("Active"))
        mainCategoryDao.insertMainCategory(MainCategories("Pasive"))
        mainCategoryDao.insertMainCategory(MainCategories("Datorii"))
        var categories = mainCategoryDao.getAllMainCategories()
        assert(3 == categories.size)
        assert("Active" == categories[0].name)
        assert("Pasive" == categories[1].name)
        assert("Datorii" == categories[2].name)

        //Nu am implementat update pe MainCategories pentru ca nu e nevoie.
        //delete check
        mainCategoryDao.deleteAllEntriesFromMainCategories()
        categories = mainCategoryDao.getAllMainCategories()
        assert(0 == categories.size)
    }

    private fun stringToDate(dateString: String): Date {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(dateString, formatter)
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }
    @Test
    fun checkBudgetsGetInsertUpdateDelete() = runBlocking {
        //insert, getAll check
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
        val dateString3 = "2024-02-05"
        val date3 = stripTime(stringToDate(dateString3))
        val dateString4 = "2024-02-20"
        val date4 = stripTime(stringToDate(dateString4))
        budgetsDao.insertBudget(Budgets(1, "budget1", 545.0, date1, date2))
        var budgets = budgetsDao.getAllBudgets()
        assert(1 == budgets.size)
        assert(1 == budgets[0].categoryId)
        assert("budget1" == budgets[0].name)
        assert(545.0 == budgets[0].upperThreshold)
        assert(date1 == budgets[0].startDate)
        assert(date2 == budgets[0].endDate)

        //update check
        budgetsDao.updateBudget(Budgets(1,2,"budget2",575.0,date3,date4))
        budgets = budgetsDao.getAllBudgets()
        assert(1 == budgets.size)
        assert(2 == budgets[0].categoryId)
        assert("budget2" == budgets[0].name)
        assert(575.0 == budgets[0].upperThreshold)
        assert(date3 == budgets[0].startDate)
        assert(date4 == budgets[0].endDate)

        //delete check
        budgetsDao.deleteBudgetByName("budget2")
        budgets = budgetsDao.getAllBudgets()
        assert(0 == budgets.size)

        budgetsDao.insertBudget(Budgets(1, "budget1", 545.0, date1, date2))
        budgetsDao.deleteBudgetById(1)
        budgets = budgetsDao.getAllBudgets()
        assert(0 == budgets.size)

        budgetsDao.insertBudget(Budgets(1,"budget1",545.0, date1, date2))
        budgetsDao.insertBudget(Budgets(2,"budget2",575.0, date3, date4))
        budgetsDao.deleteAllEntriesFromBudgets()
        budgets = budgetsDao.getAllBudgets()
        assert(0 == budgets.size)
    }

    @Test
    fun checkTransactionsGetInsertUpdateDelete() = runBlocking {
        //insert, getAll check
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

        val dateString1 = "2024-04-05"
        val date1 = stripTime(stringToDate(dateString1))
        val dateString2 = "2024-05-10"
        val date2 = stripTime(stringToDate(dateString2))
        transactionsDao.insertTransaction(Transactions("payee1", "desc1", 54.0, 1, date1))
        transactionsDao.insertTransaction(Transactions("payee2", "desc2", 33.0, 2, date2))
        var transactions = transactionsDao.getAllTransactions()
        assert(2 == transactions.size)
        assert("payee1" == transactions[0].payee)
        assert("payee2" == transactions[1].payee)
        assert("desc1" == transactions[0].description)
        assert("desc2" == transactions[1].description)
        assert(54.0 == transactions[0].value)
        assert(33.0 == transactions[1].value)
        assert(1 == transactions[0].categoryId)
        assert(2 == transactions[1].categoryId)
        assert(date1 == transactions[0].date)
        assert(date2 == transactions[1].date)

        //update
        transactionsDao.updateTransaction(Transactions(1, "payee2", "desc2", 33.0, 2, date2))
        transactionsDao.updateTransaction(Transactions(2, "payee1", "desc1", 54.0, 1, date1))
        transactions = transactionsDao.getAllTransactions()
        assert(2 == transactions.size)
        assert("payee1" == transactions[1].payee)
        assert("payee2" == transactions[0].payee)
        assert("desc1" == transactions[1].description)
        assert("desc2" == transactions[0].description)
        assert(54.0 == transactions[1].value)
        assert(33.0 == transactions[0].value)
        assert(1 == transactions[1].categoryId)
        assert(2 == transactions[0].categoryId)
        assert(date1 == transactions[1].date)
        assert(date2 == transactions[0].date)

        //delete
        transactionsDao.deleteTransactionById(1)
        transactions = transactionsDao.getAllTransactions()
        assert(1 == transactions.size)
        assert(transactions[0].id == 2)
        transactionsDao.deleteTransactionById(2)
        transactions = transactionsDao.getAllTransactions()
        assert(0 == transactions.size)

        transactionsDao.insertTransaction(Transactions("payee1", "desc1", 54.0, 1, date1))
        transactionsDao.insertTransaction(Transactions("payee2", "desc2", 33.0, 2, date2))
        transactions = transactionsDao.getAllTransactions()
        assert(2 == transactions.size)
        transactionsDao.deleteAllEntriesFromTransactions()
        transactions = transactionsDao.getAllTransactions()
        assert(0 == transactions.size)
    }

    @Test
    fun checkGetTransactionsByCategory() = runBlocking {
        categoryDao.insertCategory(Categories("Pasive", "cp1"))
        categoryDao.insertCategory(Categories("Pasive", "cp2"))
        categoryDao.insertCategory(Categories("Pasive", "cp3"))
        categoryDao.insertCategory(Categories("Active", "ca1"))
        categoryDao.insertCategory(Categories("Active", "ca2"))
        categoryDao.insertCategory(Categories("Datorii", "cd1"))

        val dateString1 = "2024-04-05"
        val date1 = stripTime(stringToDate(dateString1))
        transactionsDao.insertTransaction(Transactions("payee1", "desc1", 10.0, 1, date1))
        transactionsDao.insertTransaction(Transactions("payee2", "desc2", 15.0, 1, date1))
        transactionsDao.insertTransaction(Transactions("payee3", "desc3", 10.0, 2, date1))
        transactionsDao.insertTransaction(Transactions("payee4", "desc4", 15.0, 2, date1))
        transactionsDao.insertTransaction(Transactions("payee5", "desc5", 10.0, 2, date1))
        transactionsDao.insertTransaction(Transactions("payee6", "desc6", 15.0, 3, date1))
        transactionsDao.insertTransaction(Transactions("payee7", "desc7", 20.0, 3, date1))
        transactionsDao.insertTransaction(Transactions("payee8", "desc8", 10.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee9", "desc9", 15.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee10", "desc10", 10.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee11", "desc11", 15.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee12", "desc12", 10.0, 5, date1))
        transactionsDao.insertTransaction(Transactions("payee13", "desc13", 15.0, 5, date1))
        transactionsDao.insertTransaction(Transactions("payee14", "desc14", 20.0, 6, date1))

        var transactions = transactionsDao.getTransactionsByCategory(1)
        assert(2 == transactions.size)
        transactions = transactionsDao.getTransactionsByCategory(2)
        assert(3 == transactions.size)
        transactions = transactionsDao.getTransactionsByCategory(3)
        assert(2 == transactions.size)
        transactions = transactionsDao.getTransactionsByCategory(4)
        assert(4 == transactions.size)
        transactions = transactionsDao.getTransactionsByCategory(5)
        assert(2 == transactions.size)
        transactions = transactionsDao.getTransactionsByCategory(6)
        assert(1 == transactions.size)

        transactionsDao.deleteAllEntriesFromTransactions()
        transactions = transactionsDao.getAllTransactions()
        assert(0 == transactions.size)
        categoryDao.deleteAllEntriesFromCategories()
        val categories = categoryDao.getAllCategories()
        assert(0 == categories.size)
    }

    @Test
    fun checkGetTransactionsSumByCategoryAndInterval() = runBlocking {
        categoryDao.insertCategory(Categories("Pasive", "cp1"))
        categoryDao.insertCategory(Categories("Pasive", "cp2"))
        categoryDao.insertCategory(Categories("Pasive", "cp3"))
        categoryDao.insertCategory(Categories("Active", "ca1"))
        categoryDao.insertCategory(Categories("Active", "ca2"))
        categoryDao.insertCategory(Categories("Datorii", "cd1"))

        val dateString1 = "2024-04-01"
        val date1 = stripTime(stringToDate(dateString1))
        val dateString2 = "2024-04-20"
        val date2 = stripTime(stringToDate(dateString2))

        val dateString3 = "2024-04-05"
        val date3 = stripTime(stringToDate(dateString3))
        val dateString4 = "2024-04-10"
        val date4 = stripTime(stringToDate(dateString4))
        val dateString5 = "2024-04-12"
        val date5 = stripTime(stringToDate(dateString5))
        val dateString6 = "2024-04-25"
        val date6 = stripTime(stringToDate(dateString6))

        transactionsDao.insertTransaction(Transactions("payee1", "desc1", 10.0, 1, date3))
        transactionsDao.insertTransaction(Transactions("payee2", "desc2", 15.0, 1, date4))
        transactionsDao.insertTransaction(Transactions("payee3", "desc3", 10.0, 2, date5))
        transactionsDao.insertTransaction(Transactions("payee4", "desc4", 15.0, 2, date1))
        transactionsDao.insertTransaction(Transactions("payee5", "desc5", 10.0, 2, date4))
        transactionsDao.insertTransaction(Transactions("payee6", "desc6", 15.0, 3, date5))
        transactionsDao.insertTransaction(Transactions("payee7", "desc7", 20.0, 3, date2))
        transactionsDao.insertTransaction(Transactions("payee8", "desc8", 10.0, 4, date3))
        transactionsDao.insertTransaction(Transactions("payee9", "desc9", 15.0, 4, date2))
        transactionsDao.insertTransaction(Transactions("payee10", "desc10", 10.0, 4, date4))
        transactionsDao.insertTransaction(Transactions("payee11", "desc11", 15.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee12", "desc12", 10.0, 5, date6))
        transactionsDao.insertTransaction(Transactions("payee13", "desc13", 15.0, 5, date3))
        transactionsDao.insertTransaction(Transactions("payee14", "desc14", 20.0, 6, date2))

        var sum = transactionsDao.getTransactionsSumByCategoryAndInterval(2, date3, date6)
        assert(20.0 == sum)
        sum = transactionsDao.getTransactionsSumByCategoryAndInterval(4, date1, date4)
        assert(35.0 == sum)
        sum = transactionsDao.getTransactionsSumByCategoryAndInterval(3, date3, date4)
        assert(0.0 == sum)
        sum = transactionsDao.getTransactionsSumByCategoryAndInterval(5, date4, date6)
        assert(10.0 == sum)

        transactionsDao.deleteAllEntriesFromTransactions()
        val transactions = transactionsDao.getAllTransactions()
        assert(0 == transactions.size)
        categoryDao.deleteAllEntriesFromCategories()
        val categories = categoryDao.getAllCategories()
        assert(0 == categories.size)
    }

    @Test
    fun checkGetTransactionsCategoryListQuery() = runBlocking {
        categoryDao.insertCategory(Categories("Pasive", "cp1"))
        categoryDao.insertCategory(Categories("Pasive", "cp2"))
        categoryDao.insertCategory(Categories("Pasive", "cp3"))
        categoryDao.insertCategory(Categories("Active", "ca1"))
        categoryDao.insertCategory(Categories("Active", "ca2"))
        categoryDao.insertCategory(Categories("Datorii", "cd1"))

        val dateString1 = "2024-04-01"
        val date1 = stripTime(stringToDate(dateString1))
        val dateString2 = "2024-04-20"
        val date2 = stripTime(stringToDate(dateString2))

        val dateString3 = "2024-04-05"
        val date3 = stripTime(stringToDate(dateString3))
        val dateString4 = "2024-04-10"
        val date4 = stripTime(stringToDate(dateString4))
        val dateString5 = "2024-04-12"
        val date5 = stripTime(stringToDate(dateString5))
        val dateString6 = "2024-04-25"
        val date6 = stripTime(stringToDate(dateString6))

        transactionsDao.insertTransaction(Transactions("payee1", "desc1", 10.0, 1, date3))
        transactionsDao.insertTransaction(Transactions("payee2", "desc2", 15.0, 1, date4))
        transactionsDao.insertTransaction(Transactions("payee3", "desc3", 10.0, 2, date5))
        transactionsDao.insertTransaction(Transactions("payee4", "desc4", 15.0, 2, date1))
        transactionsDao.insertTransaction(Transactions("payee5", "desc5", 10.0, 2, date4))
        transactionsDao.insertTransaction(Transactions("payee6", "desc6", 15.0, 3, date5))
        transactionsDao.insertTransaction(Transactions("payee7", "desc7", 20.0, 3, date2))
        transactionsDao.insertTransaction(Transactions("payee8", "desc8", 10.0, 4, date3))
        transactionsDao.insertTransaction(Transactions("payee9", "desc9", 15.0, 4, date2))
        transactionsDao.insertTransaction(Transactions("payee10", "desc10", 10.0, 4, date4))
        transactionsDao.insertTransaction(Transactions("payee11", "desc11", 15.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee12", "desc12", 10.0, 5, date6))
        transactionsDao.insertTransaction(Transactions("payee13", "desc13", 15.0, 5, date3))
        transactionsDao.insertTransaction(Transactions("payee14", "desc14", 20.0, 6, date2))

        val categoriesAndTransactions1 = transactionsDao.getTransactionsCategoryListQuery("Active")
        assert(categoriesAndTransactions1.size == 2)
        assert(categoriesAndTransactions1[0].category.name == "ca1" && categoriesAndTransactions1[0].transactions.size == 4)
        assert(categoriesAndTransactions1[1].category.name == "ca2" && categoriesAndTransactions1[1].transactions.size == 2)
        val categoriesAndTransactions2 = transactionsDao.getTransactionsCategoryListQuery("Pasive")
        assert(categoriesAndTransactions2.size == 3)
        assert(categoriesAndTransactions2[0].category.name == "cp1" && categoriesAndTransactions2[0].transactions.size == 2)
        assert(categoriesAndTransactions2[1].category.name == "cp2" && categoriesAndTransactions2[1].transactions.size == 3)
        assert(categoriesAndTransactions2[2].category.name == "cp3" && categoriesAndTransactions2[2].transactions.size == 2)
        val categoriesAndTransactions3 = transactionsDao.getTransactionsCategoryListQuery("Datorii")
        assert(categoriesAndTransactions3.size == 1)
        assert(categoriesAndTransactions3[0].category.name == "cd1" && categoriesAndTransactions3[0].transactions.size == 1)

        transactionsDao.deleteAllEntriesFromTransactions()
        val transactions = transactionsDao.getAllTransactions()
        assert(0 == transactions.size)
        categoryDao.deleteAllEntriesFromCategories()
        val categories = categoryDao.getAllCategories()
        assert(0 == categories.size)
    }

    @Test
    fun checkGetTransactionsCategoryList() = runBlocking {
        categoryDao.insertCategory(Categories("Pasive", "cp1"))
        categoryDao.insertCategory(Categories("Pasive", "cp2"))
        categoryDao.insertCategory(Categories("Pasive", "cp3"))
        categoryDao.insertCategory(Categories("Active", "ca1"))
        categoryDao.insertCategory(Categories("Active", "ca2"))
        categoryDao.insertCategory(Categories("Datorii", "cd1"))

        val dateString1 = "2024-04-01"
        val date1 = stripTime(stringToDate(dateString1))
        val dateString2 = "2024-04-03"
        val date2 = stripTime(stringToDate(dateString2))
        val dateString3 = "2024-04-05"
        val date3 = stripTime(stringToDate(dateString3))
        val dateString4 = "2024-04-10"
        val date4 = stripTime(stringToDate(dateString4))
        val dateString5 = "2024-04-12"
        val date5 = stripTime(stringToDate(dateString5))
        val dateString6 = "2024-04-25"
        val date6 = stripTime(stringToDate(dateString6))

        transactionsDao.insertTransaction(Transactions("payee1", "desc1", 10.0, 1, date4))
        transactionsDao.insertTransaction(Transactions("payee2", "desc2", 15.0, 1, date3))
        transactionsDao.insertTransaction(Transactions("payee3", "desc3", 10.0, 2, date5))
        transactionsDao.insertTransaction(Transactions("payee4", "desc4", 15.0, 2, date1))
        transactionsDao.insertTransaction(Transactions("payee5", "desc5", 10.0, 2, date4))
        transactionsDao.insertTransaction(Transactions("payee6", "desc6", 15.0, 3, date5))
        transactionsDao.insertTransaction(Transactions("payee7", "desc7", 20.0, 3, date2))
        transactionsDao.insertTransaction(Transactions("payee8", "desc8", 10.0, 4, date3))
        transactionsDao.insertTransaction(Transactions("payee9", "desc9", 15.0, 4, date2))
        transactionsDao.insertTransaction(Transactions("payee10", "desc10", 10.0, 4, date4))
        transactionsDao.insertTransaction(Transactions("payee11", "desc11", 15.0, 4, date1))
        transactionsDao.insertTransaction(Transactions("payee12", "desc12", 10.0, 5, date6))
        transactionsDao.insertTransaction(Transactions("payee13", "desc13", 15.0, 5, date3))
        transactionsDao.insertTransaction(Transactions("payee14", "desc14", 20.0, 6, date2))

        val categoriesAndTransactions1 = transactionsDao.getTransactionsCategoryList("Active")
        assert(categoriesAndTransactions1.size == 2)
        assert(categoriesAndTransactions1[0].category.name == "ca1" && categoriesAndTransactions1[0].transactions.size == 4)
        assert(categoriesAndTransactions1[0].transactions[0].date == date4)
        assert(categoriesAndTransactions1[0].transactions[1].date == date3)
        assert(categoriesAndTransactions1[0].transactions[2].date == date2)
        assert(categoriesAndTransactions1[0].transactions[3].date == date1)
        assert(categoriesAndTransactions1[1].category.name == "ca2" && categoriesAndTransactions1[1].transactions.size == 2)
        assert(categoriesAndTransactions1[1].transactions[0].date == date6)
        assert(categoriesAndTransactions1[1].transactions[1].date == date3)
        val categoriesAndTransactions2 = transactionsDao.getTransactionsCategoryList("Pasive")
        assert(categoriesAndTransactions2.size == 3)
        assert(categoriesAndTransactions2[0].category.name == "cp1" && categoriesAndTransactions2[0].transactions.size == 2)
        assert(categoriesAndTransactions2[0].transactions[0].date == date4)
        assert(categoriesAndTransactions2[0].transactions[1].date == date3)
        assert(categoriesAndTransactions2[1].category.name == "cp2" && categoriesAndTransactions2[1].transactions.size == 3)
        assert(categoriesAndTransactions2[1].transactions[0].date == date5)
        assert(categoriesAndTransactions2[1].transactions[1].date == date4)
        assert(categoriesAndTransactions2[1].transactions[2].date == date1)
        assert(categoriesAndTransactions2[2].category.name == "cp3" && categoriesAndTransactions2[2].transactions.size == 2)
        assert(categoriesAndTransactions2[2].transactions[0].date == date5)
        assert(categoriesAndTransactions2[2].transactions[1].date == date2)
        val categoriesAndTransactions3 = transactionsDao.getTransactionsCategoryList("Datorii")
        assert(categoriesAndTransactions3.size == 1)
        assert(categoriesAndTransactions3[0].category.name == "cd1" && categoriesAndTransactions3[0].transactions.size == 1)
        assert(categoriesAndTransactions3[0].transactions[0].date == date2)

        transactionsDao.deleteAllEntriesFromTransactions()
        val transactions = transactionsDao.getAllTransactions()
        assert(0 == transactions.size)
        categoryDao.deleteAllEntriesFromCategories()
        val categories = categoryDao.getAllCategories()
        assert(0 == categories.size)
    }

    @After
    fun tearDown() {
        budgetTrackerDatabase.close()
    }
}