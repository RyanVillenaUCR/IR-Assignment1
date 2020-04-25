# IR-Assignment1
Assignment 1 code for my Information Retrieval class. Specs are [here](https://www.cs.ucr.edu/~vagelis/classes/CS172/assignment1.html).

## Usage

On Linux, I use the following:

`$ ./compile.sh && ./run.sh`

Note that you might have to run `$ chmod +x *.sh` in order to execute the provided shell files.

## Behavior

To change hashing terms from `mod 255` to `mod 256`, change [`ExerciseB.MOD_VALUE`](https://github.com/RyanVillenaUCR/IR-Assignment1/blob/37b8bd93e4fe899b17a0f7c5f00ec91cd714faa3/src/ExerciseB.java#L165).

To change which documents are compared (e.g. excluding Document 0), change [`lo_bound`](https://github.com/RyanVillenaUCR/IR-Assignment1/blob/37b8bd93e4fe899b17a0f7c5f00ec91cd714faa3/src/ExerciseB.java#L147) or [`hi_bound`](https://github.com/RyanVillenaUCR/IR-Assignment1/blob/37b8bd93e4fe899b17a0f7c5f00ec91cd714faa3/src/ExerciseB.java#L148) to what you want.
