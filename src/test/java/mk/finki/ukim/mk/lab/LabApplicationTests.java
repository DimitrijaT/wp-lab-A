//package mk.finki.ukim.mk.lab;
//
//import mk.finki.ukim.mk.lab.model.Balloon;
//import mk.finki.ukim.mk.lab.model.Manufacturer;
//import mk.finki.ukim.mk.lab.service.AuthService;
//import mk.finki.ukim.mk.lab.service.BalloonService;
//import mk.finki.ukim.mk.lab.service.ManufacturerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDate;
//
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//class LabApplicationTests {
//
//    MockMvc mockMvc;
//    @Autowired
//    AuthService authService;
//    @Autowired
//    BalloonService balloonService;
//    @Autowired
//    ManufacturerService manufacturerService;
//
//    private static Balloon b1;
//    private static Manufacturer m1;
//    private static boolean dataInitialized = false;
//
//    // BeforeEach значи дека ќе се повика секогаш пред @Test метода посебно
//    @BeforeEach
//    public void setup(WebApplicationContext wac) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        initData();
//    }
//
//    // Се користи иницијализација на нашата база на податоци
//    private void initData() {
//        if (!dataInitialized) {
//
//            m1 = manufacturerService.save("m1", "m1", "m1").get();
//            manufacturerService.save("m2", "m2", "m2");
//
//            b1 = balloonService.save("m1", "m1", m1.getId()).get();
//            balloonService.save("m2", "m2", m1.getId());
//
//            String admin = "admin";
//
//            authService.register(admin, admin, admin, admin, admin, LocalDate.now());
//
//            dataInitialized = true;
//
//        }
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testGetProducts() throws Exception {
//        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/balloons");
//        this.mockMvc.perform(productRequest)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("balloonList"))
//                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listBalloons"))
//                .andExpect(MockMvcResultMatchers.view().name("master-template"));
//    }
//
//
////    // За да го избршиме овој тест треба да имаме продукт во базата на податоци
////    @Test
////    public void testDeleteProduct() throws Exception {
////
////        Balloon product = this.balloonService.save("test", "test",  m1.getId()).get();
////        MockHttpServletRequestBuilder balloonDeleteRequest = MockMvcRequestBuilders
////                .delete("/balloons/delete/" + product.getId());
////
////        this.mockMvc.perform(balloonDeleteRequest)
////                .andDo(MockMvcResultHandlers.print())
////                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
////                .andExpect(MockMvcResultMatchers.redirectedUrl("/balloons"));
////
////    }
//
//}
//
//
