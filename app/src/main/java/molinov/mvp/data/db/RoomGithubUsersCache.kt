package molinov.mvp.data.db

import molinov.mvp.data.GitHubUser

class RoomGithubUsersCache : CacheUsers {

    override fun fromModelToDb(users: List<GitHubUser>): List<RoomGithubUser> {
        return users.map { user ->
            RoomGithubUser(
                user.id,
                user.login,
                user.avatarUrl,
                user.reposUrl
            )
        }
    }

    override fun fromDbToModel(roomUsers: List<RoomGithubUser>): List<GitHubUser> {
        return roomUsers.map { roomUser ->
            GitHubUser(
                roomUser.id,
                roomUser.login,
                roomUser.avatarUrl,
                roomUser.reposUrl,
            )
        }
    }
}
