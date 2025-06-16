#!/bin/bash

if [ $# -eq 0 ]; then
    java L0int
    exit 0
fi

if [ $# -eq 1 ]; then
    java L0int "$1"
    exit 0
fi