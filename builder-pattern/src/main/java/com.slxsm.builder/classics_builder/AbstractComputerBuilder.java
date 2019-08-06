package com.slxsm.builder.classics_builder;

/**
 * 抽象构建者模式
 */
public abstract class AbstractComputerBuilder {

    public abstract void setSb();
    public abstract void setLanya();

    public abstract Computer computer();
}
