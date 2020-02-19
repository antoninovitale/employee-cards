package com.ninovitale.employeecards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ninovitale.employeecards.api.model.Gist
import com.ninovitale.employeecards.data.GistsRepository
import com.ninovitale.employeecards.tools.MySchedulers
import com.ninovitale.employeecards.ui.model.EmployeeCard
import com.ninovitale.employeecards.ui.model.ViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class EmployeesViewModelFactory(
    private val employeeMapper: Mapper<Gist, EmployeeCard>,
    private val gistsRepository: GistsRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmployeesViewModel(
            employeeMapper,
            gistsRepository
        ) as T
    }
}

class EmployeesViewModel(
    private val employeeMapper: Mapper<Gist, EmployeeCard>,
    private val gistsRepository: GistsRepository
) : ViewModel() {
    private val liveData = MutableLiveData<List<EmployeeCard>>()
    private val viewState = MutableLiveData<ViewState>()
    private val disposables = CompositeDisposable()

    fun getViewState(): LiveData<ViewState> = viewState

    fun getEmployees(): LiveData<List<EmployeeCard>> {
        viewState.postValue(ViewState.Loading(isLoading = true))
        gistsRepository.getGist()
            .subscribeOn(MySchedulers.ioThread)
            .observeOn(MySchedulers.ioThread)
            .subscribe(
                { gist ->
                    val cards = employeeMapper.map(gist)
                    viewState.postValue(ViewState.Loading(isLoading = false))
                    liveData.postValue(cards)
                },
                {
                    viewState.postValue(ViewState.Error(it.message))
                }
            )
            .addTo(disposables)
        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}