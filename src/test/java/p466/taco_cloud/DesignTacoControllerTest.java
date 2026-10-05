package p466.taco_cloud;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import p466.taco_cloud.data.IngredientRepository;
import p466.taco_cloud.web.DesignTacoController;

@WebMvcTest(DesignTacoController.class)
@Import(DesignTacoControllerTest.TestConfig.class)
public class DesignTacoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredientRepository ingredientRepo;

    @TestConfiguration
    static class TestConfig {

        @Bean
        IngredientRepository ingredientRepository() {
            return org.mockito.Mockito.mock(IngredientRepository.class);
        }
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testShowDesignForm() throws Exception {

        when(ingredientRepo.findAll()).thenReturn(Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
        ));

        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attributeExists(
                        "wrap",
                        "protein",
                        "cheese",
                        "veggies",
                        "sauce",
                        "tacoOrder",
                        "taco"))
                .andExpect(content().string(
                        containsString("Design your taco!")));
    }
}