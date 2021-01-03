package com.slxsm.jdk8.methodference;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-23
 */
public class StudentComparator {

    public int compareStudentByScore(Student student1, Student student2){
        return student1.getScore() - student2.getScore();
    }

    public int compareStudentByName(Student student1, Student student2){
        return student1.getName().compareTo(student2.getName());
    }
}
