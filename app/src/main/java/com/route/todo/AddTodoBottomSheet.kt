package com.route.todo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.route.todo.DataBase.Model.Todo
import com.route.todo.DataBase.MyDataBase
import java.text.SimpleDateFormat
import java.util.*

lateinit var finishBtn: Button
lateinit var titleTv: EditText
lateinit var discTv: EditText
lateinit var dateTv: TextView
lateinit var discLayout: TextInputLayout
lateinit var titleLayout: TextInputLayout
var onTodoAddedListener: AddTodoBottomSheet.OnTodoAddedListener?=null
class AddTodoBottomSheet:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.addtodobottomsheet, container, false)
        context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewsListener()
    }

    private fun viewsListener() {
        dateTv.setOnClickListener {
            showDatePicker()
        }
        showDate()

        finishBtn.setOnClickListener {
            val title = titleTv.text.toString()
            val disc = discTv.text.toString()
            if (validate(title, disc)) {
                //
              var todo=Todo(name = title, detail = disc, date = calender.clearTime().time, isDone = false)
                MyDataBase.getInstance(requireContext().applicationContext)
                    .todoDao()
                    .addTodo(todo)
                onTodoAddedListener?.onTodoAdded()

                dismiss()
            }

        }
    }
    var onTodoAddedListener:OnTodoAddedListener?=null
    interface OnTodoAddedListener{
        fun onTodoAdded()
    }

        fun showDate() {
            dateTv.setText(simpleDateFormat.format(Date(calender.timeInMillis)))
        }

        val calender = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        fun showDatePicker() {
            val datePicker = DatePickerDialog(
                requireContext(),
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        picker: DatePicker?,
                        year: Int,
                        month: Int,
                        dayofmonth: Int
                    ) {
                        calender.set(Calendar.DAY_OF_MONTH, dayofmonth)
                        calender.set(Calendar.MONTH, month)
                        calender.set(Calendar.YEAR, year)
                        showDate()

                    }
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)


            )
            datePicker.show()

        }

        fun initViews() {
            finishBtn = requireView().findViewById(R.id.finish)
            titleTv = requireView().findViewById(R.id.add_title)
            discTv = requireView().findViewById(R.id.add_disc)
            dateTv = requireView().findViewById(R.id.selcte_Date)
            discLayout = requireView().findViewById(R.id.disc_layout)
            titleLayout = requireView().findViewById(R.id.title_layout)
        }

        fun validate(title: String, disc: String): Boolean {
            var isValide = true
            if (title.isBlank()) {
                titleLayout.error = "Please enter title"
                isValide = false

            } else
                titleTv.error = null
            isValide = true
            if (disc.isBlank()) {
                discLayout.error = "Please enter title"
                isValide = false

            } else
                discTv.error = null
            return isValide
        }
    }
