#!/bin/sh

# test 1
actual=$(echo "2" | jjs -scripting next-year.js)

if [ "$actual" != "Please input your age: Next year, you will be 3" ]; then
    echo "fail1"
    echo $actual
fi

# test 2
actual=$(AGE=3 jjs -scripting next-year.js)

if [ "$actual" != "Next year, you will be 4" ]; then
    echo "fail2"
    echo $actual
fi

# test 3
actual=$(jjs -scripting next-year.js -- 4)

if [ "$actual" != "Next year, you will be 5" ]; then
    echo "fail3"
    echo $actual
fi

# test 4
actual=$(AGE=5 jjs -scripting next-year.js -- 6)

if [ "$actual" != "Next year, you will be 7" ]; then
    echo "fail4"
    echo $actual
fi

