package simplejavacalculator;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import org.junit.jupiter.api.Test;

import simplejavacalculator.Calculator.BiOperatorModes;
import simplejavacalculator.Calculator.MonoOperatorModes;

import static java.lang.Double.NaN;

import simplejavacalculator.Calculator;

class CalculatorTest {
	Calculator calc = new Calculator();
	
	@Test
	void testCalculateBiImplNormal() {
		assertNotNull(calc);
		calc.num1 = 5.5;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.normal;
		double x = calc.calculateBiImpl();
		assertTrue(x == calc.num2);
		assertSame(calc.calculateBiImpl(), calc.num2);
		calc.num1 = -5.5;
		calc.num2 = -2.0;
		double y = calc.calculateBiImpl();
		assertNotEquals(x,y);
		assertTrue(y == calc.num2);
		assertSame(calc.calculateBiImpl(), calc.num2);
	}
	
	@Test
	void testCalculateBiImplAdd() {
		assertNotNull(calc.calculateBi(BiOperatorModes.add, 2.2));
		assertTrue(calc.calculateBi(BiOperatorModes.add, 2.2)==4.4);
		assertTrue(calc.calculateBi(BiOperatorModes.add, 0.0)==4.4);
		assertFalse(calc.calculateBi(BiOperatorModes.add, 2.2) == 0.0);
	}
	
	@Test
	void testCalculateBiImplAddDirect() {
		calc.num1 = 2.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.add;
		double x = calc.calculateBiImpl();
		assertTrue(x == calc.num1 + calc.num2);
		assertTrue(x == calc.num2 + calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 + calc.num1);
		assertNotSame(calc.calculateBiImpl(), x);
		calc.num1 = -2.0;
		calc.num2 = -2.0;
		double y = calc.calculateBiImpl();
		assertNotEquals(x,y);
		assertTrue(y == calc.num1 + calc.num2);
		assertTrue(y == calc.num2 + calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 + calc.num1);
		assertNotSame(calc.calculateBiImpl(), y);
	}
	
	@Test
	void testCalculateBiImplAddDirectDefault() {
		calc.num1 = 2.0;
		calc.num2 = 0.0;
		calc.mode = BiOperatorModes.add;
		double x = calc.calculateBiImpl();
		assertTrue(x == calc.num1);
		assertSame(calc.calculateBiImpl(), calc.num1);
		calc.num1 = -2.0;
		calc.num2 = 0.0;
		double y = calc.calculateBiImpl();
		assertNotEquals(x,y);
		assertTrue(y == calc.num1);
		assertSame(calc.calculateBiImpl(), calc.num1);
	}
	
	@Test
	void testCalculateBiImplMinus() {
		assertNotNull(calc.calculateBi(BiOperatorModes.minus, 2.2));
		assertTrue(calc.calculateBi(BiOperatorModes.minus, 2.2)==0.0);
		assertFalse(calc.calculateBi(BiOperatorModes.minus, 2.2)==4.4);
	}
	
	@Test
	void testCalculateBiImplMinusDirect() {
		calc.num1 = 4.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.minus;
		double x = calc.calculateBiImpl();
		assertTrue(x == calc.num1-calc.num2);
		assertFalse(x == calc.num2-calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2 - calc.num1);
		assertNotSame(calc.calculateBiImpl(), x);
		calc.num1 = -4.0;
		calc.num2 = -2.0;
		double y = calc.calculateBiImpl();
		assertNotEquals(x,y);
		assertTrue(y == calc.num1-calc.num2);
		assertFalse(y == calc.num2-calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2 - calc.num1);
		assertNotSame(calc.calculateBiImpl(), y);
	}
	
	@Test
	void testCalculateBiImplMultiply() {
		assertNotNull(calc.calculateBi(BiOperatorModes.multiply, 2.0));
		assertTrue(calc.calculateBi(BiOperatorModes.multiply, 2.0)==4.0);
		assertFalse(calc.calculateBi(BiOperatorModes.multiply, 2.0)==0.0);
	}
	
