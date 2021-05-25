package com.mmk.quotesapp.ui.fragments.quoteslist

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mmk.domain.interaction.quote.allquotes.GetQuotesByPaginationUseCase
import com.mmk.domain.model.Quote
import com.mmk.domain.model.onSuccess
import com.mmk.quotesapp.ui.base.BaseViewModel
import com.mmk.quotesapp.ui.base.UiState
import timber.log.Timber

/**
 * Created by mirzemehdi on 8/12/20
 */
class QuotesViewModel constructor(
    private val quotesByPaginationUseCase: GetQuotesByPaginationUseCase,

    ) : BaseViewModel() {

    var quotesList: LiveData<PagingData<Quote>> = MutableLiveData()

    init {
        getQuotes()
    }


    private fun getQuotes() {
        executeUseCase {
            quotesByPaginationUseCase().onSuccess {
                quotesList = it.asLiveData().cachedIn(viewModelScope)
                _uiState.value = UiState.HasData
            }
        }

    }

}