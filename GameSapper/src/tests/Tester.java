package tests;

import tests.ActionFieldTest;
import tests.CellTest;
import tests.FieldTest;

/**
 * Created by Sergey on 07.07.2017.
 */

public class Tester {
    public static void main(String[] args) {
        new CellTest().run();
        new FieldTest().run();
        new ActionFieldTest().run();
    }
}
