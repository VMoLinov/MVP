package molinov.mvp.data.db

import molinov.mvp.data.GitHubUser
import javax.inject.Inject

class RoomGithubUsersCache @Inject constructor(
    private val db: GitHubDatabase
) : CacheUsers {

    override fun fromModelToDb(users: List<GitHubUser>): List<RoomGithubUser> {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id,
                user.login,
                user.avatarUrl,
                user.reposUrl
            )
        }
        db.userDao.insert(roomUsers)
        return roomUsers
    }

    override fun allFromDbToModel(): List<GitHubUser> {
        val roomUsers = db.userDao.getAll()
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
