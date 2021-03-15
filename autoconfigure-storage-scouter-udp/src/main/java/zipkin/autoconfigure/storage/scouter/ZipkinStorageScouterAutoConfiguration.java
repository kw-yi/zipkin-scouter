/*
 *  Copyright 2015-2018 the original author or authors.
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package zipkin.autoconfigure.storage.scouter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import zipkin2.storage.StorageComponent;
import zipkin2.storage.scouter.udp.ScouterConfig;
import zipkin2.storage.scouter.udp.ScouterUDPStorage;

import java.util.logging.Logger;

@Configuration
@EnableConfigurationProperties(ZipkinStorageScouterProperties.class)
@ConditionalOnProperty(name = "zipkin.storage.type", havingValue = "scouter")
@ConditionalOnMissingBean(StorageComponent.class)
public class ZipkinStorageScouterAutoConfiguration {
    private static final Logger logger = Logger.getLogger(ZipkinStorageScouterAutoConfiguration.class.getName());

    @Bean
    @ConditionalOnMissingBean
    StorageComponent storage(ZipkinStorageScouterProperties properties, @Value("${zipkin.storage.strict-trace-id:true}") boolean strictTraceId) {
        logger.info("[zipkin-scouter-storage] loading.");
        ScouterUDPStorage.Builder builder = ScouterUDPStorage.newBuilder();
        if (StringUtils.hasText(properties.getAddress())) {
            ScouterConfig config = new ScouterConfig(
                    properties.isDebug(),
                    properties.getAddress(),
                    properties.getPort(),
                    properties.getUdpPacketMaxBytes(),
                    properties.getTagMap(),
                    properties.getServiceMapsToObjType()
            );
            logger.info("[zipkin-scouter-storage] config " + config.toString());
            builder.config(config);
        }
        return builder.build();
    }
}
