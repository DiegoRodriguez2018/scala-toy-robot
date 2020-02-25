package toyrobot
import scala.io.Source

object CommandReader {
    def validate(path:String): Boolean = {
        val lines = Source.fromFile(path).getLines().toList
        val validCommands = List("PLACE", "MOVE", "LEFT", "RIGHT", "REPORT")
        
        val areValidCommands = for {
            line <-lines
            firstWord =  line.split(" ").head
            isValidCommand = validCommands.contains(firstWord)
        } yield isValidCommand

        areValidCommands.foldRight(true)(_ & _)
    }

    def getCommands(path:String): List[String] = Source.fromFile(path).getLines().toList
}