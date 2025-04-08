package com.wower.selfcareapp.feature_journal.data.data_source

import android.content.Context
import com.wower.selfcareapp.R
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun textFileDataToMutableList(
    context: Context,
    fileName: String
): MutableList<Pair<String, Int>> {
    var data: MutableList<Pair<String,Int>> = mutableListOf<Pair<String, Int>>()
    var index = 0
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.forEachLine {
            data.add(Pair(it.removeRange( 0, 5 ), index++))
        }
        reader.close()
    } catch(e: Exception) {
        e.printStackTrace()
    }
    
    return data
}
