/**
 * Copyright 2010-2016 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.ralscha.extdirectspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ch.ralscha.extdirectspring.util.ApiCache;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/testApplicationContext2.xml")
public class ApiControllerWithXMLConfig2Test {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ApiCache apiCache;

	private MockMvc mockMvc;

	@Before
	public void setupApiController() throws Exception {
		apiCache.clear();
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGroup2() throws Exception {
		Configuration config = new Configuration();
		config.setJsContentType("application/x-javascript");
		config.setTimeout(15111);
		config.setEnableBuffer(10);
		config.setMaxRetries(6);
		config.setStreamResponse(false);

		ApiRequestParams params = ApiRequestParams.builder()
				.remotingApiVar("TEST_REMOTING_API").group("group2").configuration(config)
				.build();
		ApiControllerTest.runTest(mockMvc, params, ApiControllerTest.group2Apis(null));

		ApiControllerTest.runTest(mockMvc, params, ApiControllerTest.group2Apis(null));
	}

}
