package model;

import java.util.Objects;

public class Person {
    private String name;
    private String surname;

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, surname);
    }

    @Override
    public String toString(){
        return "model.Person{" +
                "name='" + name + "\'" +
                ", surname='" + surname + "\'" +
                "}";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }
}
