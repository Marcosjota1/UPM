#!/bin/bash

x=1

# Read a file
file_name="file.txt"
while read -r line; do
  # Process each line
  echo "Line $x $line"
  (( x++ ))
done < "$file_name"