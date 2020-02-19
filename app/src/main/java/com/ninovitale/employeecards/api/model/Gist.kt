package com.ninovitale.employeecards.api.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Gist(
    val comments: Int? = null,
    @Json(name = "comments_url") val commentsUrl: String? = null,
    @Json(name = "commits_url") val commitsUrl: String? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    val description: String? = null,
    @Json(name = "files") val files: Map<String, GistFile> = emptyMap(),
    val forks: List<Any?>? = null,
    @Json(name = "forks_url") val forksUrl: String? = null,
    @Json(name = "git_pull_url") val gitPullUrl: String? = null,
    @Json(name = "git_push_url") val gitPushUrl: String? = null,
    val history: List<History?>? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    val id: String? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    val owner: Owner? = null,
    val `public`: Boolean? = null,
    val truncated: Boolean? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    val url: String? = null,
    val user: Any? = null
)

@JsonSerializable
data class GistFile(
    val content: String? = null,
    val filename: String? = null,
    val language: String? = null,
    @Json(name = "raw_url") val rawUrl: String? = null,
    val size: Int? = null,
    val truncated: Boolean? = null,
    val type: String? = null
)

@JsonSerializable
data class History(
    @Json(name = "change_status") val changeStatus: ChangeStatus? = null,
    @Json(name = "committed_at") val committedAt: String? = null,
    val url: String? = null,
    val user: User? = null,
    val version: String? = null
)

@JsonSerializable
data class ChangeStatus(
    val additions: Int? = null,
    val deletions: Int? = null,
    val total: Int? = null
)

@JsonSerializable
data class User(
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "events_url") val eventsUrl: String? = null,
    @Json(name = "followers_url") val followersUrl: String? = null,
    @Json(name = "following_url") val followingUrl: String? = null,
    @Json(name = "gists_url") val gistsUrl: String? = null,
    @Json(name = "gravatar_id") val gravatarId: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    val id: Int? = null,
    val login: String? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "organizations_url") val organizationsUrl: String? = null,
    @Json(name = "received_events_url") val receivedEventsUrl: String? = null,
    @Json(name = "repos_url") val reposUrl: String? = null,
    @Json(name = "site_admin") val siteAdmin: Boolean? = null,
    @Json(name = "starred_url") val starredUrl: String? = null,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String? = null,
    val type: String? = null,
    val url: String? = null
)

@JsonSerializable
data class Owner(
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "events_url") val eventsUrl: String? = null,
    @Json(name = "followers_url") val followersUrl: String? = null,
    @Json(name = "following_url") val followingUrl: String? = null,
    @Json(name = "gists_url") val gistsUrl: String? = null,
    @Json(name = "gravatar_id") val gravatarId: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    val id: Int? = null,
    val login: String? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "organizations_url") val organizationsUrl: String? = null,
    @Json(name = "received_events_url") val receivedEventsUrl: String? = null,
    @Json(name = "repos_url") val reposUrl: String? = null,
    @Json(name = "site_admin") val siteAdmin: Boolean? = null,
    @Json(name = "starred_url") val starredUrl: String? = null,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String? = null,
    val type: String? = null,
    val url: String? = null
)