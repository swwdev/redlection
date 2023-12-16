package org.lab5.interface2_and_impl;

import org.lab5.interface1_and_impl.Infc1;

public class FirstImpl implements Infc2 {
    @Override
    public void f2() {
        System.out.println("first");
    }
}
