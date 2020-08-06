/*
 * Copyright 2019 Maksim Zheravin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exchange.core2.rest;

import exchange.core2.core.ExchangeCore;
import exchange.core2.core.common.config.ExchangeConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableConfigurationProperties
@Configuration
@Slf4j
public class RestGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestGatewayApplication.class, args);
    }

    @Bean
    public ExchangeCore exchangeCore(@Autowired CommandEventsRouter eventsRouter) {

        // default exchange configuration
        ExchangeConfiguration conf = ExchangeConfiguration.defaultBuilder().build();

        // build exchange core
        ExchangeCore exchangeCore = ExchangeCore.builder()
                .exchangeConfiguration(conf)
                .build();
        return exchangeCore;

//        return ExchangeCore.builder()
//                .resultsConsumer(eventsRouter)
//                .ringBufferSize(4096)
//                .matchingEnginesNum(1)
//                .riskEnginesNum(1)
//                .msgsInGroupLimit(1024)
//                .threadAffinityMode(THREAD_AFFINITY_ENABLE_PER_LOGICAL_CORE)
//                .waitStrategy(CoreWaitStrategy.SLEEPING)
//                .orderBookFactory(symbolType -> new OrderBookFastImpl(OrderBookFastImpl.DEFAULT_HOT_WIDTH, symbolType))
//                .build();

    }

//    @Bean
//    public Consumer<OrderCommand> resultsConsumer() {
//        return cmd -> {
//            System.out.println(">>>" + cmd);
//        };
//    }

}
