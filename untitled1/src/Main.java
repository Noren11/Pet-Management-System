import java.util.List;
public class Main {
    public static void main(String[] args) {

        PetGUI petGUI = new PetGUI();


        System.out.println("Fetching all pets from the database...");
        List<Pet> pets = PetManager.getAllPets();


        for (Pet pet : pets) {
            System.out.println("ID: " + pet.getId());
            System.out.println("Name: " + pet.getName());
            System.out.println("Type: " + pet.getType());
            System.out.println("Age: " + pet.getAge());
            System.out.println("Owner: " + pet.getOwnerName());
            System.out.println("-------------------------------");
        }


        petGUI.setVisible(true);
    }
}
