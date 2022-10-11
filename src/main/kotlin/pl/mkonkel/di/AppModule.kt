package pl.mkonkel.di

import org.koin.dsl.module
import pl.mkonkel.features.users.domain.UsersRepository
import pl.mkonkel.features.users.domain.UsersRepositoryImpl

val appModule = module {
    single<UsersRepository> { UsersRepositoryImpl() }
}