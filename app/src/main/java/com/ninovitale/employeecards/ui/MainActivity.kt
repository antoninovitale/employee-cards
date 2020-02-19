package com.ninovitale.employeecards.ui

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.ninovitale.employeecards.MainApp
import com.ninovitale.employeecards.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private var adapter: EmployeesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setup()
    }

    private fun setup() {
        if (adapter == null) adapter = EmployeesAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        val snapHelper: SnapHelper = GravitySnapHelper(Gravity.TOP)
        snapHelper.attachToRecyclerView(recycler_view)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(EmployeesViewModel::class.java)
        viewModel.getViewState()
            .observe(this, Observer { state ->
                //handle state, coming soon
            })
        viewModel.getEmployees()
            .observe(this, Observer { people ->
                adapter?.setItems(people)
            })
        toolbar_search.doAfterTextChanged { adapter?.filter?.filter(it.toString()) }
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }
}