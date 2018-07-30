package models;

import java.util.HashSet;
import java.util.Set;



public class Student {


    public Integer id;
    public String name;
    public Integer marks;

    public Student() {
    }

    public Student(Integer id, String name, Integer marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    private static Set<Student> students;

    static{
        students = new HashSet<>();
        students.add(new Student(1, "Shivam", 70 ));
        students.add(new Student(2, "Rahul", 80 ));
    }

    public static Set<Student> allStudents(){
        return students;
    }


    public static Student findById(Integer id){
        return students.stream().filter(aStudent -> aStudent.id.equals(id)).findFirst().get();
    }

    public static void add(Student aStudent){
        students.add(aStudent);
    }

    public static Boolean remove(Student aStudent){
        return students.remove(aStudent);
    }
}

