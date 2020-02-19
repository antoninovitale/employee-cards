package com.ninovitale.employeecards.api.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Employees(
    val data: List<Data>? = null,
    val included: List<Data>? = null,
    val links: Links? = null,
    val meta: Meta? = null
)

@JsonSerializable
data class Data(
    val attributes: Attributes? = null,
    val id: String,
    val links: Links? = null,
    val relationships: Relationships? = null,
    val type: String? = null
)

@JsonSerializable
data class Attributes(
    val email: String? = null,
    @Json(name = "Age") val age: String? = null,
    val avatar: Any? = null,
    @Json(name = "Business Unit") val businessUnit: String? = null,
    @Json(name = "Commute Time") val commuteTime: Int? = null,
    @Json(name = "Department") val department: String? = null,
    val employmentStart: String? = null,
    val external: Boolean? = null,
    val features: List<String?>? = null,
    val firstName: String? = null,
    @Json(name = "Gender") val gender: String? = null,
    val identifier: Any? = null,
    @Json(name = "Job Level") val jobLevel: String? = null,
    val lastName: String? = null,
    @Json(name = "Last Year Bonus") val lastYearBonus: Int? = null,
    @Json(name = "Local Office") val localOffice: String? = null,
    val name: String? = null,
    @Json(name = "% of target") val ofTarget: Int? = null,
    @Json(name = "Region") val region: String? = null,
    @Json(name = "Salary") val salary: Int? = null,
    @Json(name = "Tenure") val tenure: String? = null
)

@JsonSerializable
data class Links(val self: String? = null)

@JsonSerializable
data class Relationships(
    val account: RelationshipsData? = null,
    val company: RelationshipsData? = null,
    @Json(name = "Manager") val manager: RelationshipsData? = null,
    val phones: Phones? = null
)

@JsonSerializable
data class RelationshipsData(val data: Data? = null)

@JsonSerializable
data class Phones(val data: List<Any?>? = null)

@JsonSerializable
data class Meta(val page: Page? = null)

@JsonSerializable
data class Page(val total: Int? = null)