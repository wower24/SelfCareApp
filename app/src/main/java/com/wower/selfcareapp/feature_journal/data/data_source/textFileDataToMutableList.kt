package com.wower.selfcareapp.feature_journal.data.data_source

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun textFileDataToMutableList(
    context: Context,
    fileName: String
): MutableList<String> {
    var data: MutableList<String> = mutableListOf<String>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.forEachLine { prompt ->
            data.add(prompt)
        }
        reader.close()
    } catch(e: Exception) {
        e.printStackTrace()
    }

    return data
}
