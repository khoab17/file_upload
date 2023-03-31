package com.sharjahuniversity.type2dm_poc.data.remote

object APIUrls {
    const val BASE_URL = "http://townhall.bjitgroup.com:8001/api/v1/"
    //below url added as local server using developer's device ip
    //const val BASE_URL = "http://192.168.19.94:8001/api/v1/"
    const val AuthUserSignUpGoogle = "auth/google/user"
    private const val PathId = "/{id}"
    const val PathIdValue = "id"
    private const val All = "/all"
    const val UpdateUser = "users$PathId"
    const val User = "users$PathId"
    const val Foods = "foods"
    const val Exercises = "exercises"
    const val Days = "days"
    const val Suggestions = "suggestions$PathId$All"
    const val Survey = "surveys"
    const val UploadSurveyAnswer = "users/add_survey"
    const val IconBaseURL = "https://devapi.fitlab-su.net/"
    const val Projects = "projects"
}