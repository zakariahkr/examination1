import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        var persons = readCsvFile("mock_data.csv"); // Read file contains 1000 Persons with different ages, job, names and gender
        Predicate<Person> ageCondition = (x) -> x.age >= 30 && x.age <= 35; // filtering by age.
        Predicate<Person> jobFilter = (x) -> Objects.equals(x.job, "TA"); // filtering by job.
        Predicate<Person> genderFilter = (x) -> Objects.equals(x.gender, "Female"); // filtering by gender.
        Comparator<Person> orderByAge = Comparator.comparingInt(x -> x.age); // sorting by age.

         persons.stream()
                 .filter(ageCondition.and(jobFilter).and(genderFilter))
                 .sorted(orderByAge)
                 .forEach(System.out::println);
    }



    public static List<Person> readCsvFile(String filePath) {
        List<Person> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read the CSV file line by line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Assuming the CSV columns order is: name, job, idNumber, age, gender
                if (fields.length == 5) {
                    Person person = new Person(
                            fields[0].trim(),
                            fields[1].trim(),
                            Integer.parseInt(fields[3].trim()),
                            fields[4].trim(),
                            fields[2].trim()
                            );

                    people.add(person);
                } else {
                    // Handle invalid or incomplete lines in the CSV file
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately based on your application's requirements
        }

        return people;

    }
}