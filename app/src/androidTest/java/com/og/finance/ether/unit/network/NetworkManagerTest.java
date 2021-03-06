/**
 * Copyright 2013 Olivier Goutay (olivierg13)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.og.finance.ether.unit.network;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.og.finance.ether.network.NetworkCallback;
import com.og.finance.ether.network.NetworkManager;
import com.og.finance.ether.network.apis.AbstractEtherApi;
import com.og.finance.ether.network.enums.Endpoint;
import com.og.finance.ether.unit.AbstractUnitTest;
import com.og.finance.ether.utilities.SharedPreferencesUtilities;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by olivier.goutay on 3/9/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class NetworkManagerTest extends AbstractUnitTest implements NetworkCallback<AbstractEtherApi> {

    /**
     * The current tested {@link Endpoint}
     */
    private Endpoint mCurrentEndpoint;

    /**
     * The {@link CountDownLatch} to wait for the async network query to finish
     */
    private CountDownLatch mCountDownLatch;

    /**
     * Test {@link com.og.finance.ether.network.NetworkManager#getCurrentEthValue(android.content.Context, NetworkCallback)}
     */
    @Test
    public void testGetCurrentEthValue() throws InterruptedException {
        //Test with Polionex
        mCountDownLatch = new CountDownLatch(1);
        mCurrentEndpoint = Endpoint.POLONIEX;
        SharedPreferencesUtilities.INSTANCE.storeIntForKey(getContext(), SharedPreferencesUtilities.INSTANCE.getSHARED_ENDPOINT_ID(), mCurrentEndpoint.getId());
        NetworkManager.Companion.getCurrentEthValue(getContext(), this);
        mCountDownLatch.await();

        //Test with Kraken
        mCountDownLatch = new CountDownLatch(1);
        mCurrentEndpoint = Endpoint.KRAKEN;
        SharedPreferencesUtilities.INSTANCE.storeIntForKey(getContext(), SharedPreferencesUtilities.INSTANCE.getSHARED_ENDPOINT_ID(), mCurrentEndpoint.getId());
        NetworkManager.Companion.getCurrentEthValue(getContext(), this);
        mCountDownLatch.await();

        //Test with CoinMarketCap
        mCountDownLatch = new CountDownLatch(1);
        mCurrentEndpoint = Endpoint.COIN_MARKET_CAP;
        SharedPreferencesUtilities.INSTANCE.storeIntForKey(getContext(), SharedPreferencesUtilities.INSTANCE.getSHARED_ENDPOINT_ID(), mCurrentEndpoint.getId());
        NetworkManager.Companion.getCurrentEthValue(getContext(), this);
        mCountDownLatch.await();
    }

    @Override
    public void updateApi(AbstractEtherApi api) {
        assertNotNull(api);
        assertNotNull(api.getPriceValue());
        assertNotNull(api.getPriceChange());
        assertTrue(api.getClass() == mCurrentEndpoint.getEtherApiClass());

        if (mCountDownLatch != null) {
            mCountDownLatch.countDown();
        } else {
            fail();
        }
    }

}
