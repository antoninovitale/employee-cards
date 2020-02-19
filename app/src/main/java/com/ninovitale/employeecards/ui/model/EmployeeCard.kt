package com.ninovitale.employeecards.ui.model

data class EmployeeCard(
    val id: String,
    val name: String,
    val email: String,
    val department: String,
    val businessUnit: String,
    val localOffice: String
)