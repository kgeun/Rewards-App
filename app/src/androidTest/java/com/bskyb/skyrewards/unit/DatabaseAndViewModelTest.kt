package com.bskyb.skyrewards

import androidx.lifecycle.*
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.model.SRWRewardResult
import com.bskyb.skyrewards.data.persistance.SRWAppDatabase
import com.bskyb.skyrewards.data.persistance.SRWMainDao
import com.bskyb.skyrewards.view.viewmodel.SRWMainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var mainDao: SRWMainDao
    private lateinit var mDatabase: SRWAppDatabase
    private lateinit var mainViewModel: SRWMainViewModel

    @Before
    fun init() {
        mDatabase = Room.databaseBuilder(
            context,
            SRWAppDatabase::class.java,
            "skyrewards.db"
        ).build()
        mainDao = mDatabase.SRWMainDao()
        mainViewModel = SRWMainViewModel(mainDao)
    }

    @Test
    fun customerDataTest() {
        mainViewModel.viewModelScope.launch {
            val data = SRWCustomerData("111111111111", 3, 123123123)
            withContext(Dispatchers.IO) {
                mainDao.insertCustomerData(data)

                // Database Test (sync)
                assertEquals(data, mainDao.getCustomerDataSync())
            }

            // ViewModel Test (livedata)
            mainDao.getCustomerData().observeOnce {
                assertEquals(data, it)
            }
        }
    }

    @Test
    fun rewardResultTest() {
        mainViewModel.viewModelScope.launch {
            val data = SRWRewardResult(1, "title", "description", 123, 123123)
            withContext(Dispatchers.IO) {
                mainDao.insertRewardResult(data)

                // Database Test (sync)
                assertEquals(data, mainDao.getRewardResultSync())
            }

            // ViewModel Test (livedata)
            mainDao.getRewardResult().observeOnce {
                assertEquals(data, it)
            }
        }
    }
}

class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)
    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        handler(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}
