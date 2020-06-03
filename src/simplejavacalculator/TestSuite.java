package simplejavacalculator;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;




@RunWith(JUnitPlatform.class)

@SelectClasses({
		BufferedImageCustomTest.class,
		CalculatorTest.class
})

public class TestSuite {
}
