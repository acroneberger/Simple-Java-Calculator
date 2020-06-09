#!/bin/bash
#cd src/simplejavacalculator/

rm -rf testClasses
mkdir testClasses
javac128="/etc/alternatives/java_sdk_11_openjdk/bin/javac -J-Xms128m -J-Xmx128m"
$javac128 -d testClasses src/simplejavacalculator/Calculator.java
echo "compiling calculator"
$javac128 -d testClasses -cp testClasses:junit-platform-console-standalone-1.6.2.jar src/simplejavacalculator/CalculatorTest.java
echo "compiling unit tests"
if [ $# -ne 0 ]
then
echo "An error occurred during compilation. Exiting..."
exit 1
fi
echo "Compilation complete!"
