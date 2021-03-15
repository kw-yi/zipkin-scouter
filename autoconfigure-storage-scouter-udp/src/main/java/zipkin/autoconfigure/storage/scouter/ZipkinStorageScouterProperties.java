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

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("zipkin.storage.scouter")
public class ZipkinStorageScouterProperties {
    /**
     * Scouter Collector UDP address; defaults to localhost:6100
     */
    private boolean debug;
    private String address;
    private int port;
    private int udpPacketMaxBytes;
    private Map<String, String> tagMap = new HashMap<>();
    private String serviceMapsToObjType;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getUdpPacketMaxBytes() {
        return udpPacketMaxBytes;
    }

    public void setUdpPacketMaxBytes(int udpPacketMaxBytes) {
        this.udpPacketMaxBytes = udpPacketMaxBytes;
    }

    public Map<String, String> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, String> tagMap) {
        this.tagMap = tagMap;
    }

    public String getServiceMapsToObjType() {
        return serviceMapsToObjType;
    }

    public void setServiceMapsToObjType(String serviceMapsToObjType) {
        this.serviceMapsToObjType = serviceMapsToObjType;
    }
}
