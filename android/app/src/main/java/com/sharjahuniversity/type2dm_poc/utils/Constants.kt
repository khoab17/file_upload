package com.sharjahuniversity.type2dm_poc.utils

object Constants {
    const val PACKAGE_NAME = "com.townhall.social_audit_south_africa"

    //Date time format
    const val DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss aa"
    const val DATE_TIME_FORMAT_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT = "dd-MM-yyyy"
    const val DATE_FORMAT_TO_UPLOAD = "yyyy-MM-dd"

    //keys and tags
    const val DATABASE_NAME = "type2dm_database"
    const val SharedPreferencesFileName = "SharjahUniversity"
    const val IsProfileDataInputComplete = "isProfileDataInputComplete"
    const val DAILY_RESET_WORK_TAG = "DAILY_RESET_WORK_TAG"
    const val DATA_UPLOAD_WORK_TAG = "DATA_UPLOAD_WORK_TAG"
    const val UserId = "UserId"
    const val HasNewSuggestion = "HasNewSuggestion"
    const val HasNewSurvey = "HasNewSurvey"
    const val HasNewGoalsSet = "HasNewGoalsSet"
    const val IsUserActive = "IsUserActive"
    const val IsUserRequestRejected = "IsUserActive"
    const val UserImageUrl = "UserImageUrl"
    const val UserProfile = "UserId"
    const val Cookie = "Cookie"
    const val HeaderSetCookie = "Set-Cookie"
    const val HeaderCookie = "Cookie"
    const val ServerClientIDForGoogle =
        "540385108986-uhdt504qi18hnnkne4ta48q7p5frig3f.apps.googleusercontent.com"

    //Broadcasts
    const val ACTION_NEW_SUGGESTIONS = "$PACKAGE_NAME.ACTION_NEW_SUGGESTIONS"
    const val ACTION_NEW_SURVEY = "$PACKAGE_NAME.ACTION_NEW_SURVEY"
    const val ACTION_NEW_GOALS_SET = "$PACKAGE_NAME.ACTION_NEW_GOALS_SET"
    const val ACTION_UPLOAD_NEW_DATA = "$PACKAGE_NAME.ACTION_UPLOAD_NEW_DATA"

    //Constants
    const val Calorie = "Calorie"
    const val Minute = "Minute"
    const val AddButton = "Add button"
    const val AddIcon = "Add icon"
    const val RemoveButton = "Remove button"
    const val CrossButton = "Close button"
    const val Search = "Search"
    const val FitLabIcon = "FitLab icon"
    const val DialogIcon = "Dialog icon"
    const val DropDownIcon = "DropDown icon"
    const val WaterIcon = "Water icon"
    const val ProfilePicture = "Profile picture"
    const val BackButton = "Back button"
    const val Version = "Version %1s"
    const val AddItemDialogTextFieldString = "%1s"
    val Genders = listOf("Male", "Female", "Other")
    const val WeightInKg = "%1s KG"
    const val WeightValueMinimum = 30
    const val WeightValueMaximum = 150
    const val HeightInFeetInch = "%1d'%2d\""
    const val HeightInCM = "%1s CM"
    const val HeightValueFeetMinimum = 1
    const val HeightValueFeetMaximum = 7
    const val HeightValueInchMinimum = 0
    const val HeightValueInchMaximum = 11
    const val HeightValueCMMinimum = 50
    const val HeightValueCMMaximum = 250
    const val ActivityGoal = "%1s"
    const val ActivityGoalMinimum = 1000
    const val ActivityGoalMaximum = 4000
    const val WeightGoalText = "%1s KG"
    const val WeightGoalMinimum = 30
    const val WeightGoalMaximum = 100
    const val FoodIntakeRange = 1800f
    const val WaterIntakeGoal = 6f
    const val StepsGoal = 5000f
    const val CalorieBurnGoal = 1000f
    const val SleepHourGoal = 8f
    const val WeightGoal = 55f
    val GoalEnums = mapOf(
        GoalEnum.CalorieIntake.itemName to GoalEnum.CalorieIntake.value,
        GoalEnum.CalorieBurn.itemName to GoalEnum.CalorieBurn.value,
        GoalEnum.Water.itemName to GoalEnum.Water.value,
        GoalEnum.Steps.itemName to GoalEnum.Steps.value,
        GoalEnum.Sleep.itemName to GoalEnum.Sleep.value,
        GoalEnum.Weight.itemName to GoalEnum.Weight.value,
    )
    const val CMPerInch = 2.54
    const val FloatStringFormatUpto2Decimal = "%1.2f"
    const val MinimumRating = 1
    const val MaximumRating = 5
}