package controllers;

import models.Student;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import services.MongoConfig;
import views.html.students.*;

import javax.inject.Inject;

import java.util.Set;

public class StudentController extends Controller {

    @Inject
    FormFactory formFactory;

    // for all students
    public Result index(){
        Set<Student> students =  Student.allStudents();
        return ok(index.render(students));
    }


    //Create a Student
    public Result create(){
        Form<Student> studentForm = formFactory.form(Student.class);
        return ok(create.render(studentForm));
    }

    //Save A student
    public Result save(){
        Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
        Student student = studentForm.get();
        student.add();
        return redirect(routes.StudentController.index());
    }

    //edit A student
    public Result edit(Integer id){
        Student student = Student.findById(id);
        if(student == null){
            return notFound("Student Not Found");
        }
        Form<Student> studentForm = formFactory.form(Student.class).fill(student);
        return ok(edit.render(studentForm));
    }

    //Update the edited student record in DB
    public Result update(){
        Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
        Student student = studentForm.get();
        Student oldStudent = Student.findById(student.id);

        if(oldStudent==null) {
            return notFound("Student Not Found");
        }

        oldStudent.name = student.name;
        oldStudent.marks = student.marks;
        student.add();
        return redirect(routes.StudentController.index());
    }

    //Delete a student record
    public Result destroy(Integer id){
        Student student = Student.findById(id);
        if(student==null){
            return notFound("Student Not Found");
        }
        Student.remove(student);
        return redirect(routes.StudentController.index());
    }

    //for student details
    public Result show(Integer id){
        Student student = Student.findById(id);
        if(student==null){
            return notFound("Student Not Found");
        }
        return ok(show.render(student));
    }
}
