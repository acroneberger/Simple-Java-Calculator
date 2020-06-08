
# stressTest.sh

# Constants
log_file="stressTestlogs.txt"
NumBackGroundProcesses=$1
NumberLoop=$2

# Check number of arguments
if [ "$#" != "2" ]; then
	echo "ERROR: Invalid number of input arguments."
	exit 1
fi

# Check number of iterations 
if [ "$1" -le "0" ] || [ "$2" -le "0" ]; then
	echo "ERROR: Input values must be bigger than zero"
	exit 2 
fi

# Remove old log files
echo Removing older log files... 
rm -f -v log_file
rm -f -v stressTestlogs_*.txt
rm -f -v testout.txt
rm -f -v testout_*.txt
rm -f -v hs_err_pid*.log

# Record start time
echo "Stress Testing...START"
# Record number of seconds a script has been running
START_TIME=$SECONDS

# Start processes in the background 
for i in `seq 1 $NumBackGroundProcesses`; do
	echo "- Start instance $i of $NumBackGroundProcesses"
	(./runTestSuite.sh $NumberLoop >> stressTestlogs_$i.txt) & pid_list[$i]=$!
done

echo "Waiting until all processes are done"

# Wait until all processes are done
for i in `seq 1 $NumBackGroundProcesses`; do
	echo "- Wait for $i to finish"
	wait ${pid_list[$i]}
done

# Record end time
echo "Stress Testing...Done"

# Record number of seconds a script has been running
END_TIME=$SECONDS

sleep 1

echo "Combining all log files back to into log file..."
# Combine all log files back to into log file
for i in `seq 1 $NumBackGroundProcesses`; do
	cat stressTestlogs_$i.txt >> $log_file
done

# Parse results and report PASS/FAIL

echo "Parsing results and report PASS/FAIL..."
numCases=$(grep "tests found" $log_file | grep -o -E '[0-9]+' | awk '{n += $1}; END{print n}')

# Search the log file for the number of failing test cases
numCaseFail=$(grep "tests failed" $log_file | grep -o -E '[0-9]+' | awk '{n += $1}; END{print n}')

# The remainder are passing tests
numCasePass=$(grep "tests successful" $log_file | grep -o -E '[0-9]+' | awk '{n += $1}; END{print n}')

passRate=$(bc -l <<< "scale=2; $numCasePass/$numCases*100")

# Perform calculations
echo "Performing calculations..."

ELAPSED_TIME=$(( $END_TIME - $START_TIME ))

echo "Execution time: $ELAPSED_TIME in seconds"
echo "Expected number of PASS instances: $numCases"
echo "Number of PASS tests: $numCasePass"
echo "Number of FAIL tests: $numCaseFail"
echo "Passing rate: $passRate%"

exit 0
