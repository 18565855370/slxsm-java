package com.slxsm.builder;

public class ComputerTest {

    public static void main(String[] args) {
        Computer computer = new Computer.Builder("因特尔","华为","32G")
                .setLanya("sm")
                .setSb("luo ji")
                .build();
        System.out.println(computer.toString());
    }
}
