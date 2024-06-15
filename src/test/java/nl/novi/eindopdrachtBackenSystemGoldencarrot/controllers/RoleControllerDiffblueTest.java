package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.RoleDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RoleController.class})
@ExtendWith(SpringExtension.class)
class RoleControllerDiffblueTest {
    @Autowired
    private RoleController roleController;

    @MockBean
    private RoleService roleService;

    /**
     * Method under test: {@link RoleController#createRole(RoleDto)}
     */
    @Test
    void testCreateRole() throws Exception {
        // Arrange
        when(roleService.createRole(Mockito.<RoleDto>any())).thenReturn(1L);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/roles")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new RoleDto()));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(roleController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"roleName\":null}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/roles/1"));
    }
}
