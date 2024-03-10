package com.barbuceanuconstantin.proiectlicenta.view.screenmodules

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.RememberMe
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.RememberMe
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.barbuceanuconstantin.proiectlicenta.R
import com.barbuceanuconstantin.proiectlicenta.view.screen.CategoriesScreenComposable
import com.barbuceanuconstantin.proiectlicenta.view.screen.PrincipalScreenComposable
import com.barbuceanuconstantin.proiectlicenta.view.screen.TransactionsScreenComposable

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@OptIn(ExperimentalFoundationApi::class)
class MenuScreensSwipeableTabRows() {

    @Composable
    fun showMenu() {
        val tabItems = listOf(
            TabItem(
                title = stringResource(id = R.string.acasa),
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
            TabItem(
                title = stringResource(id = R.string.tranzactii),
                unselectedIcon = Icons.Outlined.Money,
                selectedIcon = Icons.Filled.Money
            ),
            TabItem(
                title = stringResource(id = R.string.categorii),
                unselectedIcon = Icons.Outlined.Category,
                selectedIcon = Icons.Filled.Category
            ),
            TabItem(
                title = stringResource(id = R.string.mementouri),
                unselectedIcon = Icons.Outlined.RememberMe,
                selectedIcon = Icons.Filled.RememberMe
            ),
            TabItem(
                title = stringResource(id = R.string.valute),
                unselectedIcon = Icons.Outlined.CurrencyExchange,
                selectedIcon = Icons.Filled.CurrencyExchange
            ),
            TabItem(
                title = stringResource(id = R.string.sumar_buget),
                unselectedIcon = Icons.Outlined.Summarize,
                selectedIcon = Icons.Filled.Summarize
            ),
            TabItem(
                title = stringResource(id = R.string.calendar),
                unselectedIcon = Icons.Outlined.CalendarMonth,
                selectedIcon = Icons.Filled.CalendarMonth
            ),
            TabItem(
                title = stringResource(id = R.string.grafice),
                unselectedIcon = Icons.Outlined.AutoGraph,
                selectedIcon = Icons.Filled.AutoGraph
            )
        )

        var selectedTabIndex by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //am adaugat minOf pt siguranta in caz ca se modifica lista
            ScrollableTabRow(selectedTabIndex = minOf(tabItems.count(), selectedTabIndex),
                             modifier = Modifier.fillMaxHeight(100f / LocalConfiguration.current.screenHeightDp)) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                                    selectedTabIndex = index
                                  },
                        text = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if(index == selectedTabIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> {
                    PrincipalScreenComposable.principalScreenLayout()
                }
                1 -> {
                    TransactionsScreenComposable.transactionsLayout()
                }
                2 -> {
                    CategoriesScreenComposable.categoriesLayout()
                }
                3 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(text = tabItems[3].title)
                    }
                }
                4 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(text = tabItems[4].title)
                    }
                }
                5 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(text = tabItems[5].title)
                    }
                }
                6 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(text = tabItems[6].title)
                    }
                }
                7 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(text = tabItems[6].title)
                    }
                }
            }
        }
    }
}