package org.practice.task1_3;

import java.util.ArrayList;
import java.util.List;

//- Представим, что у нас есть ArrayList, в который нельзя добавлять больше чем 10 элементов.
// Нужно разработать свой собственный класс исключения (наследник от Exception),
// которое будет выкидываться при попытке добавления 11го элемента.
//В данном классе постараться переопределить как можно больше методов класса Throwable.
public class ExceptionsTask1 {

    public static void main(String[] args) {
        ExceptionsTask1 classObj = new ExceptionsTask1();
        List<Integer> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < 11; i++) {
                classObj.addElementToList(arrayList);
            }
        } catch (MoreThanTenException mtte) {
            System.out.println(mtte);
            System.out.println(mtte.getMessage());
            mtte.printStackTrace();
        }
    }

    public void addElementToList(List<Integer> list) throws MoreThanTenException {
        if (list.size() >= 10) {
            throw new MoreThanTenException("Невозможно добавить более 10 элементов в массив");
        } else {
            list.add((int) (Math.random()*10));
        }
    }
}


class MoreThanTenException extends Exception {

    public MoreThanTenException() {
        super();
    }

    public MoreThanTenException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        Class<MoreThanTenException> c = MoreThanTenException.class;
        return "Исключение = '" + c.getSimpleName() +"', сообщение = '" + this.getMessage()+"'";
    }
}
