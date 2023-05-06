package org.irushu.demo;

import org.irushu.demo.service.KafkaProducerService;
import org.irushu.demo.service.MysqlService;
import org.irushu.demo.service.RedisCounterService;
import org.irushu.demo.web.controller.DemoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ControllerTests {

    @Mock
    private MysqlService mysqlService;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @Mock
    private RedisCounterService redisCounterService;

    @InjectMocks
    private DemoController controller ;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMysql() throws Exception {
        when(mysqlService.findByInput(anyString())).thenReturn("B" );

        RequestBuilder request = MockMvcRequestBuilders
                .post("/demo/01-mysql")
                .content("{ \"input\" : \"A\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.output").value("B"))
                .andReturn();

    }

}
