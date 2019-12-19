package hibernate.dto;

public class AuthorGetDto {

    private int id;
    private String name;

    public AuthorGetDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AuthorGetDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
