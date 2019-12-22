package com.livtech.common.ui.listeners

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.*

open class SimpleSearchTextChangeListener(
    private val onSearchTextChange: (String) -> Unit, lifeCycle : Lifecycle,
    private val debouncePeriod: Long = 500) : TextWatcher {

    private var searchJob: Job? = null

    private val coroutineScope = lifeCycle.coroutineScope

    override fun afterTextChanged(s: Editable?) {
        //todo
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //todo
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            s?.let {
                delay(debouncePeriod)
                withContext(Dispatchers.Main) {
                    onSearchTextChange(s.toString())
                }
            }
        }
    }
}