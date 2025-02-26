package wannabit.io.cosmostaion.data.viewmodel.dapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import wannabit.io.cosmostaion.common.BaseData.ecosystems
import wannabit.io.cosmostaion.database.Prefs

class DappViewModel : ViewModel() {

    private val _dappList = MutableLiveData<List<JsonObject>>()
    val dappList: LiveData<List<JsonObject>> get() = _dappList

    private val _pinnedByDetail = MutableLiveData<List<JsonObject>>()
    val pinnedByDetail: LiveData<List<JsonObject>> get() = _pinnedByDetail

    private val _sortedBy = MutableLiveData<List<JsonObject>>()
    val sortedBy: LiveData<List<JsonObject>> get() = _sortedBy

    fun fetchDappList() {
        if (Prefs.dappFilter == 0) {
            ecosystems?.sortWith(compareByDescending<JsonObject> {
                it["isPinned"].asBoolean
            }.thenBy {
                it["name"].asString
            })

        } else {
            ecosystems?.sortWith(compareByDescending<JsonObject> {
                it["isPinned"].asBoolean
            }.thenByDescending {
                it["chains"].asJsonArray.size()
            }.thenBy {
                it["name"].asString
            })
        }
        _dappList.postValue(ecosystems)
    }

    fun pinnedByDetail(
        ecosystems: MutableList<JsonObject>,
        type: String,
        chain: String,
        searchTxt: String?,
        isPinned: Boolean? = false,
        isPinnedId: Int? = 0
    ) {
        val updatedEcosystems = ecosystems.map { ecosystem ->
            if (ecosystem["id"].asInt == isPinnedId) {
                val updatedEcosystem = JsonParser.parseString(ecosystem.toString()).asJsonObject
                updatedEcosystem.addProperty(
                    "isPinned", Prefs.getPinnedDapps().contains(isPinnedId)
                )
                updatedEcosystem
            } else {
                ecosystem
            }
        }

        val filtered = updatedEcosystems.filter { ecosystem ->
            var matches = true

            when (type) {
                "Popular" -> {
                    val popular = if (ecosystem.has("is_default")) {
                        ecosystem["is_default"].asBoolean
                    } else {
                        false
                    }
                    matches = matches && popular
                }

                "All" -> {}

                else -> {
                    matches = matches && (ecosystem["type"].asString == type)
                }
            }

            if (chain != "All Network") {
                matches = matches && ecosystem["chains"].asJsonArray.any { it.asString == chain }
            }

            if (!searchTxt.isNullOrEmpty()) {
                matches =
                    matches && ecosystem["name"].asString.contains(searchTxt, ignoreCase = true)
            }

            if (isPinned == true) {
                matches = matches && Prefs.getPinnedDapps().contains(ecosystem["id"].asInt)
            }

            matches
        }

        filtered.toList().let { sortList ->
            _pinnedByDetail.postValue(sortList)
        }
    }

    fun sortByType(
        ecosystems: MutableList<JsonObject>,
        type: String,
        chain: String,
        searchTxt: String? = "",
        isPinned: Boolean? = false
    ) {
        if (Prefs.dappFilter == 0) {
            ecosystems.sortWith(compareByDescending<JsonObject> {
                it["isPinned"].asBoolean
            }.thenBy {
                it["name"].asString
            })

        } else {
            ecosystems.sortWith(compareByDescending<JsonObject> {
                it["isPinned"].asBoolean
            }.thenByDescending {
                it["chains"].asJsonArray.size()
            }.thenBy {
                it["name"].asString
            })
        }

        val filtered = ecosystems.filter { ecosystem ->
            var matches = true

            when (type) {
                "Popular" -> {
                    val popular = if (ecosystem.has("is_default")) {
                        ecosystem["is_default"].asBoolean
                    } else {
                        false
                    }
                    matches = matches && popular
                }

                "All" -> {}

                else -> {
                    matches = matches && (ecosystem["type"].asString == type)
                }
            }

            if (chain != "All Network") {
                matches = matches && ecosystem["chains"].asJsonArray.any { it.asString == chain }
            }

            if (!searchTxt.isNullOrEmpty()) {
                matches =
                    matches && ecosystem["name"].asString.contains(searchTxt, ignoreCase = true)
            }

            if (isPinned == true) {
                matches = matches && Prefs.getPinnedDapps().contains(ecosystem["id"].asInt)
            }

            matches
        }

        filtered.toList().let { sortList ->
            _sortedBy.postValue(sortList)
        }
    }
}