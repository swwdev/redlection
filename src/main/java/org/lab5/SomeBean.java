package org.lab5;

import org.lab5.interface1_and_impl.Infc1;
import org.lab5.interface2_and_impl.Infc2;

import java.io.IOException;

public class SomeBean {
    @AutoInjectable
    private Infc1 field1;
    @AutoInjectable
    private Infc2 field2;

    public void foo (){
        try {
            Injector.inject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        field1.f();
        field2.f2();
    }
}
