package com.bskyb.skyrewards.unit

import androidx.lifecycle.*
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bskyb.skyrewards.data.model.network.SRWCustomerData
import com.bskyb.skyrewards.data.model.network.SRWRewardResult
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
class DatabaseAndViewModelTest {
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

        mainViewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainDao.truncateRewardResult()
                mainDao.truncateCustomerData()
            }
        }
    }

    @Test
    fun customerDataTest() {
        mainViewModel.viewModelScope.launch {
            val data = SRWCustomerData("111111111111", 3, 123123123)
            withContext(Dispatchers.IO) {
                mainDao.insertCustomerData(data)
            }

            // DB, ViewModel Test (livedata)
            mainDao.getCustomerData().observeOnce {
                if (it != null) {
                    assertEquals(data, it)
                }
            }
        }
    }

    @Test
    fun rewardResultTest() {
        mainViewModel.viewModelScope.launch {
            val data = SRWRewardResult(1, "title", "description", 123, 123123)
            withContext(Dispatchers.IO) {
                mainDao.insertRewardResult(data)
            }

            // DB, ViewModel Test (livedata)
            mainDao.getRewardResult().observeOnce {
                if (it != null) {
                    assertEquals(data, it)
                }
            }
        }
    }
}

class CustomObserver<T>(private val handler: (T?) -> Unit) : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)
    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        handler(t)
    }
}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T?) -> Unit) {
    val observer = CustomObserver(handler = onChangeHandler)
    observe(observer, observer)
}
