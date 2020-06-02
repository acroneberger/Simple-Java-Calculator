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
		calc.mode = BiOperatorModes.normal;
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = Double.MIN_VALUE;
		assertSame(calc.calculateBiImpl(), calc.num2);
		calc.num1 = Double.MIN_VALUE;
		calc.num2 = Double.MAX_VALUE;
		assertSame(calc.calculateBiImpl(), calc.num2);
		assertEquals(calc.num2, Double.MAX_VALUE);
		assertEquals(calc.num1, Double.MIN_VALUE);
		assertEquals(calc.calculateBi(BiOperatorModes.normal,
				Double.MAX_VALUE), NaN);
	}

	@Test
		//Exercises BiOperator addition Mode.
		//Similar to chaining additions (1+1+1+1...)
	void testCalculateBiImplAdd() {

		//Test Default Mode interaction
		calc.mode = BiOperatorModes.normal;
		assertEquals(calc.calculateBi(BiOperatorModes.add, Double.MAX_VALUE),
				NaN);

		//Test Setters
		assertNotNull(calc);
		assertEquals(calc.num1, Double.MAX_VALUE);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.mode, BiOperatorModes.add);

		//Test Arithmetic
		assertEquals(calc.calculateBi(BiOperatorModes.add, -Double.MAX_VALUE),
				0.0);
		assertEquals(calc.calculateBi(BiOperatorModes.add, -Double.MAX_VALUE),
				-Double.MAX_VALUE);

		assertEquals(calc.calculateBi(BiOperatorModes.add, Double.MAX_VALUE),
				0.0);
		assertEquals(calc.calculateBi(BiOperatorModes.add, Double.MAX_VALUE),
				Double.MAX_VALUE);

		assertEquals(calc.calculateBi(BiOperatorModes.add, 0.0),
				Double.MAX_VALUE);

		assertEquals(calc.calculateBi(BiOperatorModes.add, -Double.MIN_VALUE),
				Double.MAX_VALUE + -Double.MIN_VALUE);
		assertEquals(calc.calculateBi(BiOperatorModes.add, Double.MIN_VALUE),
				Double.MAX_VALUE);
	}

	@Test
		//Directly exercises function rather than go through CalculateBi
		// function as program intends.
		//First condition.
		//Tests Addition Arithmetic.
	void testCalculateBiImplAddDirect() {
		calc.mode = BiOperatorModes.add;

		calc.num1 = Double.MAX_VALUE;
		calc.num2 = Double.MAX_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 + calc.num1);

		calc.num1 = Double.MIN_VALUE;
		calc.num2 = Double.MIN_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 + calc.num1);

		calc.num1 = Double.MAX_VALUE;
		calc.num2 = -Double.MAX_VALUE;
		calc.mode = BiOperatorModes.add;
		assertEquals(calc.calculateBiImpl(), calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 + calc.num1);

		calc.num1 = -Double.MAX_VALUE;
		calc.mode = BiOperatorModes.add;
		assertEquals(calc.calculateBiImpl(), calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 + calc.num1);

		calc.num1 = Double.MIN_VALUE;
		calc.num2 = -Double.MIN_VALUE;
		calc.mode = BiOperatorModes.add;
		assertEquals(calc.calculateBiImpl(), calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 + calc.num1);

		calc.num1 = -Double.MIN_VALUE;
		calc.mode = BiOperatorModes.add;
		assertEquals(calc.calculateBiImpl(), calc.num1 + calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 + calc.num1);
	}

	@Test
		//Directly exercises function rather than go through CalculateBi
		// function as program intends.
		//Default Condition: Returns Num1 if Num2 = 0.0
	void testCalculateBiImplAddDirectDefault() {
		calc.mode = BiOperatorModes.add;
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = 0.0;
		assertSame(calc.calculateBiImpl(), calc.num1);
		calc.num1 = Double.MIN_VALUE;
		assertSame(calc.calculateBiImpl(), calc.num1);
	}

	@Test
		//Exercises BiOperator subtraction Mode.
	void testCalculateBiImplMinus() {

		//Test Default Mode interaction
		calc.mode = BiOperatorModes.normal;
		assertEquals(calc.calculateBi(BiOperatorModes.minus, Double.MAX_VALUE)
				, NaN);

		//Test Setters
		assertNotNull(calc);
		assertEquals(calc.num1, Double.MAX_VALUE);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.mode, BiOperatorModes.minus);

		//Test Arithmetic MAX VALUE
		assertEquals(calc.calculateBi(BiOperatorModes.minus, Double.MAX_VALUE)
				, 0.0);
		assertEquals(calc.calculateBi(BiOperatorModes.minus, Double.MAX_VALUE)
				, -Double.MAX_VALUE);
		assertEquals(calc.calculateBi(BiOperatorModes.minus, 0.0),
				-Double.MAX_VALUE);

		//Subtract Negative
		assertEquals(calc.calculateBi(BiOperatorModes.minus,
				-Double.MAX_VALUE), 0.0);

		//Test Arithmetic MIN VALUE
		assertEquals(calc.calculateBi(BiOperatorModes.minus, Double.MIN_VALUE)
				, -Double.MIN_VALUE);

		//Subtract Negative
		assertEquals(calc.calculateBi(BiOperatorModes.minus,
				-Double.MIN_VALUE), 0.0);
	}

	@Test
		//Directly exercises function rather than go through CalculateBi
		// function as program intends.
		//Tests Subtraction Arithmetic and order.
	void testCalculateBiImplMinusDirect() {
		calc.mode = BiOperatorModes.minus;
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = -Double.MAX_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(), calc.num2 - calc.num1);

		calc.num1 = Double.MIN_VALUE;
		calc.num2 = -Double.MIN_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(), calc.num2 - calc.num1);

		calc.num1 = 0.0;
		assertEquals(calc.calculateBiImpl(), calc.num1 - calc.num2);
		assertNotEquals(calc.calculateBiImpl(), calc.num2 - calc.num1);
	}

	@Test
		//Exercises BiOperator multiplication Mode.
	void testCalculateBiImplMultiply() {
		//Test Default Mode interaction
		calc.mode = BiOperatorModes.normal;
		assertEquals(calc.calculateBi(BiOperatorModes.multiply,
				Double.MAX_VALUE), NaN);

		//Test Setters
		assertNotNull(calc);
		assertEquals(calc.num1, Double.MAX_VALUE);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.mode, BiOperatorModes.multiply);

		//Test Arithmetic (Max Value x Max Value)
		assertEquals(calc.calculateBi(BiOperatorModes.multiply,
				Double.MAX_VALUE), Double.POSITIVE_INFINITY);

		//Test for sign switch when multiplying positive by negative.
		assertEquals(calc.calculateBi(BiOperatorModes.multiply, -1.0),
				Double.NEGATIVE_INFINITY);

		//Test multiply by 0
		assertEquals(calc.calculateBi(BiOperatorModes.multiply, 0.0), NaN);

	}


	@Test
		//Directly exercises function rather than go through CalculateBi
		// function as program intends.
		//Tests Multiplication Arithmetic and order.
	void testCalculateBiImplMultiplyDirect() {

		//Test Positive Multiplication.
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = Double.MAX_VALUE;
		calc.mode = BiOperatorModes.multiply;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 * calc.num1);

		//Test Negative Multiplication.
		calc.num1 = Double.MIN_VALUE;
		calc.num2 = Double.MIN_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 * calc.num1);

		//Test Sign Switch.
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = -1.0;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		assertEquals(Math.signum(calc.num1 / calc.num2), Math.signum(-1));
		calc.num1 = -Double.MAX_VALUE;
		calc.num2 = -1.0;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		assertEquals(Math.signum(calc.num1 / calc.num2), Math.signum(1));

		//Test Multiply by 0.
		calc.num1 = Double.MAX_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		calc.num1 = -Double.MAX_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		calc.num1 = Double.MIN_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
		calc.num1 = -Double.MIN_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 * calc.num2);
	}

	@Test
		//Exercises BiOperator division Mode.
	void testCalculateBiImpldivide() {
		//Test Default Mode interaction
		calc.mode = BiOperatorModes.normal;
		assertEquals(calc.calculateBi(BiOperatorModes.divide,
				Double.MAX_VALUE), NaN);

		//Test Setters
		assertNotNull(calc);
		assertEquals(calc.num1, Double.MAX_VALUE);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.mode, BiOperatorModes.divide);

		//Test Arithmetic (Max Value / Max Value)
		assertEquals(calc.calculateBi(BiOperatorModes.divide,
				Double.MAX_VALUE), 1);


		//Test for sign switch when dividing positive by negative.
		assertEquals(calc.calculateBi(BiOperatorModes.divide, -1.0), -1.0);


		//Test divide by 0.
		//Java double / 0 = (Signed)Infinity
		assertEquals(calc.calculateBi(BiOperatorModes.divide, 0.0),
				Double.NEGATIVE_INFINITY);
	}

	@Test
		//Directly exercises function rather than go through CalculateBi
		// function as program intends.
		//Tests Division Arithmetic and order.
	void testCalculateBiImpldivideDirect() {

		//Test Positive Division.
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = Double.MAX_VALUE;
		calc.mode = BiOperatorModes.divide;
		assertEquals(calc.calculateBiImpl(), calc.num1 / calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 / calc.num1);

		//Test Negative Division.
		calc.num1 = -Double.MAX_VALUE;
		calc.num2 = -Double.MAX_VALUE;
		assertEquals(calc.calculateBiImpl(), calc.num1 / calc.num2);
		assertEquals(calc.calculateBiImpl(), calc.num2 / calc.num1);

		//Test Order.
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = Double.MIN_VALUE;
		calc.mode = BiOperatorModes.divide;
		assertEquals(calc.calculateBiImpl(), calc.num1 / calc.num2);
		assertNotEquals(calc.calculateBiImpl(), calc.num2 / calc.num1);

		//Test Sign Switch.
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = -1.0;
		assertEquals(calc.calculateBiImpl(), calc.num1 / calc.num2);
		assertEquals(Math.signum(calc.num1 / calc.num2), Math.signum(-1));
		calc.num1 = -Double.MAX_VALUE;
		calc.num2 = -1.0;
		assertEquals(calc.calculateBiImpl(), calc.num1 / calc.num2);
		assertEquals(Math.signum(calc.num1 / calc.num2), Math.signum(1));

		//Test Divide by 0.
		calc.num1 = Double.MAX_VALUE;
		calc.num2 = 0.0;
		assertEquals(calc.calculateBiImpl(), Double.POSITIVE_INFINITY);
		calc.num1 = -Double.MAX_VALUE;
		calc.num2 = 0.0;
		assertEquals(calc.calculateBiImpl(), Double.NEGATIVE_INFINITY);
		calc.num1 = Double.MIN_VALUE;
		calc.num2 = 0.0;
		assertEquals(calc.calculateBiImpl(), Double.POSITIVE_INFINITY);
		calc.num1 = -Double.MIN_VALUE;
		calc.num2 = 0.0;
		assertEquals(calc.calculateBiImpl(), Double.NEGATIVE_INFINITY);
	}

	@Test
		//Exercises BiOperator exponent Mode.
		//Check edge cases.
	void testCalculateBiImplXpowerofy() {
		//Test Default Mode interaction
		calc.mode = BiOperatorModes.normal;
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.MAX_VALUE), NaN);

		//Test Setters
		assertNotNull(calc);
		assertEquals(calc.num1, Double.MAX_VALUE);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.mode, BiOperatorModes.xpowerofy);

		//Test Arithmetic (Max Value ^ Max Exponent)
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.valueOf(Double.MAX_EXPONENT)),
				Double.POSITIVE_INFINITY);

		//Test Arithmetic (-Max Value ^ Max Exponent)
		calc.num1 = -Double.MAX_VALUE;
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.valueOf(Double.MAX_EXPONENT)),
				Double.NEGATIVE_INFINITY);


		//Test Arithmetic (1 ^ MAX/MIN_EXPONENT)
		calc.num1 = 1.0;
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.valueOf(Double.MAX_EXPONENT)), 1);
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.valueOf(Double.MIN_EXPONENT)), 1);

		//Test Arithmetic (-1 ^ MAX/MIN_EXPONENT)
		calc.num1 = -1.0;
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.valueOf(Double.MAX_EXPONENT)), -1);
		//We expect that the answer should be negative given ((-1)^-y) = -1
		//however, the Math.Pow Function calculates results of pow() as (-1)
		// ^-y,
		//therefore -1 to the power of any negative exponent will result in a
		// positive 1.
		assertNotEquals(calc.calculateBi(BiOperatorModes.xpowerofy,
				Double.valueOf(Double.MIN_EXPONENT)), -1);

		//Test POW of 0
		calc.num1 = Double.MAX_VALUE;
		assertEquals(calc.calculateBi(BiOperatorModes.xpowerofy, 0.0), 1);

		//Test Negative POW of 0
		//We expect that the answer should be negative given ((-x)^y) = -1,
		// where y = 0
		//however the Math.Pow Function calculates results of pow() as (x)^y,
		// therefore
		//any number (negative or positive) to the power of 0 = 1.
		calc.num1 = -Double.MAX_VALUE;
		assertNotEquals(calc.calculateBi(BiOperatorModes.xpowerofy, 0.0), -1);
	}

	@Test
		//Directly exercises function rather than go through CalculateBi
		// function as program intends.
		//Tests Exponent Arithmetic and order.
	void testCalculateBiImplXpowerofyDirect() {
		calc.mode = BiOperatorModes.xpowerofy;

		//Test Positive to the power of Max Exponent
		calc.num1 = 2.0;
		calc.num2 = Double.valueOf(Double.MAX_EXPONENT);
		assertEquals(calc.calculateBiImpl(), pow(calc.num1, calc.num2));
		assertNotEquals(calc.calculateBiImpl(), pow(calc.num2, calc.num1));

		//Test Positive to the power of Min Exponent
		calc.num2 = Double.valueOf(Double.MIN_EXPONENT);
		assertEquals(calc.calculateBiImpl(), pow(calc.num1, calc.num2));
		assertNotEquals(calc.calculateBiImpl(), pow(calc.num2, calc.num1));

		//Test Negative to the power of Max Exponent
		calc.num1 = -2.0;
		calc.num2 = Double.valueOf(Double.MAX_EXPONENT);
		assertEquals(calc.calculateBiImpl(), pow(calc.num1, calc.num2));
		assertNotEquals(calc.calculateBiImpl(), pow(calc.num2, calc.num1));

		//Test Negative to the power of Min Exponent
		calc.num2 = Double.valueOf(Double.MIN_EXPONENT);
		assertEquals(calc.calculateBiImpl(), pow(calc.num1, calc.num2));
		assertNotEquals(calc.calculateBiImpl(), pow(calc.num2, calc.num1));
	}

	@Test
		//Forces calculateBiImp to default (general error thrown).
	void testCalculateBiImpDefault() {
		calc.mode = null;
		Error thrown = assertThrows(Error.class, () -> calc.calculateBiImpl(),
				"Should Throw");
		assertEquals(thrown.getMessage(), null);
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
		assertEquals(3.0, calc.calculateMono(MonoOperatorModes.squareRoot,
				9.0));
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.squareRoot,
				0.0));
		assertEquals(NaN, calc.calculateMono(MonoOperatorModes.squareRoot,
				-1.0));
	}

	@Test
		//test 1/x function with 5, 0, and a slight delta around 0
	void testCalculateMonoOneDevidedBy() {
		assertEquals(0.2, calc.calculateMono(MonoOperatorModes.oneDevidedBy,
				5.0));
		assertEquals(Double.POSITIVE_INFINITY,
				calc.calculateMono(MonoOperatorModes.oneDevidedBy, 0.0));
		assertNotEquals(Double.POSITIVE_INFINITY,
				calc.calculateMono(MonoOperatorModes.oneDevidedBy, -.001));
		assertNotEquals(Double.POSITIVE_INFINITY,
				calc.calculateMono(MonoOperatorModes.oneDevidedBy, .001));
	}

	@Test
		//test cosine function with 0, pi/3 and pi/2 radians
	void testCalculateMonoCos() {
		assertEquals(1.0, calc.calculateMono(MonoOperatorModes.cos, 0.0));
		assertEquals(.500, calc.calculateMono(MonoOperatorModes.cos,
				(Math.PI / 3)), .001);
		assertEquals(0.000, calc.calculateMono(MonoOperatorModes.cos,
				(Math.PI / 2)), .001);
	}

	@Test
		//test sine function with 0, pi/3 and pi/2 radians
	void testCalculateMonoSin() {
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.sin, 0.0));
		assertEquals(.866, calc.calculateMono(MonoOperatorModes.sin,
				(Math.PI / 3)), .001);
		assertEquals(1.000, calc.calculateMono(MonoOperatorModes.sin,
				(Math.PI / 2)), .001);
	}

	@Test
		//Test tangent function
		//Note that the implementation of tangent is incorrect in the
		//underlying code, as tangent special cases are
		//specified in degrees while underlying math function expects radians.
		//This test thus exposes an error in the underlying code and will
		// currently fail if asserted equal.
		//Furthermore, complete coverage is not possible due to the lack of
		// conditional default.
	void testCalculateMonoTan() {
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.tan, 0.0));
		assertEquals(0.0, calc.calculateMono(MonoOperatorModes.tan, 180.0));
		assertEquals(1.732, calc.calculateMono(MonoOperatorModes.tan,
				(Math.PI / 3)), .001);
		assertEquals(NaN, calc.calculateMono(MonoOperatorModes.tan, 90.0));
		assertNotEquals(NaN, calc.calculateMono(MonoOperatorModes.tan, 180.0));

		//According to code, this should be equal.
		assertEquals(NaN, calc.calculateMono(MonoOperatorModes.tan,
				(Math.PI / 2)));
	}

	@Test
		//Test log function with 0,1, and a slight delta around 0 for
		// boundaries
	void testCalculateMonoLog() {
		assertEquals(Double.NEGATIVE_INFINITY,
				calc.calculateMono(MonoOperatorModes.log, 0.0));
		assertNotEquals(Double.NEGATIVE_INFINITY,
				calc.calculateMono(MonoOperatorModes.log, .001));
		assertNotEquals(Double.NEGATIVE_INFINITY,
				calc.calculateMono(MonoOperatorModes.log, -.001));
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
		Error thrown = assertThrows(Error.class, () -> calc.calculateMono(null
				, 0.0), "Should Throw");
		assertEquals(thrown.getMessage(), null);
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
		assertNotEquals(calc.num1, x);
		assertNotEquals(calc.num2, y);
		assertNotEquals(calc.mode, testMode);
		assertEquals(calc.num1, 0.0);
		assertEquals(calc.num2, 0.0);
		assertEquals(calc.mode, BiOperatorModes.normal);
		assertEquals(calc.reset(), NaN);
	}

	@Test
		//Acts as an "equal button" when given the second number of the
		// expression.
		//Enter a number and an operator into a calculator
		//This function acts as the following:
		//Enter second number and press "equals"
	void calculateEqual() {
		assertNotNull(calc);
		double num = 2.0;
		//Test entering a number and pressing equals
		assertEquals(calc.calculateEqual(num), NaN);

		//Test addition
		calc.mode = BiOperatorModes.add;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num), 4.0);

		//Test Subtraction
		calc.mode = BiOperatorModes.minus;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num), 0.0);

		//Test Multiplication
		calc.mode = BiOperatorModes.multiply;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num), 4.0);

		//Test Division
		calc.mode = BiOperatorModes.divide;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num), 1.0);

		//Test Exponent
		calc.mode = BiOperatorModes.xpowerofy;
		calc.num1 = 2.0;
		assertEquals(calc.calculateEqual(num), 4.0);
	}

}
