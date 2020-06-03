#!/bin/bash

cd src/simplejavacalculator
numLoops=$1
optionalEmail=$2
echo "Starting test at $(date)" > testout.txt

for i in $(seq 1 $numLoops)
do
echo $i
java -Xms128m -Xmx128m\
 -jar junit-platform-console-standalone-1.6.2.jar\
 --class-path testClasses --scan-class-path | tee -a testout.txt 
result=$?
echo $result
done

echo "Ending test at $(date)" >> testout.txt

if [ $# -eq 2 ]
then
echo "sending email to $optionalEmail"
echo "your test results are included" | mailx -s' test output' -a testout.txt $optionalEmail
fi


echo "done"
