#!/bin/bash
cd src/simplejavacalculator/
pwd
rm -rf testClasses
mkdir testClasses
javac128="/etc/alternatives/java_sdk_11_openjdk/bin/javac -J-Xms128m -J-Xmx128m"

$javac128 -d testClasses Calculator.java BufferedImageCustom.java
echo "compiling calculator"
#$javac128 -d testClasses BufferedImageCustom.java
$javac128 -d testClasses -cp testClasses:junit-platform-console-standalone-1.6.2.jar CalculatorTest.java  BufferedImageCustomTest.java
echo "compiling unit tests"
#$javac128 -d testClasses -cp testClasses:junit-platform-console-standalone-1.6.2.jar TestSuite.java
