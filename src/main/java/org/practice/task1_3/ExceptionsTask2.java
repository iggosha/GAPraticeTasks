package org.practice.task1_3;

public class ExceptionsTask2 {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            try {
                new MyException();
            } catch (Throwable e) {
                System.out.println("e = " + e);
                if (e.getCause() instanceof MyException) {
                    MyException ex = (MyException) e.getCause();
                    System.out.println("e instanceof " + MyException.class.getName() + ", s = " + ex.get());
                }
            }
        }
    }
}


class MyException extends RuntimeException {

    private String s;

    static {
        init();
    }

    public MyException() {
        s = "Hello";
    }

    private static void init() {
        throw new MyException();
    }

    public String get() {
        return s;
    }
}
