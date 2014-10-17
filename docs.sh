#!/bin/bash

SRCDIR='src'
DSTDIR='doc'
WINTITLE="Automated Magic Twisty Puzzles"
DOCTITLE="Automated Magic Twisty Puzzles"
HEADER="<b>Automated Magic Twisty Puzzles</b>"
BOTTOM="<font size = 1><a href=\"https://github.com/pc-god-01/rubik_solver\">GitHub Repository</a>"
GROUPPZL='"Puzzles" "main.java.pcgod01.puzzle.*"'
GROUPRPT='"Repeat" "main.java.pcgod01.repeat.*"'
GROUPSLV='"Solve" "main.java.pcgod01.solve.*"'
API="http://docs.oracle.com/javase/7/docs/api"

echo Generating Javadoc

javadoc -d "${DSTDIR}"\
        -sourcepath "${SRCDIR}"\
        -use\
        -splitIndex\
        -windowtitle "${WINTITLE}"\
        -doctitle "${DOCTITLE}"\
        -header "${HEADER}"\
        -bottom "${BOTTOM}"\
        -group ${GROUPPZL}\
        -group ${GROUPRPT}\
        -group ${GROUPSLV}\
        -subpackages  main\
        -link "${API}"\
        -quiet

echo Done
