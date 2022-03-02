package Test;

import java.util.List;
import java.util.Random;
// import java.util.concurrent.ThreadLocalRandom;
import Objects.*;
import Utils.AcademicDegree;
import Utils.DateOfBirthGenerator;
import Utils.HireDateGenerator;
import Utils.Nationality;
import Utils.PeselGenerator;
import Utils.StudentBookNumberGenerator;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;



public class GenerateObjects  {
    
    private static int numberOfStudents = 100;
    private static int numberOfGroups = 12;
    private static int numberOfDepartments = 3;
    private static int numberofSubjects = 10;
    private static int numberOfTeachers = 10;
    private static List <Teacher> teachers = new ArrayList<Teacher>();
    private static List <Student> students = new ArrayList<Student>();
    private static List<Department> dep = new ArrayList<Department>();
    private static List <StudentGroup> sg;
    private static List <Subject> subj;
    private static Random random = new Random();
    
    
    public static Student createStudents(int counter) throws Exception {
        Student s = null;
            Date birthDate = DateOfBirthGenerator.generateBirthDate();
            s = new Student(PeselGenerator.generatePesel(birthDate), "SSurname-"+counter, "SFirstName-"+counter, birthDate, Nationality.randomNationality(), StudentBookNumberGenerator.generateStNu());
        return s;
    }

    public static Teacher createTeachers(int counter)throws Exception{
        Teacher t = null;
            Date birthDate = DateOfBirthGenerator.generateBirthDate();
            t = new Teacher(PeselGenerator.generatePesel(birthDate), "TSurname-"+counter, "TFirstName-"+counter, birthDate, Nationality.randomNationality(), AcademicDegree.getRandomDegree(), HireDateGenerator.generateHireDate());
        return t;
    }

    public static Department createDepartments(int counter){
        Department dep =  new Department("dName"+counter, teachers); 
        return dep;
    }

    public static StudentGroup createStudentGroups(int counter)throws Exception{
        int max = 8;
        students = listStudents();
        StudentGroup s = null;
        for (int i = 0; i < max; i++){
            s =  new StudentGroup("StudentGroupName"+counter, students.subList(i*max, i*max+max));
        }
        max = 10;
        for (int i = 8; i < max; i++){
            s =  new StudentGroup("StudentGroupName"+counter, students.subList(i*max, i*max+max));
        }
        return s;    
    }

    public static Subject createSubject(int counter)throws Exception{
        Subject subj = null;
        teachers = listTeachers();
        dep = listDepartment();
        students = listStudents();
        int max = 10; 
        for (int i = 0 ; i < max; i++)
            subj = new Subject("SubjName"+counter,dep.get(random.nextInt(dep.size())), teachers.get(random.nextInt(teachers.size())), students.subList(i*max, i*max+max));   
        
        return subj; 
    }

    public static List <Department> listDepartment(){
        for (int i = 0 ; i < numberOfDepartments; i++){
            dep.add(createDepartments(i));
        }
        return dep;
    }

    
    public static List <Student> listStudents() throws Exception{
        for (int i = 0 ; i < numberOfStudents; i++){
            students.add(createStudents(i));
        }
        return students;
    }

    public static List <Teacher> listTeachers()throws Exception{
        for (int i = 0 ; i < numberOfTeachers; i++){
            teachers.add(createTeachers(i));
        }
        return teachers;
    }

    public static List <StudentGroup> groupList()throws Exception{
        sg = new ArrayList<StudentGroup>();
        for (int i = 0 ; i < numberOfGroups; i++){
            sg.add(createStudentGroups(i));
        }
        return sg;
    }

    public static List <Subject> subjectList()throws Exception{
        subj = new ArrayList<Subject>();
        
        for (int i = 0 ; i < numberofSubjects; i++){
            subj.add(createSubject(i));
        }
        return subj;
    }

    @Test
    public void funTest()throws Exception{
        int counter = 1;
        createDepartments(counter);
        createStudentGroups(counter);
        createStudents(counter);
        createSubject(counter);
        createTeachers(counter);
    }

    @Test
    public  void teachersList()throws Exception{
        teachers = GenerateObjects.listTeachers();
        Assert.assertNotNull(teachers);
        Assert.assertEquals(10, teachers.size());
    }

    @Test
    public  void studentsList()throws Exception{
        students = GenerateObjects.listStudents();
        Assert.assertNotNull(students);
        Assert.assertEquals(100, students.size());
    }

    @Test
    public void departmentList(){
        dep = listDepartment();
        Assert.assertNotNull(dep);
        Assert.assertEquals(3, dep.size());
    }
    @Test
    public void studentGrouplist()throws Exception{
        sg = groupList();
        Assert.assertNotNull(sg);
        Assert.assertEquals(12, sg.size());
    }
    @Test
    public void listOfSubject()throws Exception{
        subj = subjectList();
        Assert.assertNotNull(subj);
        Assert.assertEquals(10, subj.size());
    }
}


