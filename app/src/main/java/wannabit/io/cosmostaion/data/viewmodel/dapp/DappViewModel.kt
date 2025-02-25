package wannabit.io.cosmostaion.data.viewmodel.dapp

import android.util.Log
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

    private val _sortedBy = MutableLiveData<Pair<List<JsonObject>, List<JsonObject>>>()
    val sortedBy: LiveData<Pair<List<JsonObject>, List<JsonObject>>> get() = _sortedBy

    fun fetchDappList() {
        if (Prefs.dappFilter == 0) {
            ecosystems?.sortWith(compareByDescending<JsonObject> {
                if (it.has("isPinned")) it["isPinned"].asBoolean else false
            }.thenBy {
                it["name"].asString
            })

        } else {
            ecosystems?.sortWith(compareByDescending<JsonObject> {
                if (it.has("isPinned")) it["isPinned"].asBoolean else false
            }.thenByDescending {
                it["chains"].asJsonArray.size()
            }.thenBy {
                it["name"].asString
            })
        }
        _dappList.postValue(ecosystems)
    }

    fun sortByType(
        type: String,
        chain: String,
        searchTxt: String? = "",
        isPinned: Boolean? = false,
        isPinnedId: Int? = 0
    ) {
//        if (Prefs.dappFilter == 0) {
//            ecosystems?.sortWith(compareBy { it["name"].asString })
//        } else {
//            ecosystems?.sortWith(compareByDescending<JsonObject> { ecosystem ->
//                ecosystem["chains"].asJsonArray.size()
//            }.thenBy { ecosystem -> ecosystem["name"].asString })
//        }

//        if (Prefs.dappFilter == 0) {
//            ecosystems?.sortWith(
//                compareByDescending<JsonObject> {
//                    if (it.has("isPinned")) it["isPinned"].asBoolean else false
//                }.thenBy {
//                    it["name"].asString
//                }
//            )
//
//        } else {
//            ecosystems?.sortWith(
//                compareByDescending<JsonObject> {
//                    if (it.has("isPinned")) it["isPinned"].asBoolean else false
//                }.thenByDescending {
//                    it["chains"].asJsonArray.size()
//                }.thenBy {
//                    it["name"].asString
//                }
//            )
//        }

        val updatedEcosystems = ecosystems?.map { ecosystem ->
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

        val filtered = updatedEcosystems?.filter { ecosystem ->
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

        if (Prefs.dappFilter == 0) {
            updatedEcosystems?.toMutableList()?.sortWith(compareByDescending<JsonObject> {
                if (it.has("isPinned")) it["isPinned"].asBoolean else false
            }.thenBy {
                it["name"].asString
            })

        } else {
            updatedEcosystems?.toMutableList()?.sortWith(compareByDescending<JsonObject> {
                if (it.has("isPinned")) it["isPinned"].asBoolean else false
            }.thenByDescending {
                it["chains"].asJsonArray.size()
            }.thenBy {
                it["name"].asString
            })
        }

        Log.e("Test12345 : ", updatedEcosystems.toString())

        filtered?.toList()?.let { sortList ->
            _sortedBy.postValue(Pair(sortList, updatedEcosystems))
        }
    }
}