public class Pet {
    private int id;
    private String name;
    private String type;
    private int age;
    private String ownerName;

    public Pet(String name, String type, int age, String ownerName) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.ownerName = ownerName;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}
