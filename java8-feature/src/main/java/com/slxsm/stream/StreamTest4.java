package com.slxsm.stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-01
 */
public class StreamTest4 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "hello world");
//        String[] stringArray = stream.toArray(String[]::new);
//        Arrays.asList(stringArray).forEach(System.out::println);

//        List<String> list = stream.collect(Collectors.toList());
//        System.out.println(list);
     /*   List<String> list1 = stream.collect(() -> new ArrayList<String>(),
                (theList, item) -> theList.add(item),
                ((arrayList, arrayList2) -> arrayList.addAll(arrayList2)));
        List<String> list = stream.collect(ArrayList::new,ArrayList::add, ArrayList::addAll);
        list.forEach(System.out::println);
        String concat = stream.collect(StringBuilder::new, StringBuilder::append,
                                                          StringBuilder::append)
                                     .toString();*/
//     List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
//     list.forEach(System.out::println);

        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }
}
