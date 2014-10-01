#!bin/bash
# rubik-solver: solves a rubiks cube from a given file
# Usage: rubik-solver cubefile
# TODO allow options
echo troll
cubefile = $1
echo lol
java -jar bin/out.jar ${cubefile:?"missing"}
