package usecase.usecases.common

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun <T> suspendedTx(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }