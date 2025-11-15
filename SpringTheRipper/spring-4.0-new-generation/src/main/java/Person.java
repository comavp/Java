public class Person {

    String firstName;
    String lastName;
    Integer age;

    public Person(String name, int age) {
        this.firstName = name;
        this.age = age;
    }

    // до параметра 'имя' с помощью reflection добраться нельзя
    public Person(String имя, String фамилия) {
        this.firstName = имя;
        this.lastName = фамилия;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{firstName=\"" + this.firstName + "\", lastName=\"" + this.lastName + "\"}";
    }
}
