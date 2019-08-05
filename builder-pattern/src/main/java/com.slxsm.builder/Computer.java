package com.slxsm.builder;

public class Computer {

    private String cpu;//必填
    private String disk;//必填
    private String memory;//必填
    private String sb;//选填
    private String lanya;//选填

    private Computer(Builder builder){
        this.cpu = builder.cpu;
        this.disk = builder.disk;
        this.memory = builder.memory;
        this.sb = builder.sb;
        this.lanya = builder.lanya;
    }

    static class Builder{
        private String cpu;//必填
        private String disk;//必填
        private String memory;//必填
        private String sb;//选填
        private String lanya;//选填

        public Builder(String cpu, String disk, String memory){
            this.cpu = cpu;
            this.disk = disk;
            this.memory = memory;
        }

        public Builder setSb(String sb){
            this.sb = sb;
            return this;
        }
        public Builder setLanya(String lanya){
            this.lanya = lanya;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }
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
