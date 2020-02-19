package com.ninovitale.employeecards.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.employee_card.view.*

class EmployeesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.employee_name
    val email = view.employee_email
    val department = view.employee_department
    val businessUnit = view.employee_unit
    val localOffice = view.employee_address
}