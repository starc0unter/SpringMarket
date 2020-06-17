package com.geekbrains.geekmarketwinter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {

    }


}



// 1
//class Main2 {
//    public static void main(String[] args) {
//        int i = 0;
//        System.out.println(++i == i++);
//        System.out.println(i++ == i++);
//    }
//}
//













// 2
//class Main3 {
//    public static void main(String[] args) {
//        int a = 2;
//        int b = 10;
//        int c = 1 >> a++ + ++b - --a - b << 1;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//    }
//}










// 3

///////// 1
interface I1 {
    void copy() throws IOException;
}

interface I2 {
    void copy() throws FileNotFoundException;
}


class A implements I1, I2 {

    // вопрос: какие исключения нужно использовать и нужно ли?
    @Override
    public void copy() {

    }
}









// 4
class equalsLong {
    public static void main(String[] args) {
        Long a = 111L;
        Long b = 111L;
        Long c = 222L;
        Long d = 222L;

        System.out.println(a == b);
        System.out.println(c == d);
    }
}






// 5

class Tetst5 {
    public static void main(String[] args) {
        int i = 8;
        System.out.println(i++);
        System.out.println(i+1);
        System.out.println(i);
    }
}








// 6

class Tetst6 {
    public static void main(String[] args) {
            double d1 = 11./0;
            double d2 = 15./0;
            System.out.println(d1);
            System.out.println(d2);
            System.out.println(d1 - d2);
    }
}













// 7

//-------------------------------------
//    public static void main(String[] args) {
//        Box box1 = new Box(10, "red");
//        Box box2 = new Box(10, "red");
//        System.out.println(box1 == box2);
//        System.out.println(box1.equals(box2));
//    }
//-------------------------------------
//}



//class Box {
//    int weight;
//    String color;
//
//    public Box(int weight, String color) {
//        this.weight = weight;
//        this.color = color;
//    }
//}
