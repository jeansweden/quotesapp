package com.mmk.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.mmk.common.ui.MR
import com.mmk.common.ui.theme.MyApplicationTheme
import com.mmk.common.ui.theme.getColors
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun MainScreen(
    darkTheme: Boolean = false,
    currentRoute: String,
    onSelectedTopLevelDestination: (TopLevelDestination) -> Unit,
    navigationContent: @Composable () -> Unit
) {
    MyApplicationTheme(darkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = getColors().background
        ) {
            MainScreen(
                currentRoute = currentRoute,
                onSelectedTopLevelDestination = onSelectedTopLevelDestination,
                navigationContent = navigationContent
            )
        }
    }
}

@Composable
private fun MainScreen(
    currentRoute: String,
    onSelectedTopLevelDestination: (TopLevelDestination) -> Unit,
    navigationContent: @Composable () -> Unit
) {

    val isBottomNavVisible = currentRoute != TopLevelDestination.ADD_QUOTE.route
    val selectedBottomNavItem = TopLevelDestination.getDestinationFromRoute(currentRoute)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                navigationContent()
            }
            AnimatedVisibility(isBottomNavVisible, modifier = Modifier.fillMaxWidth()) {
                BottomNavigationView(
                    modifier = Modifier.fillMaxWidth(),
                    selectedDestination = selectedBottomNavItem
                ) {
                    onSelectedTopLevelDestination(it)
                }
            }
        }
        AddQuoteButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            isVisible = isBottomNavVisible,
            onClick = { onSelectedTopLevelDestination(TopLevelDestination.ADD_QUOTE) }
        )
    }
}

@Composable
private fun AddQuoteButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onClick: () -> Unit
) {
    val addButtonSize = 74.dp
    AnimatedVisibility(
        isVisible,
        modifier = modifier
            .size(addButtonSize)
            .offset(y = (addButtonSize / 2 - 56.dp))
            .zIndex(2f)
    ) {
        FloatingActionButton(
            containerColor = getColors().secondary,
            contentColor = getColors().onSecondary,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
            shape = CircleShape,
            onClick = onClick,
        ) {
            Icon(
                painter = painterResource(MR.images.ic_add),
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
private fun BottomNavigationView(
    modifier: Modifier = Modifier,
    selectedDestination: TopLevelDestination = TopLevelDestination.QUOTES,
    onSelectedTopLevelDestination: (TopLevelDestination) -> Unit
) {
    BottomNavigation(
        backgroundColor = getColors().primary,
        modifier = modifier
    ) {

        val disabledItems = listOf(TopLevelDestination.ADD_QUOTE)

        TopLevelDestination.values().forEachIndexed { index, destination ->
            BottomNavigationItem(
                label = stringResource(destination.labelStringResource),
                icon = destination.iconRes?.let { painterResource(it) },
                selected = selectedDestination == destination,
                enabled = destination !in disabledItems
            ) {
                onSelectedTopLevelDestination(destination)
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationItem(
    label: String,
    icon: Painter? = null,
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BottomNavigationItem(
        selected = selected,
        enabled = enabled,
        onClick = { onClick() },
        icon = {
            icon?.let {
                Icon(
                    painter = icon,
                    contentDescription = label,
                    tint = if (selected) getColors().onPrimary else getColors().inActive,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .size(24.dp)
                )
            }
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = if (selected) getColors().onPrimary else getColors().inActive,
                modifier = Modifier.padding(8.dp)
            )
        },
        selectedContentColor = getColors().onPrimary,
        unselectedContentColor = getColors().inActive

    )
}
