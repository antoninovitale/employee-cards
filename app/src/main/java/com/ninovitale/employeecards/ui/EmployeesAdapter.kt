package com.ninovitale.employeecards.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ninovitale.employeecards.R
import com.ninovitale.employeecards.ui.model.EmployeeCard

class EmployeesAdapter : RecyclerView.Adapter<EmployeesViewHolder>(), Filterable {
    private var people: List<EmployeeCard> = emptyList()
    private var filteredPeople: List<EmployeeCard> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.employee_card, parent, false)
        return EmployeesViewHolder(view)
    }

    override fun getItemCount(): Int = filteredPeople.size

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        val person = filteredPeople.getOrNull(position)
        person?.let { personCard ->
            with(personCard) {
                holder.name.text = name
                holder.email.text = email
                holder.businessUnit.text = businessUnit
                holder.department.text = department
                holder.localOffice.text = localOffice
            }

        } ?: run {
            //shouldn't be possible, notify
        }
    }

    fun setItems(people: List<EmployeeCard>) {
        this.people = people.toList()
        this.filteredPeople = this.people
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                filteredPeople = if (constraint.isNullOrBlank()) {
                    people
                } else {
                    people.filter {
                        it.name.contains(constraint, ignoreCase = true)
                                || it.email.contains(constraint, ignoreCase = true)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredPeople
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredPeople = results.values as List<EmployeeCard>
                notifyDataSetChanged()
            }
        }
    }

}