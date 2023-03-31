package com.sharjahuniversity.type2dm_poc.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.*

sealed class NavigationItem(
    val route: String,
    val icon: Int,
    val titleId: Int,
    val iconBackgroundBrush: Brush,
    val iconBorderColor: Color
) {
    object Navigation : NavigationItem(
        "navigation",
        R.drawable.ic_baseline_add_24,
        R.string.navigation,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object Home : NavigationItem(
        "home",
        R.drawable.ic_home,
        R.string.home,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object Survey : NavigationItem(
        "survey",
        R.drawable.ic_survey,
        R.string.survey,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object Projects : NavigationItem(
        "projects",
        R.drawable.ic_project_list,
        R.string.projects,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object Suggestions : NavigationItem(
        "suggestions",
        R.drawable.ic_suggestions,
        R.string.suggestions,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object Profile : NavigationItem(
        "profile",
        R.drawable.ic_my_profile,
        R.string.profile,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object ProfileEdit : NavigationItem(
        "profile_edit",
        R.drawable.ic_my_profile,
        R.string.profile_edit,
        DefaultBlueBrush,
        NavigationScreenItemBorderColor
    )

    object Food : NavigationItem(
        "food",
        R.drawable.ic_food_icon,
        R.string.food,
        FoodScreenBrush,
        FoodSectionIconBorderColor
    )

    object AddFood : NavigationItem(
        "add_food",
        R.drawable.ic_food_icon,
        R.string.add_food,
        FoodScreenBrush,
        FoodSectionIconBorderColor
    )

    object Water : NavigationItem(
        "water",
        R.drawable.ic_glass_of_water,
        R.string.water,
        WaterScreenBrush,
        WaterDetailsScreenIconBorderColor
    )

    object Steps : NavigationItem(
        "steps",
        R.drawable.ic_steps,
        R.string.steps,
        DailyStepsScreenVioletBrush,
        DailyStepsDetailsScreenIconBorderColor
    )

    object Sleep : NavigationItem(
        "sleep",
        R.drawable.ic_sleep,
        R.string.sleep,
        DailySleepScreenGreenBrush,
        DailySleepDetailsScreenIconBorderColor
    )

    object Exercise : NavigationItem(
        "exercise",
        R.drawable.ic_exercise,
        R.string.exercise,
        DailyExerciseScreenYellowBrush,
        DailyExerciseScreenIconBorderColor
    )

    object Weight : NavigationItem(
        "weight",
        R.drawable.ic_weight_icon,
        R.string.weight,
        WeightScreenBrush,
        WeightScreenIconBorderColor
    )
}