package com.exa.android.vlogassignment.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    data object VlogScreen : Screen("vlog_screen")
    data object WebViewScreen : Screen("webview_screen/{url}") {
        fun createRoute(url: String) = "webview_screen/${URLEncoder.encode(url, StandardCharsets.UTF_8.toString())}"
    }
}
