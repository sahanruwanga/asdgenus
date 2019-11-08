package org.codespark.asdgenus.dtos;

public class SubjectDTO {

    private int id;
    private int age;
    private String name;
    private String gender;

    public SubjectDTO(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

//    @Override
//    public String toString() {
//        return "SubjectDTO{" +
//                "age='" + age + '\'' +
//                ", name='" + name + '\'' +
//                ", gender='" + gender + '\'' +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
