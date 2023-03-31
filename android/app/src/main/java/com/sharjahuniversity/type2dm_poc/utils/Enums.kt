package com.sharjahuniversity.type2dm_poc.utils

enum class FoodCategory {
    Breakfast, Lunch, Dinner, Snacks, Empty
}

enum class GoalEnum(val itemName: String, val value: Float) {
    CalorieIntake("CalorieIntake", Constants.FoodIntakeRange),
    Water("Water", Constants.WaterIntakeGoal),
    Steps("Steps", Constants.StepsGoal),
    Sleep("Sleep", Constants.SleepHourGoal),
    CalorieBurn("CalorieBurn", Constants.CalorieBurnGoal),
    Weight("Weight", Constants.WeightGoal)
}

enum class SurveyScreen {
    Introduction, QuestionsAnswers, Complete
}

enum class SurveyQuestionsType(val value: String) {
    Checkbox("checkbox"), RadioButton("radio"), TextField("text")
}

enum class NavigationAnimationTransitionTime(val value: Int) {
    Slow(700), Medium(500), Fast(300)
}

enum class APIResponseStatus(val value: String) {
    Success("success"), Failed("failed")
}

enum class APIResponseSuccess(val value: String) {
    True("true"), False("false")
}

enum class NotificationTitle(val value: String) {
    NewSuggestions("New Suggestion"), NewSurvey("New Survey"), NewGoalsSet("New Goals Set")
}

enum class NotificationDataAction(val value: String) {
    NewSuggestions("NEW_SUGGESTION"), NewSurvey("NEW_SURVEY"), NewGoalsSet("NEW_GOALS_SET")
}

enum class NotificationDataKey(val value: String) {
    Action("action"), Title("title"), Body("body")
}

enum class UserStatus(val value: String) {
    Active("active"), Pending("pending"), Rejected("rejected")
}