/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.wsservlet;

import org.apache.camel.CamelContext;
import org.apache.camel.ComponentConfiguration;
import org.apache.camel.EndpointConfiguration;
import org.apache.camel.component.atmosphere.websocket.WebsocketComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class WebsocketComponentConfigurationAndDocumentationTest extends CamelTestSupport {

    @Override
    public boolean isUseRouteBuilder() {
        return false;
    }

    @Test
    public void testComponentConfiguration() throws Exception {
        WebsocketComponent comp = context.getComponent("atmosphere-websocket", WebsocketComponent.class);
        EndpointConfiguration conf = comp.createConfiguration("atmosphere-websocket://localhost:8088/hola?sendToAll=true&useStreaming=false");

        assertEquals("true", conf.getParameter("sendToAll"));
        assertEquals("false", conf.getParameter("useStreaming"));

        ComponentConfiguration compConf = comp.createComponentConfiguration();
        String json = compConf.createParameterJsonSchema();

        assertTrue(json.contains("\"useStreaming\": { \"type\": \"boolean\" }"));
        assertTrue(json.contains("\"sendToAll\": { \"type\": \"boolean\" }"));
    }

    @Test
    public void testComponentDocumentation() throws Exception {
        CamelContext context = new DefaultCamelContext();
        String html = context.getComponentDocumentation("atmosphere-websocket");
        assertNotNull("Should have found some auto-generated HTML if on Java 7", html);
    }

}
