package simplejavacalculator;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.pow;
import org.junit.jupiter.api.Test;

import simplejavacalculator.Calculator.BiOperatorModes;
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
