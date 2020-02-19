package com.ninovitale.employeecards

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.ninovitale.employeecards.api.model.Gist
import com.ninovitale.employeecards.data.GistsRepository
import com.ninovitale.employeecards.ui.EmployeesViewModel
import com.ninovitale.employeecards.ui.Mapper
import com.ninovitale.employeecards.ui.model.EmployeeCard
import com.ninovitale.employeecards.ui.model.ViewState
import com.ninovitale.employeecards.utils.InjectTestScheduler
import com.ninovitale.employeecards.utils.InstantExecutorExtension
import com.ninovitale.employeecards.utils.TestSchedulerExtension
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class, TestSchedulerExtension::class)
class ViewModelTest {
    @InjectTestScheduler
    internal lateinit var scheduler: TestScheduler

    @Mock
    lateinit var repo: GistsRepository

    @Mock
    lateinit var employeeMapper: Mapper<Gist, EmployeeCard>

    @Mock
    lateinit var stateObserver: Observer<ViewState>

    @Mock
    lateinit var observer: Observer<List<EmployeeCard>>

    private lateinit var viewModel: EmployeesViewModel

    @BeforeAll
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @BeforeEach
    fun setUp() {
        viewModel = EmployeesViewModel(employeeMapper, repo)
    }

    @Test
    fun test_loadEmployees() {
        whenever(employeeMapper.map(any())).thenReturn(createSampleCards())
        whenever(repo.getGist()).thenReturn(Single.just(Gist()))

        viewModel.getViewState().observeForever(stateObserver)
        viewModel.getEmployees().observeForever(observer)

        Assertions.assertEquals(ViewState.Loading(true), viewModel.getViewState().value)

        scheduler.triggerActions()

        Assertions.assertEquals(ViewState.Loading(false), viewModel.getViewState().value)
        Assertions.assertTrue(viewModel.getEmployees().value?.isNotEmpty() == true)
    }

    @Test
    fun test_loadEmployees_error() {
        whenever(employeeMapper.map(any())).thenReturn(createSampleCards())
        whenever(repo.getGist()).thenReturn(Single.error(Exception("error!")))

        viewModel.getViewState().observeForever(stateObserver)
        viewModel.getEmployees().observeForever(observer)

        Assertions.assertEquals(ViewState.Loading(true), viewModel.getViewState().value)

        scheduler.triggerActions()

        Assertions.assertEquals(ViewState.Error("error!"), viewModel.getViewState().value)
    }

    private fun createSampleCards(count: Int = 10): List<EmployeeCard> {
        val cards = mutableListOf<EmployeeCard>()
        repeat(count) {
            cards.add(
                EmployeeCard(
                    "$it",
                    "name $it",
                    "email $it",
                    "dept $it",
                    "bu $it",
                    "office $it"
                )
            )
        }
        return cards
    }
}