package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {
        Clazz obj = new Clazz();
        System.out.println(obj.getNumber());

        //getName
        try {
            Field field = obj.getClass().getDeclaredField("name");
            //getFields, getDeclareFields
//            System.out.println(field);
            field.setAccessible(true);
//            System.out.println(field);

            String name = (String) field.get(obj);
            System.out.println(name);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Method
//        Method method = null;
        try {
            Method method = obj.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // Create экземпляр class in work time
        try {
            Class clazz = Class.forName(Clazz.class.getName());
            obj = (Clazz) clazz.getConstructor().newInstance();
            System.out.println(Clazz.class.getName());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Class clazz = null;
        try {
            clazz = Class.forName(Clazz.class.getName());
            Class[] params = {int.class, String.class, int.class};
            obj = (Clazz) clazz.getDeclaredConstructor().newInstance(params);
            System.out.println(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            clazz = Class.forName(Clazz.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] params = constructor.getParameterTypes();
            System.out.println(constructor.getName());
            for (Class param : params) {
                System.out.println(param.getName());
            }
        }

    }
}

class Clazz {
    private int number;
    private String name = "default";
    private int value;

    //Закомментировать
    public Clazz(int number, String name, int value) {
        this.number = number;
        this.name = name;
        this.value = value;
    }

    public Clazz() {
    } //Добавить позже

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {

        this.name = name;
    }

    private void printData() {
        System.out.println("number=" + number + ", name=" + name + ", value=" + value);
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
