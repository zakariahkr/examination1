import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var persons = readCsvFile("mock_data.csv");// Read file contains 1000 Persons with different ages, job, names and gender
        final long startTime = System.nanoTime();
        List<Person> sortedPersons = new ArrayList<>();
        for (Person person : persons){
            if (person.age >= 30 && person.age <= 35
            && Objects.equals(person.job, "TA")
            && Objects.equals(person.gender, "Female")){
                sortedPersons.add(person);
            }
        }
        int n = sortedPersons.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedPersons.get(j).age > sortedPersons.get(j + 1).age) {
                    Person temp = sortedPersons.get(j);
                    sortedPersons.set(j, sortedPersons.get(j + 1));
                    sortedPersons.set(j + 1, temp);
                }
            }
        }


        for (Person person : sortedPersons){
            System.out.println(person);
        }
        final long duration = System.nanoTime() - startTime;
        long durationInMilliSecond= duration / 1_000_000;
        System.out.println("Running time in milli second is " + durationInMilliSecond);

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