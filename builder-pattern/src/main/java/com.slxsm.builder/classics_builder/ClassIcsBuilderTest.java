package com.slxsm.builder.classics_builder;

/**
 * 过于复杂
 */
public class ClassIcsBuilderTest {

    public static void main(String[] args) {
        ComputerDirector director = new ComputerDirector();
        AbstractComputerBuilder builder = new MacComputerBuilder("I5处理器","固态硬盘","16G");
        director.makeComputer(builder);
        Computer computer = builder.computer();
        System.out.println(computer);

        AbstractComputerBuilder builder1 = new LxComputerBuilder("I7处理器","液体硬盘","32G");
        director.makeComputer(builder1);
        Computer computer1 = builder1.computer();
        System.out.println(computer1);
    }
}
