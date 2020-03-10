package toyrobot
import scala.io.Source

object CommandReader {
    val validCommands = List("PLACE", "MOVE", "LEFT", "RIGHT", "REPORT")
    
    def validate(path:String): Boolean = {
        val lines = Source.fromFile(path).getLines().toList
        val areValidCommands = for {
            line <-lines
            firstWord =  line.split(" ").head
            isValidCommand = validCommands.contains(firstWord)
        } yield isValidCommand
        areValidCommands.foldRight(true)(_ & _)
    }

    def getCommands(path:String): List[String] = {
        val allCommands = Source.fromFile(path).getLines().toList
        getCommandsAfterFirstPlaceCommand(allCommands)
    }

    def getCommandsAfterFirstPlaceCommand(allCommands: List[String]): List[String] = {
        // Ignoring commands before the first PLACE command
        val firstPlaceCommandIndex = allCommands.map(_.split(" ").head).indexOf("PLACE")
        allCommands.slice(firstPlaceCommandIndex, allCommands.length) 
    }
}