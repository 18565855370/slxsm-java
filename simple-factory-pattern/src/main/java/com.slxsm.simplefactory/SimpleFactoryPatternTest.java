package com.slxsm.simplefactory;

public class SimpleFactoryPatternTest {

    public static void main(String[] args) {
        AbstractComputer computer = ComputerFactory.createComputer("mi");
        computer.setOperationSystem();
    }
}
