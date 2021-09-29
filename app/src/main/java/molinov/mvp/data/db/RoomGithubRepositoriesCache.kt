package molinov.mvp.data.db

import molinov.mvp.data.GitHubRepository
import molinov.mvp.data.GitHubUser
import javax.inject.Inject

class RoomGithubRepositoriesCache @Inject constructor(
    private val db: GitHubDatabase
) : CacheRepositories {

    override fun fromModelToDb(
        repos: List<GitHubRepository>,
        id: Long
    ): List<RoomGithubRepository> {
        val roomRepos = repos.map { repo ->
            RoomGithubRepository(
                repo.id,
                repo.name,
                repo.description,
                repo.forks,
                repo.watchers,
                id
            )
        }
        db.repositoryDao.insert(roomRepos)
        return roomRepos
    }

    override fun fromDbToModel(user: GitHubUser): List<GitHubRepository>? {
        val roomUser = db.userDao.getByLogin(user.login)
        val roomRepos = db.repositoryDao.getByUserId(roomUser.id.toString())
        val repos = roomRepos?.map { roomRepo ->
            GitHubRepository(
                roomRepo.id,
                roomRepo.name,
                roomRepo.description,
                roomRepo.forks,
                roomRepo.watchers
            )
        }
        return repos
    }
}
