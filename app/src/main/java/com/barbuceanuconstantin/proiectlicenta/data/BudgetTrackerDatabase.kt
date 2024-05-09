package com.barbuceanuconstantin.proiectlicenta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
            entities = [Categories::class, MainCategories::class, Transactions::class, Budgets::class],
            version = 1, exportSchema = false
)
abstract class BudgetTrackerDatabase: RoomDatabase() {
    abstract fun mainCategoryDao(): MainCategoryDAO
    abstract fun categoryDao(): CategoryDAO
    abstract fun transactionDao(): TransactionsDAO
    abstract fun budgetDao(): BudgetsDAO

    companion object {
        @Volatile
        private var instance1: BudgetTrackerDatabase? = null

        fun getInstance(context: Context): BudgetTrackerDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = instance1

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BudgetTrackerDatabase::class.java,
                        "sleep_history_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    instance1 = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}
