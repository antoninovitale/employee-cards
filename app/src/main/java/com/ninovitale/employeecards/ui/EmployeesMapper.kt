package com.ninovitale.employeecards.ui

import com.ninovitale.employeecards.api.model.Data
import com.ninovitale.employeecards.api.model.Employees
import com.ninovitale.employeecards.api.model.Gist
import com.ninovitale.employeecards.ui.model.EmployeeCard
import com.squareup.moshi.Moshi

interface Mapper<in I, out O> {
    fun map(value: I): List<O>
}

class EmployeesMapper(private val moshi: Moshi) : Mapper<Gist, EmployeeCard> {
    override fun map(value: Gist): List<EmployeeCard> {
        val employeesFileContent = value.files.entries.firstOrNull()?.value?.content
        val employees = moshi.adapter(Employees::class.java).fromJson(employeesFileContent)

        val employeesList = employees?.data?.let { employees.included?.plus(it) } ?: employees?.data
        return filterData(employeesList)
    }

    private fun filterData(data: List<Data>?): List<EmployeeCard> {
        return data
//            ?.filter { it.attributes?.jobLevel?.contains("manager", ignoreCase = true) == true }
            ?.filter { it.type == "employees" }
            ?.distinctBy { it.id }
            ?.map {
                val attributes = it.attributes
                with(attributes) {
                    EmployeeCard(
                        it.id,
                        this?.name ?: "",
                        it.relationships?.account?.data?.id?.let { id -> getEmail(data, id) }
                            ?: "",
                        this?.department ?: "",
                        this?.businessUnit ?: "",
                        this?.localOffice ?: ""
                    )
                }
            }

            ?: emptyList()
    }

    private fun getEmail(data: List<Data>?, accountId: String): String {
        return data
            ?.filter { it.type == "accounts" }
            ?.firstOrNull { account -> account.id == accountId }?.attributes?.email ?: ""
    }
}