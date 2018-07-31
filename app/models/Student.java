package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import services.MongoConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(value = "students", noClassnameStored = true)
public class Student {

    @Id
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

    public static Set<Student> allStudents() {
        Query<Student> query = MongoConfig.datastore()
                .createQuery(Student.class);
        return new HashSet<Student>(query.asList());
    }


    public static Student findById(Integer id) {
        return MongoConfig.datastore()
                .createQuery(Student.class).field("_id").equal(id).asList().get(0);
    }

    public void add() {
        MongoConfig.datastore().save(this);
    }

    public static Boolean remove(Student aStudent) {
        return MongoConfig.datastore().delete(aStudent).isUpdateOfExisting();
    }

}

