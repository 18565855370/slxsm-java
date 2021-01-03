package com.slxsm.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-06
 */
public class StreamTest8 {

    public static void main(String[] args) {
//        IntStream.iterate(0,i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);
//        IntStream.iterate(0,i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
        System.out.println("100".hashCode()  % 100);
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("zhansan" + i);
            student.setAge(18 + i);
            if (i % 2 == 0){
                student.setAddress("shanghai");
            }else {
                student.setAddress("beijing");
            }
            studentList.add(student);
        }
        studentList.stream().collect(Collectors.toCollection()).
                filter(item -> item.getAge() > 20)
                .filter(item -> "beijing".equals(item.getAddress()))
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .forEach(item -> System.out.println(item.name));

    }

    public static class Student{
        String name;
        Integer age;
        String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
