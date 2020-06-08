#!/bin/bash

numLoops=$1
optionalEmail=$2
number=$RANDOM
echo "Starting test at $(date)" > testout_$number.txt

for i in $(seq 1 $numLoops)
do
echo $i
java -Xmx512m -Xmx512m\
 -jar junit-platform-console-standalone-1.6.2.jar\
 --class-path testClasses --scan-class-path | tee -a testout_$number.txt
result=$?
if [ $? -ne 0 ]
then
echo "An error occurred on running the tests. Exiting..."
exit 1
fi
done

#process results (use only last test in the event that multiple tests were run)
totaltests=$(grep -o -P "(\d+) tests found" testout_$number.txt | tail -1 | cut -d ' ' -f 1)
successtests=$(grep -o -P "(\d+) tests successful" testout_$number.txt | tail -1 | cut -d ' ' -f 1)
failtests=$(grep -o -P "(\d+) tests failed" testout_$number.txt | tail -1 | cut -d ' ' -f 1)

echo "Total Tests: $totaltests"
echo "Passed Tests: $successtests"
echo "Failed Tests: $failtests"
echo "Ending test at $(date)"



if [ $# -eq 2 ]
then
echo "sending email to $optionalEmail"
if [ $failtests -eq 0 ]
then
status='PASSED'
msg="All tests passed, results are included in attached document"
else
status='FAILED'
msg="$failtests tests failed, results are included in attached document."
fi
echo $msg | mailx -s "Results from test run: $status" -a testout_$number.txt $optionalEmail
fi


echo "done"

exit 0
