#!/bin/bash

# shellcheck disable=SC2223
: ${SLEEP_SECOND:=2}

wait_for() {
  echo Waiting for $1 on port $2... ...
  # shellcheck disable=SC2086
  # shellcheck disable=SC2188
  while ! </dev/tcp/$1/$2; do
    echo Waiting for $1 on port $2... ...
    sleep $SLEEP_SECOND
  done
  echo Server:"$1" on port:"$2" is running... ...
}

SER_STRS=$1

# shellcheck disable=SC2207
# shellcheck disable=SC2006
SERVICES_PORTS=($(echo "$SER_STRS" | tr ',' ' '))

THEN_COMMAND=$2

for ((i = 0; i < ${#SERVICES_PORTS[@]}; i++)); do
  SERVICE_PORT=${SERVICES_PORTS[${i}]}
  # shellcheck disable=SC2207
  # shellcheck disable=SC2006
  array=($(echo "$SERVICE_PORT" | tr ':' ' '))
  servername=${array[0]}
  serverport=${array[1]}
  wait_for "$servername" "${serverport}"
done
echo "start to run command: $THEN_COMMAND"
# shellcheck disable=SC2004
if [ "$THEN_COMMAND" ]; then
  eval "$THEN_COMMAND"
else
  # shellcheck disable=SC2102
  echo command: [$THEN_COMMAND] is invalid... ...
fi
