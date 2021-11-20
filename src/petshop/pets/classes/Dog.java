package petshop.pets.classes;
public class Dog extends PetsAb {
    public Dog(){
        this.location = "./dat/dog/";
        this.ID = generateDogId();
        this.class_of_pet = "dog";
    }
    private String generateDogId()
    {
        return "D" + generate_ID();
    }
}
