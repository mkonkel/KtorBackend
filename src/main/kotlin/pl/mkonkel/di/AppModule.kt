package pl.mkonkel.di

import org.koin.dsl.module
import pl.mkonkel.database.DatabaseFactory
import pl.mkonkel.database.DatabaseFactoryImpl
import pl.mkonkel.features.users.data.dao.UsersDAOFacade
import pl.mkonkel.features.users.data.dao.UsersDAOFacadeImpl
import pl.mkonkel.features.users.domain.UsersRepository
import pl.mkonkel.features.users.domain.UsersRepositoryImpl

val appModule = module {
    single<UsersRepository> { UsersRepositoryImpl(get()) }
    single<DatabaseFactory> { DatabaseFactoryImpl() }
    single<UsersDAOFacade> { UsersDAOFacadeImpl() }
}