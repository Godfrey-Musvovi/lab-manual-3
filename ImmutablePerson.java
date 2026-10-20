import java.util.ArrayList;
import java.util.List;

public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.name = name.trim();
        this.age = age;
        this.hobbies = List.copyOf(hobbies);   // defensive copy on the way in
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getHobbies() { return hobbies; }   // already unmodifiable

    // "Changing" a person returns a NEW object instead of modifying this one
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(name, newAge, hobbies);
    }

    @Override
    public String toString() {
        return name + " (" + age + ") " + hobbies;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("Chess", "Football"));
        ImmutablePerson p = new ImmutablePerson("Tendai", 20, list);

        list.add("Gaming");   // changing the original list...
        System.out.println(p);   // ...does not affect p

        ImmutablePerson older = p.withAge(21);
        System.out.println(p);
        System.out.println(older);

        try {
            p.getHobbies().add("Hacking");
        } catch (UnsupportedOperationException e) {
            System.out.println("Hobbies are read-only");
        }
    }
}