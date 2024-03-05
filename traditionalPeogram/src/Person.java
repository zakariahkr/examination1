public class Person {
    final String name;
    final private String idNumber;
    int age;
    String gender;
    public String job;


    Person(String name, String job, int age, String gender,String idNumber){
        this.name = name;
        this.job = job;
        this.age = age;
        this.gender = gender;
        this.idNumber = idNumber;
    }


    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString(){
        return String.format("%-25s %-20s %-10s %d", name, job, gender, age);
    }
}
