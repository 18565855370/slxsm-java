package com.slxsm.builder.classics_builder;

/**
 * 实体构造者类 lx
 */
public class LxComputerBuilder extends AbstractComputerBuilder {

    private Computer computer;

    public LxComputerBuilder(String cpu, String disk, String memory){
        computer = new Computer(cpu,disk,memory);
    }

    public void setSb() {
        computer.setSb("logitech1");
    }

    public void setLanya() {
        computer.setLanya("lxLy");
    }

    public Computer computer() {
        return computer;
    }
}
