package wannabit.io.cosmostaion.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(@PrimaryKey val id: Long, val resource: String)