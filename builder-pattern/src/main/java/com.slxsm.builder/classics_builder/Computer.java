package com.slxsm.builder.classics_builder;

/**
 * 目标computer类
 */
public class Computer {

    private String cpu;//必填
    private String disk;//必填
    private String memory;//必填
    private String sb;//选填
    private String lanya;//选填

    public Computer(String cpu, String disk, String memory){
        this.cpu = cpu;
        this.disk = disk;
        this.memory = memory;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public void setLanya(String lanya) {
        this.lanya = lanya;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", disk='" + disk + '\'' +
                ", memory='" + memory + '\'' +
                ", sb='" + sb + '\'' +
                ", lanya='" + lanya + '\'' +
                '}';
    }
}
