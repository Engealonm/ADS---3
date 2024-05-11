import java.util.Random;

public class test {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int randValue = random.nextInt(1000);
            MyTestingClass key = new MyTestingClass(randValue);
            Student student = new Student("Student" + i);
            table.put(key, student);
        }

    }
}