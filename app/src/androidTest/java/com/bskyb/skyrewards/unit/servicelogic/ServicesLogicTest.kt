package com.bskyb.skyrewards.unit.servicelogic

import com.bskyb.skyrewards.service.client.*
import com.bskyb.skyrewards.service.util.*
import org.junit.Test
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals


@RunWith(AndroidJUnit4::class)
class ServicesLogicTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun checkSportChannelValidCase() {
        // Sports Channel, Valid Account
        assertEquals(
            SRWEngineUtil
                .makeResultEligibleCustomer(CustomerCases.Customer_who_subscribed_sports_channel_and_have_valid_account_number)
                .also {
                    it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_sports_channel_and_have_valid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkSportChannelInValidCase() {
        // Sports Channel, Invalid Account
        assertEquals(
            SRWNegativeResultCase
                .getInvalidAccountNumberCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_sports_channel_and_have_invalid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkKidsChannelValidCase() {
        // Kids Channel, Valid Account
        assertEquals(
            SRWNegativeResultCase
                .getCustomerInEligibleCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_kids_channel_and_have_valid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkKidsChannelInvalidCase() {
        // Kids Channel, Invalid Account
        assertEquals(
            SRWNegativeResultCase
                .getCustomerInEligibleCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_kids_channel_and_have_invalid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkMusicChannelValidCase() {
        // Music channel, Valid Account
        assertEquals(
            SRWEngineUtil
                .makeResultEligibleCustomer(CustomerCases.Customer_who_subscribed_music_channel_and_have_valid_account_number)
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_music_channel_and_have_valid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkMusicChannelInvalidCase() {
        // Music channel, Invalid Account
        assertEquals(
            SRWNegativeResultCase
                .getInvalidAccountNumberCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_music_channel_and_have_invalid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkNewsChannelValidCase() {
        // News channel, Valid Account
        assertEquals(
            SRWNegativeResultCase
                .getCustomerInEligibleCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_news_channel_and_have_valid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkNewsChannelInvalidCase() {
        // News channel, Invalid Account
        assertEquals(
            SRWNegativeResultCase
                .getCustomerInEligibleCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_news_channel_and_have_invalid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkMovieChannelValidCase() {
        // Movie channel, Valid Account
        assertEquals(
            SRWEngineUtil
                .makeResultEligibleCustomer(CustomerCases.Customer_who_subscribed_movie_channel_and_have_valid_account_number)
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_movie_channel_and_have_valid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }

    @Test
    fun checkMovieChannelInvalidCase() {
        // Movie channel, Invalid Account
        assertEquals(
            SRWNegativeResultCase
                .getInvalidAccountNumberCase()
                .also {
                      it.timestamp = 0
                },
            SRWSkyClientEngine(CustomerCases.Customer_who_subscribed_movie_channel_and_have_invalid_account_number, context)
                .getRewardResult()
                .also {
                    it!!.timestamp = 0
                }
        )
    }
}