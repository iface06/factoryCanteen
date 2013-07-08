#!/bin/bash

BASE_DIR=`dirname $0`

echo ""
echo "Starting Testacular Server (http://vojtajina.github.com/testacular)" 
echo "-------------------------------------------------------------------"
echo $BASE_DIR/../config/testacular-e2e.conf.js

testacular start $BASE_DIR/../config/testacular-e2e.conf.js $*
