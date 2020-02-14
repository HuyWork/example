/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example.springdata.geode.client.function.server;

import example.springdata.geode.client.function.Order;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.data.gemfire.function.annotation.RegionData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class OrderFunctions {

	@GemfireFunction(id = "sumPricesForAllProductsForOrderFnc", HA = true, optimizeForWrite = false, hasResult = true)
	public BigDecimal sumPricesForAllProductsForOrderFnc(Long orderId, @RegionData Map<Long, Order> orderData) {
		return orderData.get(orderId).calcTotal();
	}
}
