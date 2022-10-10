package pl.mkonkel.features.games

import pl.mkonkel.features.games.domain.GamesRepository
import pl.mkonkel.features.games.domain.GamesRepositoryImpl

object GamesFeature {
    val repository: GamesRepository = GamesRepositoryImpl()
}