package molinov.mvp.data.db

import molinov.mvp.data.GitHubRepository
import molinov.mvp.data.GitHubUser

interface CacheUsers {

    fun fromModelToDb(users: List<GitHubUser>): List<RoomGithubUser>
    fun fromDbToModel(roomUsers: List<RoomGithubUser>): List<GitHubUser>
}

interface CacheRepositories {

    fun fromModelToDb(repos: List<GitHubRepository>, id: Long): List<RoomGithubRepository>
    fun fromDbToModel(roomRepos: List<RoomGithubRepository>?): List<GitHubRepository>?
}
