package edu.whu.demo.aspect;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.service.IProductService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ProfilingAspectTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductService productService;

    @Autowired
    ProfilingAspect profilingAspect;

    @Test
    public void testApi() throws Exception {
        Supplier supplier=new Supplier();
        supplier.setName("A公司");
        supplier.setPhone("1982229992");
        Supplier supplier2=new Supplier();
        supplier2.setName("B公司");
        supplier2.setPhone("1982223332");

        Product product=new Product();
        product.setName("IPhone 18");
        product.setPrice(12000F);
        product.setProductType("SND-dff");
        product.setCategory("手机");
        product.setStockQuantity(10F);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(supplier)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        supplier.setId(getIdFromResponse(mvcResult));

        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(supplier2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        product.setId(getIdFromResponse(mvcResult));

        Supplier supplier3=new Supplier();
        supplier3.setId(0L); //Supplier表中不存在的id
        supplier3.setName("B公司");
        supplier3.setPhone("1982223332");
        String url = "/product/" + product.getId() + "/suppliers";
        mockMvc.perform(MockMvcRequestBuilders.put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(Lists.newArrayList(supplier3))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(Lists.newArrayList(supplier))))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ApiMetric metric1 = profilingAspect.getMetrics()
                .get("void edu.whu.demo.controller.ProductController.updateProductSuppliers(long,List)");
        assertEquals(1,metric1.executionCount);
        assertEquals(1,metric1.exceptionCountMap.size());

        System.out.println("****** Profiling Report ***********");
        profilingAspect.getMetrics().forEach((api,metric)->{
            System.out.println("API:"+api);
            System.out.println(metric);
        });
        System.out.println("************************************");
    }

    /**
     * 从返回结果的Json字符串中获得id
     * @param mvcResult
     * @return
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    private static Long getIdFromResponse(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String responseJson = mvcResult.getResponse().getContentAsString();
        JsonNode responseNode = new ObjectMapper().readTree(responseJson);
        Long id = responseNode.get("id").asLong();
        return id;
    }


}
