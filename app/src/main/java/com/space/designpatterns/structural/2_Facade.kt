package com.space.designpatterns.structural

import java.lang.Thread.sleep
import kotlin.random.Random

/** Very Complex Network Caller we don't care about */
data class NetworkResponse(val code: Int, val data: String)
class NetworkCaller {
    fun getRequest(url: String): NetworkResponse {
        println("GET request from $url ...")
        sleep(1000)
        val data = Random.nextLong().toString()
        val response = parseData(data)
        println("Success! Code: ${response.code}")
        return response
    }

    fun postRequest(url: String, data: String) {
        println("POST request to $url ...")
        sleep(1000)
        println("Success! Code: 200")
    }

    private fun parseData(data: String): NetworkResponse {
        println("Parsing data from server...")
        return NetworkResponse(200, data)
    }
}

/** [NetworkRepository] acts as a facade, because it simplifies the complex class [NetworkCaller]
by calling just the functions we need to get data and save data to server. */
class NetworkRepository {
    private val api = NetworkCaller()
    private val url = "https://www.google.com/"

    fun getDataFromApi(): String {
        val response = api.getRequest(url)
        return response.data
    }

    fun saveDataToApi(data: String) {
        api.postRequest(url, data)
    }
}

/** We do not care what happened behind scenes when we called repository functions.
   We are happy that we got the response and sent the data to the server. */
fun main() {
    val repo = NetworkRepository()

    val data = repo.getDataFromApi()
    println("\n")
    repo.saveDataToApi("Hello World!")
}
