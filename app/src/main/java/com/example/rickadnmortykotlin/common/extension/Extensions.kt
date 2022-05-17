package com.example.rickadnmortykotlin.common.extension

import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String){
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}

fun RadioGroup.getText(): String {
    val checkedId = this.checkedRadioButtonId
    val button: RadioButton = this.findViewById(checkedId)
    return button.text.toString()
}