package p466.taco_cloud.data;

import org.springframework.data.repository.CrudRepository;

import p466.taco_cloud.Ingredient;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}