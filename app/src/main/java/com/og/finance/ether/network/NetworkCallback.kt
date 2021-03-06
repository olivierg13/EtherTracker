/**
 * Copyright 2013 Olivier Goutay (olivierg13)
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.og.finance.ether.network

import com.og.finance.ether.network.apis.Api

/**
 * Created by olivier.goutay on 2/29/16.
 * Provides a way to get back the network response on the main [Thread]
 */
interface NetworkCallback<T : Api> {

    /**
     * Called by [NetworkManager] once a network call is done

     * @param api the retrieved [Api]
     */
    fun updateApi(api: T?)
}
