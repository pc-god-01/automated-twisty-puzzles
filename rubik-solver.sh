#!bin/bash
# rubik-solver: solves a rubiks cube from a given file
# Usage: rubik-solver cubefile
# TODO allow options
cubefile = $1
java -jar bin/out.jar ${cubefile:?"missing"}
