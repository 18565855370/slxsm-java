package com.slxsm.jdk8.methodference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-20
 */
class MethodReferenceTest {

    public String getString(Supplier<String> supplier){
        return supplier.get() + " test";
    }

    public String getString2(String str, Function<String, String> function){
        return function.apply(str);
    }

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan",10);
        Student student2 = new Student("lisi",90);
        Student student3 = new Student("wangwu",50);
        Student student4 = new Student("zhaoliu",40);
        List<Student> studentList = Arrays.asList(student1,student2,student3,student4);
        //静态方法引用：
        /*studentList.sort((studentParam1,studentParam2) ->
                Student.compareStudentByScore(studentParam1,studentParam2));
        studentList.forEach(student -> System.out.println(student.getScore()));
        System.out.println("===========================");
        studentList.sort(Student::compareStudentByScore);
        studentList.forEach(student -> System.out.println(student.getScore()));
        studentList.sort(Student::compareStudentByName);
        studentList.forEach(student -> System.out.println(student.getName()));*/
        //实例方法引用：
        //1
//        StudentComparator studentComparator = new StudentComparator();
//        studentList.sort((studentParam1,studentParam2) -> studentComparator.compareStudentByScore(studentParam1,studentParam2));
//        studentList.forEach(student -> System.out.println(student.getScore()));
        //2
//        studentList.sort(studentComparator::compareStudentByScore);
//        studentList.forEach(student -> System.out.println(student.getScore()));
        //正确的实例方法引用 -> 调用compareByScore的是lambda的第一个参数，后续的所有参数都作为方法的参数传递到方法中。
//        studentList.sort(Student::compareByScore);
//        studentList.forEach(student -> System.out.println(student.getScore()));

        List<String> cities = Arrays.asList("qingdao","chongqing","tianjin","beijing");
//        Collections.sort(cities, (c1,c2) -> c1.compareToIgnoreCase(c2));
//        cities.forEach(city -> System.out.println(city));
        Collections.sort(cities,String::compareToIgnoreCase);
//        cities.forEach(System.out::println);

        //构造方法
        MethodReferenceTest methodReferenceTest = new MethodReferenceTest();
        System.out.println(methodReferenceTest.getString(String::new));

        System.out.println(methodReferenceTest.getString2("hello",String::toUpperCase));
    }
}
