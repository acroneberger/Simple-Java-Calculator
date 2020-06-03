#!/bin/bash
cd src/simplejavacalculator
numLoops=$1
optionalEmail=$2

echo $1
echo $2


for i in $(seq 1 $numLoops)
do
echo $i
java -Xms128m -Xmx128m -jar junit-platform-console-standalone-1.6.2.jar --class-path testClasses --scan-class-path
done
