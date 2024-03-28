package dev.ashish.disclosure.utils

class NetworkHelperTest : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
      return true
    }
}