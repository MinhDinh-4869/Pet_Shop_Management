package petshop.pets.classes;

public class Cat extends PetsAb{
    public Cat(){
        this.location = "./dat/cat/";
        this.ID = generateCatId();
        this.class_of_pet = "cat";
    }
    private String generateCatId()
    {
        return "C" + generate_ID();
    }
}
