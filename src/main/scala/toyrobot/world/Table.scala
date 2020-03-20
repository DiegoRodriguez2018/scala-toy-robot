package toyrobot;
import toyrobot.{ Position }

object Table {
    val tableSize = 5;

    def isValidPosition(position:Position) = {
        val (x, y) = position.unapply
        x >= 0 & x <= this.tableSize & y >= 0 & y <= this.tableSize
    }
}