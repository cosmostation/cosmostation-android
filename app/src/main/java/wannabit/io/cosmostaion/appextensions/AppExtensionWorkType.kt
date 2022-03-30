package wannabit.io.cosmostaion.appextensions

sealed class AppExtensionWorkType(val id: String) {
    object START: AppExtensionWorkType("start")
    object STOP: AppExtensionWorkType("stop")
    object OPEN: AppExtensionWorkType("open")
}
