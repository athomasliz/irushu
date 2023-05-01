package org.irushu.demo;

import org.irushu.demo.service.KafkaProducerService;
import org.irushu.demo.service.MysqlService;
import org.irushu.demo.service.RedisCounterService;
import org.irushu.demo.web.controller.DemoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DemoController.class,
        useDefaultFilters = false,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class)},
        excludeAutoConfiguration = {SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class}
)
@ComponentScan(basePackages = "org.irushu.demo.web.controller")
public class ControllerTests {

    @MockBean
    private MysqlService mysqlService;
    @MockBean
    private KafkaProducerService kafkaProducerService;
    @MockBean
    private RedisCounterService redisCounterService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMysql() throws Exception {
        Mockito.when(mysqlService.findByInput(anyString())).thenReturn("B" );
        RequestBuilder request = MockMvcRequestBuilders
                .post("/demo/01-mysql")
                .content("{ \"input\" : \"A\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("{\"output\":\"B\"}", result.getResponse().getContentAsString());
    }

}
