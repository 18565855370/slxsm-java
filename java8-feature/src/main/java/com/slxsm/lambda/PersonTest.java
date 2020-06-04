package com.slxsm.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-01
 */
public class PersonTest {

    public static void main(String[] args) {
        Person person1 = new Person("zhangsan",10);
        Person person2 = new Person("lisi",20);
        Person person3 = new Person("wangwu",40);

        List<Person> persons = Arrays.asList(person1,person2,person3);
        PersonTest test = new PersonTest();
//        List<Person> personResult = test.getPersonsByUsername("zhangsan",persons);
//        personResult.forEach(person -> System.out.println(person.getName()));

//        List<Person> personList = test.getPersonByAge(12,persons);
//        personList.forEach(value -> System.out.println(value.getAge()));

        List<Person> personResult = test.getPersonByAge2(12,persons, (age,personList) -> personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList()));
        personResult.forEach( value -> System.out.println(value.getAge()));
    }

    private List<Person> getPersonsByUsername(String userName, List<Person> personList){
        return personList.stream().filter(person -> person.getName().equals(userName))
                .collect(Collectors.toList());
    }

    public List<Person> getPersonByAge(int age, List<Person> personList){
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageByPerson, persons) -> persons.stream().filter(person -> person.getAge() > ageByPerson)
              .collect(Collectors.toList());
        return biFunction.apply(age,personList);
    }

    public List<Person> getPersonByAge2(int age, List<Person> personList, BiFunction<Integer, List<Person>, List<Person>> biFunction){
        return biFunction.apply(age,personList);
    }
}
