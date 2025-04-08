package com.wower.selfcareapp.feature_journal.data.data_source

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun textFileDataToMutableList(
    context: Context,
    fileName: String
): List<String> {
    var data: List<String> = listOf<String>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.forEachLine { prompt ->
            data.plus(prompt)
        }
        reader.close()
    } catch(e: Exception) {
        e.printStackTrace()
    }

    return data
}
