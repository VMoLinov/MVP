package molinov.mvp.data.db

import molinov.mvp.data.GitHubRepository

class RoomGithubRepositoriesCache : CacheRepositories {

    override fun fromModelToDb(
        repos: List<GitHubRepository>,
        id: Long
    ): List<RoomGithubRepository> {
        return repos.map { repo ->
            RoomGithubRepository(
                repo.id,
                repo.name,
                repo.description,
                repo.forks,
                repo.watchers,
                id
            )
        }
    }

    override fun fromDbToModel(roomRepos: List<RoomGithubRepository>?): List<GitHubRepository>? {
        return roomRepos?.map { roomRepo ->
            GitHubRepository(
                roomRepo.id,
                roomRepo.name,
                roomRepo.description,
                roomRepo.forks,
                roomRepo.watchers
            )
        }
    }
}
