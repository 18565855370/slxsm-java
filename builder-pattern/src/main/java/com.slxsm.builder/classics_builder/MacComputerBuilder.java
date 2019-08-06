package com.slxsm.builder.classics_builder;

/**
 * 实体构建者类 mac
 */
public class MacComputerBuilder extends AbstractComputerBuilder {

    private Computer computer;

    public MacComputerBuilder(String cpu, String disk, String memory){
        computer = new Computer(cpu,disk,memory);
    }

    public void setSb() {
        computer.setSb("logitech");
    }


    public void setLanya() {
        computer.setLanya("iphoneLy");
    }

    public Computer computer() {
        return computer;
    }
}
