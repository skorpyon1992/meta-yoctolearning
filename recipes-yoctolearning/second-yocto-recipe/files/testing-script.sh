#! /bin/sh

trap signal_handler 9 15

signal_handler()
{
  echo "Caught SIGTERM signal, closing..."
  done=1
}

index=1
done=0

while [ $done -ne 1 ]
do
  logger "Some testing script ${index}"
  index=$(( index + 1 ))
 sleep 1
done
