package wannabit.io.cosmostaion.data.model.req

data class Price(val denom: String, val coinGeckoId: String, val current_price: Double, val daily_price_change_in_percentage: Double)