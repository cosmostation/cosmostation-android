package wannabit.io.cosmostaion.network.res.neutron

import java.math.BigDecimal

data class ResVestingData(val allocated_amount: String?, val withdrawn_amount: String?, val schedule: Schedule?) {

    fun getVestingAmount(): BigDecimal? {
        return if (allocated_amount != null && withdrawn_amount != null) {
            BigDecimal(allocated_amount).subtract(BigDecimal(withdrawn_amount))
        } else {
            BigDecimal.ZERO
        }
    }
}

data class Schedule(
    val start_time: Long?, val cliff: Long?, val duration: Long?) {

    fun getVestingDuration(): Long {
        return if (start_time != null && duration != null) {
            (start_time + duration) * 1000
        } else {
            0
        }
    }
}

