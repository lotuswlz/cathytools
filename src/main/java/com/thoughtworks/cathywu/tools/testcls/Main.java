package com.thoughtworks.cathywu.tools.testcls;

public class Main {

    public static void main(String[] args) {
        Child child = new Child();
        child.setMiddleName("middle");
        child.setFamilyName("Wu");
        child.setName("name");
        Parent parent = child;
        Child copyChild = (Child) parent;
        System.out.println(copyChild.getName() + ", " + copyChild.getMiddleName() + ", " + copyChild.getFamilyName());
    }
}
