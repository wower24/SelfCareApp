package com.wower.selfcareapp.feature_journal.domain.use_case
//Use Cases for Journal feature to be injected in view model
data class EntryUseCases(
    val getEntries: GetEntries,
    val deleteEntry: DeleteEntry,
    val addEntry: AddEntry
)