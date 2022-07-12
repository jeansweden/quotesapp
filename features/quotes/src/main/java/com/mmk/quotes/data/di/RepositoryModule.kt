package com.mmk.quotes.data.di

import com.mmk.quotes.data.repository.QuotesRepositoryImpl
import com.mmk.quotes.domain.repository.QuotesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<QuotesRepository> { QuotesRepositoryImpl(get()) }
}
