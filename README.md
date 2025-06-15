# Sudoku Solver

This is a Scala program that provides implementation for solving Sudoku.

The sudoku board is given as 2D Array of integers, where 0 is representing the empty cell


---
The algorithm to solve the sudoku:
- Find the first empty cell
-  Try each valid digit for that cell (based on row/col/box constraints)
-  Place it and recurse
-  If the board becomes invalid → backtrack