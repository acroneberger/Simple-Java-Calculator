package simplejavacalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import simplejavacalculator.Calculator.BiOperatorModes;
import simplejavacalculator.Calculator.MonoOperatorModes;

import static java.lang.Double.NaN;

import simplejavacalculator.Calculator;

class CalculatorTest {
	Calculator calc = new Calculator();
	
	@Test
	//Tests BiOperator Normal Mode.
	//Exercises directly through BiImpl and indirectly through calculateBi.
	void testCalculateBiImplNormal() {
		assertNotNull(calc);
		calc.num1 = 5.5;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.normal;
		assertSame(calc.calculateBiImpl(), calc.num2);
		calc.num1 = -5.5;
		calc.num2 = -2.0;
		assertSame(calc.calculateBiImpl(), calc.num2);
		assertEquals(calc.calculateBi(BiOperatorModes.normal,5.2),NaN);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.num1, 5.2);
	}
	
	@Test
	//Exercises BiOperator addition Mode.
	void testCalculateBiImplAdd() {
		assertNotNull(calc.calculateBi(BiOperatorModes.add, 2.2));
		assertEquals(calc.calculateBi(BiOperatorModes.add, 2.2),4.4);
		assertEquals(calc.calculateBi(BiOperatorModes.add, 0.0),4.4);
		assertNotEquals(calc.calculateBi(BiOperatorModes.add, 2.2),0.0);
	}
	
	@Test
	//Directly exercises function rather than go through CalculateBi function as program intends.
	//First condition.
	//Tests Addition Arithmetic.
	void testCalculateBiImplAddDirect() {
		calc.num1 = 2.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.add;
		assertEquals(calc.calculateBiImpl(),calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 + calc.num1);
		calc.num1 = -2.0;
		calc.num2 = -2.0;
		assertEquals(calc.calculateBiImpl(),calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 + calc.num1);
	}
	
	@Test
	//Directly exercises function rather than go through CalculateBi function as program intends.
	//Default Condition.
	void testCalculateBiImplAddDirectDefault() {
		calc.num1 = 2.0;
		calc.num2 = 0.0;
		calc.mode = BiOperatorModes.add;
		assertSame(calc.calculateBiImpl(), calc.num1);
		calc.num1 = -2.0;
		calc.num2 = 0.0;
		assertSame(calc.calculateBiImpl(), calc.num1);
	}
	
	@Test
	//Exercises BiOperator subtraction Mode.
	void testCalculateBiImplMinus() {
		assertNotNull(calc.calculateBi(BiOperatorModes.minus, 2.2));
		assertEquals(calc.calculateBi(BiOperatorModes.minus, 2.2),0.0);
		assertNotEquals(calc.calculateBi(BiOperatorModes.minus, 2.2),4.4);
	}
	
	@Test
	//Directly exercises function rather than go through CalculateBi function as program intends.
	//Tests Subtraction Arithmetic.
	void testCalculateBiImplMinusDirect() {
		calc.num1 = 4.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.minus;
		assertEquals(calc.calculateBiImpl(),calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2 - calc.num1);
		calc.num1 = -4.0;
		calc.num2 = -2.0;
		assertEquals(calc.calculateBiImpl(),calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2 - calc.num1);
	}
	
	@Test
	//Exercises BiOperator multiplication Mode.
	void testCalculateBiImplMultiply() {
		assertNotNull(calc.calculateBi(BiOperatorModes.multiply, 2.0));
		assertEquals(calc.calculateBi(BiOperatorModes.multiply, 2.0),4.0);
		assertNotEquals(calc.calculateBi(BiOperatorModes.multiply, 2.0),0.0);
	}
	
	@Test
	//Directly exercises function rather than go through CalculateBi function as program intends.
	//Tests Multiplication Arithmetic.
	void testCalculateBiImplMultiplyDirect() {
		calc.num1 = 2.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.multiply;
		assertEquals(calc.calculateBiImpl(),calc.num1 * calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 * calc.num1);
		calc.num1 = -2.0;
		calc.num2 = -2.0;
		assertEquals(calc.calculateBiImpl(),calc.num1 * calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 * calc.num1);
	}
	
	@Test
	//Exercises BiOperator division Mode.
	void testCalculateBiImpldivide() {
		assertNotNull(calc.calculateBi(BiOperatorModes.divide, 2.0));
		assertEquals(calc.calculateBi(BiOperatorModes.divide, 4.0),0.5);
		assertNotEquals(calc.calculateBi(BiOperatorModes.divide, 2.0),0.0);
	}
	
	@Test
	//Directly exercises function rather than go through CalculateBi function as program intends.
	//Tests Division Arithmetic.
	void testCalculateBiImpldivideDirect() {
		calc.num1 = 4.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.divide;
		assertEquals(calc.calculateBiImpl(),calc.num1 / calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2/calc.num1);
		calc.num1 = -4.0;
		calc.num2 = -2.0;
		assertEquals(calc.calculateBiImpl(),calc.num1 / calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2/calc.num1);
	}
	
	@Test
	//Exercises BiOperator exponent Mode.
	void testCalculateBiImplXpowerofy() {
		assertNotNull(calc.calculateBi(BiOperatorModes.xpowerofy, 2.0));
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy, 3.0),8.0);
		assertNotEquals(calc.calculateBi(BiOperatorModes.xpowerofy, 2.0),0.0);
	}
	
	@Test
	//Directly exercises function rather than go through CalculateBi function as program intends.
	//Tests Exponent Arithmetic.
	void testCalculateBiImplXpowerofyDirect() {
		calc.num1 = 2.0;
		calc.num2 = 3.0;
		calc.mode = BiOperatorModes.xpowerofy;
		assertEquals(calc.calculateBiImpl(),pow(calc.num1,calc.num2));
		assertNotEquals(calc.calculateBiImpl(),pow(calc.num2,calc.num1));
		calc.num1 = -2.0;
		calc.num2 = -3.0;
		assertEquals(calc.calculateBiImpl(),pow(calc.num1,calc.num2));
		assertNotEquals(calc.calculateBiImpl(),pow(calc.num2,calc.num1));
	}
	
	@Test
	//Forces calculateBiImp to default (general error thrown).
	void testCalculateBiImpDefault() {
		calc.mode = null;
		Error thrown = assertThrows(Error.class, () -> calc.calculateBiImpl(), "Should Throw");
		assertEquals(thrown.getMessage(),null);
	}
	
	@Test
	//test squaring 0, 2, -1
	void testCalculateMonoSquare() {
		assertEquals(4.0, calc.calculateMono(MonoOperatorModes.square, 2.0));
		assertEquals(1.0, calc.calculateMono(MonoOperatorModes.square, -1.0));
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.square, 0.0));
		
	}
	
	@Test
	//test square root with 9, 0, and -1 
	void testCalculateMonoSquareRoot() {
	assertEquals(3.0, calc.calculateMono(MonoOperatorModes.squareRoot, 9.0));
	assertEquals(0.0, calc.calculateMono(MonoOperatorModes.squareRoot, 0.0));
	assertEquals(NaN, calc.calculateMono(MonoOperatorModes.squareRoot,  -1.0));
	
	}
	
	@Test
	//test 1/x function with 5, 0, and a slight delta around 0
	void testCalculateMonoOneDevidedBy() {
		assertEquals(0.2, calc.calculateMono(MonoOperatorModes.oneDevidedBy, 5.0));
		assertEquals(Double.POSITIVE_INFINITY, calc.calculateMono(MonoOperatorModes.oneDevidedBy, 0.0));
		assertNotEquals(Double.POSITIVE_INFINITY, calc.calculateMono(MonoOperatorModes.oneDevidedBy, -.001));
		assertNotEquals(Double.POSITIVE_INFINITY, calc.calculateMono(MonoOperatorModes.oneDevidedBy, .001));
		
	}
	
	@Test
	//test cosine function with 0, pi/3 and pi/2 radians
	void testCalculateMonoCos() {
		assertEquals(1.0, calc.calculateMono(MonoOperatorModes.cos, 0.0));
		assertEquals(.500, calc.calculateMono(MonoOperatorModes.cos, (Math.PI / 3)), .001);
		assertEquals(0.000, calc.calculateMono(MonoOperatorModes.cos, (Math.PI / 2)), .001);
	}
	
	@Test
	//test sine function with 0, pi/3 and pi/2 radians
	void testCalculateMonoSin() {
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.sin, 0.0));
		assertEquals(.866, calc.calculateMono(MonoOperatorModes.sin, (Math.PI / 3)), .001);
		assertEquals(1.000, calc.calculateMono(MonoOperatorModes.sin, (Math.PI / 2)), .001);
	}
	
	@Test
	//Test tangent function
	//Note that the implementation of tangent is incorrect in the
	//underlying code, as tangent special cases are
	//specified in degrees while underlying math function expects radians.
	//This test thus exposes an error in the underlying code and will currently fail if asserted equal.
	//Furthermore, complete coverage is not possible due to the lack of conditional default.
	void testCalculateMonoTan() {
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.tan, 0.0));
		assertEquals(0.0,calc.calculateMono(MonoOperatorModes.tan, 180.0));
		assertEquals(1.732, calc.calculateMono(MonoOperatorModes.tan, (Math.PI / 3)), .001);
		assertEquals(NaN,calc.calculateMono(MonoOperatorModes.tan, 90.0));
		assertNotEquals(NaN, calc.calculateMono(MonoOperatorModes.tan, 180.0));
		
		//According to code, this should be equal.
		//Asserted Not Equal to highlight that if this passes, there is an error in the code.
		assertNotEquals(NaN, calc.calculateMono(MonoOperatorModes.tan, (Math.PI / 2)));
		
	}
	
	@Test
	//Test log function with 0,1, and a slight delta around 0 for boundaries
	void testCalculateMonoLog() {
		assertEquals(Double.NEGATIVE_INFINITY, calc.calculateMono(MonoOperatorModes.log, 0.0));
		assertNotEquals(Double.NEGATIVE_INFINITY, calc.calculateMono(MonoOperatorModes.log, .001));
		assertNotEquals(Double.NEGATIVE_INFINITY, calc.calculateMono(MonoOperatorModes.log, -.001));
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.log, 1.0));
		
	}
	
	@Test
	//Test rate calculator with 1 
	void testCalculateMonoRate() {
		assertEquals(.01, calc.calculateMono(MonoOperatorModes.rate, 1.0));
	}
	
	@Test
	//test calculating absolute value with 1, 0, and -1
	void testCalculateMonoAbs() {
		assertEquals(1.0, calc.calculateMono(MonoOperatorModes.abs, 1.0));
		assertEquals(1.0, calc.calculateMono(MonoOperatorModes.abs, -1.0));
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.abs, 0.0));
	}
	
	@Test
	void testCalculateMonoDefault() {
		Error thrown = assertThrows(Error.class, () -> calc.calculateMono(null,0.0), "Should Throw");
		assertEquals(thrown.getMessage(),null);
	}
	
	@Test
	void calculateBiTest_normal() {
		assertEquals(NaN, calc.calculateBi(BiOperatorModes.normal, 1.0));
	}

	@Test
	// 2.0 + 1.0 = 2.0
	void calculateBiTest_add() {
		calc.num1 = 2.0;
		calc.mode = BiOperatorModes.add;
		assertEquals(3.0, calc.calculateBi(calc.mode, 1.0));
	}

	@Test
	// 2.0 - 1.0 = 1.0
	void calculateBiTest_minus() {
		calc.num1 = 2.0;
		calc.mode = BiOperatorModes.minus;
		assertEquals(1.0, calc.calculateBi(calc.mode, 1.0));
	}

	@Test
	// 2.0 * 1.0 = 2
	void calculateBiTest_multiply() {
		calc.num1 = 2.0;
		calc.mode = BiOperatorModes.multiply;
		assertEquals(2.0, calc.calculateBi(calc.mode, 1.0));
	}

	@Test
	// 2.0 / 1.0 = 2
	void calculateBiTest_divide() {
		calc.num1 = 2.0;
		calc.mode = BiOperatorModes.divide;
		assertEquals(2.0, calc.calculateBi(calc.mode, 1.0));
	}

	@Test
	// 2.0 ^ 1.0 = 2.0
	void calculateBiTest_xpowerofy() {
		calc.num1 = 2.0;
		calc.mode = BiOperatorModes.xpowerofy;
		assertEquals(2.0, calc.calculateBi(calc.mode, 1.0));
	}
	
	@Test
	//Reset
	//All elements should be 0/normal mode and return NaN
	void calculateReset() {
		assertNotNull(calc);
		calc.num1 = 1.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.add;
		BiOperatorModes testMode = calc.mode;
		double x = calc.num1;
		double y = calc.num2;
		calc.reset();
		assertNotNull(calc);
		assertNotEquals(calc.num1,x);
		assertNotEquals(calc.num2,y);
		assertNotEquals(calc.mode, testMode);
		assertEquals(calc.num1,0.0);
		assertEquals(calc.num2,0.0);
		assertEquals(calc.mode, BiOperatorModes.normal);
		assertEquals(calc.reset(),NaN);
	}
	
	@Test
	//Acts as an "equal button" when given the second number of the expression.
	//Enter a number and an operator into a calculator
	//This function acts as the following:
	//Enter second number and press "equals"
	void calculateEqual() {
		assertNotNull(calc);
		double num = 2.0;
		//Test entering a number and pressing equals
		assertEquals(calc.calculateEqual(num),NaN);
		
		//Test addition
		calc.mode = BiOperatorModes.add;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num),4.0);
		
		//Test Subtraction
		calc.mode = BiOperatorModes.minus;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num),0.0);
		
		//Test Multiplication
		calc.mode = BiOperatorModes.multiply;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num),4.0);
		
		//Test Division
		calc.mode = BiOperatorModes.divide;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num),1.0);
		
		//Test Exponent
		calc.mode = BiOperatorModes.xpowerofy;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num),4.0);
	}

}