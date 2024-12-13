package org.digital101.simplewallet.business.core

import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.common.getData
import org.digital101.simplewallet.common.putData

const val APP_DATASTORE = "org.digital101.simplewallet"

class AppDataStoreManager(val context: Context) : AppDataStore {

    override suspend fun setValue(
        key: String,
        value: String
    ) {
        context.putData(key, value)
    }

    override suspend fun readValue(
        key: String,
    ): String? {
        return context.getData(key)
    }

    override suspend fun clear() {
        DataStoreKeys.allKeys.forEach { key ->
            setValue(key, "")
        }
    }
}