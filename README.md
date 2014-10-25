Automated Twisty Puzzles
========================
Automated Twisty Puzzles (ATP) is a project that allows you to virtually do anything you want to a twisty puzzle.

Currently, we are working on two sub-projects:
* solve
* repeat

The project is made mostly for linux, but is easily able to expand to other platforms.

solve
-----
solve is a command-line script that takes any compatible xml file and produces another which contains the moves required to solve it.

Currently, very little work has gone into this aspect, with more emphasis placed on repeat.

repeat
------
repeat is a simple command-line script, taking at least one compatible xml file. This xml file contains the sequence of moves to apply, and, if another isn't specified, the required puzzle builder. The program then outputs the number of times the move sequence needs to be repeated to return to the original.

Currently, to run this script, enter the following in your shell from the root project directory.

    . repeat.sh [options] <move-file> [puzzle-file]

For a full list of options, run the following command.

    . repeat.sh -h

This aspect is very near completion, with only minor improvements to be made.

XML Syntax
==========
The xml files that contain the data the programs require to function. There are currently two main structures.

move-sequence
-------------
The `<move-sequence>` tag is used to delimit a sequence of moves. From this element, only `<move>` tags will be read. A move tag contains one attribute, the key. The key is the move to be used. For example,

    <move-sequence>
        <move key = "R"/>
        <move key = "U"/>
    </move-sequence>
    
will apply the moves 'R' and 'U', in that order.

puzzle-builder
--------------
The `<puzzle-builder>` tag is used to create puzzles. The tag *must* have a class attribute, or will otherwise be ignored. The class attribute is the binary name of the puzzle builder. It can also optionally contain an `<args>` tag. This is the arguments for the puzzle builder. This is interpreted differently for every builder.

The following is an example of a puzzle builder for a 3x3x3 cube.

    <puzzle-builder class = "main.java.pcgod01.puzzle.cube.Builder">
        <args sideLength = "3"/>
    </puzzle-builder>
