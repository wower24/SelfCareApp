package com.wower.selfcareapp.feature_journal.data.data_source

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun textFileDataToList(
    context: Context,
    fileName: String
): List<String> {
    var data: List<String> = listOf<String>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.forEachLine { line ->
            data = data.plus(line.removeRange(0, 5))
        }
        reader.close()
    } catch(e: Exception) {
        e.printStackTrace()
    }

    return data
}
