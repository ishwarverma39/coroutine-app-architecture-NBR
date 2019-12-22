package com.livtech.common.ui.listeners

import androidx.recyclerview.widget.RecyclerView

open class SimpleScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop()
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom()
        } else if (dy < 0) {
            onScrolledUp()
        } else if (dy > 0) {
            onScrolledDown()
        }
    }

    open fun onScrolledDown() {
    }

    open fun onScrolledUp() {

    }

    open fun onScrolledToBottom() {

    }

    open fun onScrolledToTop() {

    }
}