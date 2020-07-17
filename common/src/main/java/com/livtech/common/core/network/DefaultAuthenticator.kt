package com.livtech.common.core.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Authenticator is used to refresh the access tokens at single place
 * when the API call fails with 401(Unauthorized)  Error.
 *
 * Implement your own authenticator as per the use cases
 */
class DefaultAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request()
    }
}