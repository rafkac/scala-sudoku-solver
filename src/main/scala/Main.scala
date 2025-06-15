object Main extends App {
  // declare type Board
  type Board = Array[Array[Int]]

  // sample sudoku board to solve
  val board: Board = Array(
    Array(5, 3, 0, 0, 7, 0, 0, 0, 0),
    Array(6, 0, 0, 1, 9, 5, 0, 0, 0),
    Array(0, 9, 8, 0, 0, 0, 0, 6, 0),
    Array(8, 0, 0, 0, 6, 0, 0, 0, 3),
    Array(4, 0, 0, 8, 0, 3, 0, 0, 1),
    Array(7, 0, 0, 0, 2, 0, 0, 0, 6),
    Array(0, 6, 0, 0, 0, 0, 2, 8, 0),
    Array(0, 0, 0, 4, 1, 9, 0, 0, 5),
    Array(0, 0, 0, 0, 8, 0, 0, 7, 9)
  )

  // main method
  def solve(board: Board): Boolean = {
    findEmptyCell(board) match {
      case None => true
      case Some((row, column)) => (1 to 9).exists { num =>
        if (validate(board, row, column, num)) {
          board(row)(column) = num
          val solved = solve(board)
          if (solved) true
          else {
            board(row)(column) = 0 // Backtrack
            false
          }
        } else false
      }

    }
  }

  // helper method to find empty cell
  def findEmptyCell(board: Array[Array[Int]]): Option[(Int, Int)] = {
    for {
      row <- board.indices
      col <- board(row).indices
      if board(row)(col) == 0
    } return Some((row, col))

    None
  }

  // helper method to validate if given digit is possible in given place
  def validate(board: Board, x: Int, y: Int, value: Int): Boolean = {
    // check row
    val rowValid = !board(x).contains(value)
    // check column
    val columnValid = !board.map(row => row(y)).contains(value)

    // check 3x3 subgrid
    val boxRowStart = (x / 3) * 3
    val boxColStart = (y / 3) * 3

    val boxValid = !(
      for {
        r <- boxRowStart until boxRowStart + 3
        c <- boxColStart until boxColStart + 3
      } yield board(r)(c)
      ).contains(value)

    rowValid && columnValid && boxValid
  }

  //
  def printBoard(board: Board): Unit = println {
    board.grouped(3).map { bigGroup =>
      bigGroup.map { row =>
        row.grouped(3).map {smallGroup =>
          smallGroup.mkString(" ")
        }.mkString(" | ")
      }.mkString("\n")
    }.mkString("\n" + "-" * 21 + "\n")
  }

  println("initial board:")
  printBoard(board)

  println("solved board:")
  solve(board)
  printBoard(board)
}

