open class Account(
    val accountNumber: String,
    val ownerName: String
) {
    private var balance: Double = 0.0

    fun getBalance(): Double {
        return balance
    }

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Deposited $$amount successfully, Balance: $$balance")
        } else {
            println("Invalid amount, Try again")
        }
    }

    open fun withdraw(amount: Double) {
        if (amount > 0 && balance >= amount) {
            balance -= amount
            println("Withdrew $$amount successfully, Balance: $$balance")
        } else {
            println("Invalid amount, Try again")
        }
    }

    fun printInfo() {
        println("Account Number: $accountNumber")
        println("Owner Name: $ownerName")
        println("Balance: $$balance")
    }
}

class SavingsAccount(
    accountNumber: String,
    ownerName: String
) : Account(accountNumber, ownerName) {

    override fun withdraw(amount: Double) {
        if (amount > 500) {
            println("Withdrawal limit exceeded(500$)")
        } else {
            super.withdraw(amount)
        }
    }
}

class VIPAccount(
    accountNumber: String,
    ownerName: String,
    val transactionFee: Double = 2.0
) : Account(accountNumber, ownerName) {

    override fun withdraw(amount: Double) {
        val totalAmount = amount + transactionFee
        if (amount > 0 && getBalance() >= totalAmount) {
            super.withdraw(totalAmount)
            println("Transaction fee($$transactionFee)")
        } else {
            println("Withdrawal failed, Insufficient funds")
        }
    }
}

fun main() {
    val savings = SavingsAccount("S101", "Nikoloz M")
    val vip = VIPAccount("V202", "Ketevan S")
    println("\n")
    savings.deposit(1000.0)
    vip.deposit(1000.0)
    println("\n")
    savings.withdraw(600.0)
    savings.withdraw(400.0)
    println("\n")
    vip.withdraw(100.0)
    println("\nFinal Account Info")
    savings.printInfo()
    println()
    vip.printInfo()
}
