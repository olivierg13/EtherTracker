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
package com.og.finance.ether.unit;

import android.content.Context;

import com.og.finance.ether.utilities.SharedPreferencesUtilities;

import org.junit.Before;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by olivier.goutay on 3/9/16.
 */
public class AbstractUnitTest {

    @Before
    public void setUp() throws Exception {
        //Just deleting the SharedPreferences, to be sure we start fresh
        SharedPreferencesUtilities.INSTANCE.deleteKey(getContext(), SharedPreferencesUtilities.INSTANCE.getSHARED_BUYING_VALUE());
        SharedPreferencesUtilities.INSTANCE.deleteKey(getContext(), SharedPreferencesUtilities.INSTANCE.getSHARED_ENDPOINT_ID());
        SharedPreferencesUtilities.INSTANCE.deleteKey(getContext(), SharedPreferencesUtilities.INSTANCE.getSHARED_SERVICE_ACTIVE());
    }

    /**
     * Returns the {@link Context} from the {@link android.app.Instrumentation}
     */
    protected Context getContext() {
        return getInstrumentation().getTargetContext();
    }

}
