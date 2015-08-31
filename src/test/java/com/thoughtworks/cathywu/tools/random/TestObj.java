package com.thoughtworks.cathywu.tools.random;

/**
 * @author lzwu
 * @since 9/1/15
 */
public class TestObj implements Cloneable {
    private String name;
    private int age;

    public TestObj(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public TestObj clone() throws CloneNotSupportedException {
        return new TestObj(this.name, this.age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + this.name + ",age=" + this.age;
    }
}
