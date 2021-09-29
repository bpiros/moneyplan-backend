package com.red

import com.red.auth.JwtService
import com.red.auth.hash
import com.red.plugins.configureRouting
import com.red.plugins.configureSecurity
import com.red.plugins.configureSerialization
import com.red.plugins.configureSessions
import com.red.repository.DatabaseFactory
import com.red.repository.categories.CategoryRepository
import com.red.repository.goals.GoalRepository
import com.red.repository.transactions.TransactionRepository
import com.red.repository.users.UserRepository
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        install(Locations) {}

        configureSessions()

        DatabaseFactory.init()
        val userRepository = UserRepository()
        val transactionRepository = TransactionRepository()
        val categoryRepository = CategoryRepository()
        val goalRepository = GoalRepository()
        val jwtService = JwtService()
        val hashFunction = { s: String -> hash(s) }

        configureSecurity(userRepository, jwtService)
        configureSerialization()
        configureRouting(
            userRepository,
            transactionRepository,
            categoryRepository,
            goalRepository,
            jwtService,
            hashFunction
        )
    }.start(wait = true)
}

const val API_VERSION = "/v1"