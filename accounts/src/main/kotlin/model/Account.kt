package accounts.model

import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.atomic.AtomicLong
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Persistent account entity with JPA markup. Accounts are stored in an H2
 * relational database.
 *
 * @author Paul Chapman
 */
@Entity
@Table(name = "T_ACCOUNT")
class Account : Serializable {
    @Id
    var id: Long? = null
    var number: String? = null

    @Column(name = "name")
    var owner: String? = null
    var balance: BigDecimal = BigDecimal.ZERO
        get() = field.setScale(2, RoundingMode.HALF_EVEN)
        private set

    fun withdraw(amount: BigDecimal) {
        balance = balance.subtract(amount)
    }

    fun deposit(amount: BigDecimal) {
        balance = balance.add(amount)
    }

    override fun toString(): String {
        return "$number [$owner]: $$balance"
    }

    companion object {
        private val nextId: AtomicLong = AtomicLong(0L)
        fun getNextId() = nextId.addAndGet(1L)
    }
}