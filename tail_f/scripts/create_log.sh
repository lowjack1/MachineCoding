#!/bin/bash
val=1
while [ 1 ]
do
    echo "logging value = $val"
    echo "Logging value = $val" >> ../app.log
    val=$(( $val + 1 ))
    sleep 2
done