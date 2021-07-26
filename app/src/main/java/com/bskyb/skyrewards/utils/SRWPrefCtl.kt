package com.bskyb.skyrewards.utils

import android.content.Context
import android.content.SharedPreferences
import com.bskyb.skyrewards.SRWApplication

object SRWPrefCtl {
    private const val PREF_NAME = "SKY_REWARDS_PREF"

    private const val PREF_MY_CHANNEL_ID = "Pref_MyChannelId"

    private val pref: SharedPreferences =
        SRWApplication.instance.applicationContext.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    private fun set(key: String, value: Any?) {
        when (value) {
            is String? -> pref.edit { it.putString(key, value) }
            is Int -> pref.edit { it.putInt(key, value) }
            is Boolean -> pref.edit { it.putBoolean(key, value) }
            is Float -> pref.edit { it.putFloat(key, value) }
            is Long -> pref.edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private inline fun <reified T : Any> get(key: String, defaultValue: T? = null): T {
        return when (T::class) {
            String::class -> pref.getString(key, defaultValue as? String) as T
            Int::class -> pref.getInt(key, defaultValue as? Int ?: -1) as T
            Boolean::class -> pref.getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> pref.getFloat(key, defaultValue as? Float ?: -1f) as T
            Long::class -> pref.getLong(key, defaultValue as? Long ?: -1) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun setMyChannelId(value: Int) {
        set(PREF_MY_CHANNEL_ID, value)
    }

    fun getMyChannelId(): Int? {
        return get(PREF_MY_CHANNEL_ID, 0)
    }

    fun deleteAll() {
        setMyChannelId(0)
    }
}