	@Test
	void testCalculateBiImplMultiplyDirect() {
		calc.num1 = 2.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.multiply;
		double x = calc.calculateBiImpl();
		assertTrue(x == calc.num1*calc.num2);
		assertTrue(x == calc.num2*calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 * calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 * calc.num1);
		assertNotSame(calc.calculateBiImpl(), x);
		calc.num1 = -2.0;
		calc.num2 = -2.0;
		double y = calc.calculateBiImpl();
		assertEquals(x,y);
		assertNotSame(x, y);
		assertTrue(y == calc.num1*calc.num2);
		assertTrue(y == calc.num2*calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 * calc.num2);
		assertEquals(calc.calculateBiImpl(),calc.num2 * calc.num1);
		assertNotSame(calc.calculateBiImpl(), y);
	}
	
	@Test
	void testCalculateBiImpldivide() {
		assertNotNull(calc.calculateBi(BiOperatorModes.divide, 2.0));
		assertTrue(calc.calculateBi(BiOperatorModes.divide, 4.0)==0.5);
		assertFalse(calc.calculateBi(BiOperatorModes.divide, 2.0)==0.0);
	}
	
	@Test
	void testCalculateBiImpldivideDirect() {
		calc.num1 = 4.0;
		calc.num2 = 2.0;
		calc.mode = BiOperatorModes.divide;
		double x = calc.calculateBiImpl();
		assertTrue(x == calc.num1/calc.num2);
		assertFalse(x == calc.num2/calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 / calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2/calc.num1);
		assertNotSame(calc.calculateBiImpl(), x);
		calc.num1 = -4.0;
		calc.num2 = -2.0;
		double y = calc.calculateBiImpl();
		assertEquals(x,y);
		assertNotSame(x, y);
		assertTrue(y == calc.num1/calc.num2);
		assertFalse(y == calc.num2/calc.num1);
		assertEquals(calc.calculateBiImpl(),calc.num1 / calc.num2);
		assertNotEquals(calc.calculateBiImpl(),calc.num2/calc.num1);
		assertNotSame(calc.calculateBiImpl(), y);
	}
	
	@Test
	void testCalculateBiImplXpowerofy() {
		assertNotNull(calc.calculateBi(BiOperatorModes.xpowerofy, 2.0));
		assertTrue(calc.calculateBi(BiOperatorModes.xpowerofy, 3.0)==8.0);
		assertFalse(calc.calculateBi(BiOperatorModes.xpowerofy, 2.0)==0.0);
	}
	
	@Test
	void testCalculateBiImplXpowerofyDirect() {
		calc.num1 = 2.0;
		calc.num2 = 3.0;
		calc.mode = BiOperatorModes.xpowerofy;
		double x = calc.calculateBiImpl();
		assertTrue(x == pow(calc.num1,calc.num2));
		assertFalse(x == pow(calc.num2,calc.num1));
		assertEquals(calc.calculateBiImpl(),pow(calc.num1,calc.num2));
		assertNotEquals(calc.calculateBiImpl(),pow(calc.num2,calc.num1));
		assertNotSame(calc.calculateBiImpl(), x);
		calc.num1 = -2.0;
		calc.num2 = -3.0;
		double y = calc.calculateBiImpl();
		assertNotEquals(x,y);
		assertTrue(y == pow(calc.num1,calc.num2));
		assertFalse(y == pow(calc.num2,calc.num1));
		assertEquals(calc.calculateBiImpl(),pow(calc.num1,calc.num2));
		assertNotEquals(calc.calculateBiImpl(),pow(calc.num2,calc.num1));
		assertNotSame(calc.calculateBiImpl(), y);
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
	//test tangent function
	//Note that the implementation of tangent is incorrect in the
	//underlying code, as tangent special cases are
	//specified in degrees while underlying math function expects radians.
	//This test thus exposes an error in the underlying code and will currently fail.
	void testCalculateMonoTan() {
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.tan, 0.0));
		assertEquals(1.732, calc.calculateMono(MonoOperatorModes.tan, (Math.PI / 3)), .001);
		assertEquals(NaN, calc.calculateMono(MonoOperatorModes.tan, (Math.PI / 2)));
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
	

}
