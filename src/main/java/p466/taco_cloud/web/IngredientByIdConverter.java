package p466.taco_cloud.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import p466.taco_cloud.IngredientRef;
import p466.taco_cloud.data.IngredientRepository;

@Component
public class IngredientByIdConverter
        implements Converter<String, IngredientRef> {

    private final IngredientRepository ingredientRepo;

    public IngredientByIdConverter(
            IngredientRepository ingredientRepo) {

        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientRef convert(String id) {
        return ingredientRepo.findById(id)
                .map(ingredient -> new IngredientRef(ingredient.getId()))
                .orElse(null);
    }
}