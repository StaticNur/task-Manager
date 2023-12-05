package org.example;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Hello world!");
        Victim victim = null;
        try {
            victim = new Victim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Field field = Victim.class.getDeclaredField("field");
        field.setAccessible(true);
        int fieldValue = (int) field.get(victim);
        System.out.println(field.get(victim));

        victim.hello();
    }
}
class Victim{
    private int field = 42;
    {
        System.out.println("no static");
        //throw new Exception();
    }
    static {
        System.out.println("static");

    }
    public Victim() throws Exception {
    }
    public static void hello(){
        System.out.println("hello");
    }
    public void wtf(){
        hello();
    }
}
class A{
    private static int aa;
    private int nnn;
    static {
    }

    public A(int a){
        aa=a;
    }
    public List<Integer> aaa(int aa){
        return Collections.emptyList();
    }
}
class B{
    Integer[] inte = new Integer[]{1,2,3,4,5};
    ArrayList<Integer> a = new ArrayList<>(Arrays.asList(inte));
    public ArrayList<Integer> aaa(int aa, int bb){
        return a;
    }
}
abstract class VVVV{
    public abstract void v();
}