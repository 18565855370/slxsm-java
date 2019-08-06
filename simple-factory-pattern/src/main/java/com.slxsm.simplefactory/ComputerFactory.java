package com.slxsm.simplefactory;

public class ComputerFactory {

    public static AbstractComputer createComputer(String computerType){
        AbstractComputer computer = null;
        switch(computerType){
            case "mac":
                computer = new MacComputer();
                break;
            case "mi":
                computer = new MiComputer();
                break;
            default:
                break;
        }
        return computer;
    }
}
