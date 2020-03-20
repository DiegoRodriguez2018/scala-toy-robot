package toyrobot
import scala.io.Source

object CommandReader {
    val validCommands = List("PLACE", "MOVE", "LEFT", "RIGHT", "REPORT")
    
    def getLines(path:String):List[String]={
        val source = Source.fromFile(path)
        val lines = source.getLines().toList
        source.close()
        lines
    }

    def validate(path:String): Boolean = {
        val lines = getLines(path)
        val areValidCommands = for {
            line <-lines
            firstWord =  line.split(" ").head
            isValidCommand = validCommands.contains(firstWord)
        } yield isValidCommand
        areValidCommands.foldRight(true)(_ & _)
    }

    def getCommands(path:String): List[String] = {
        val allCommands = getLines(path)
        getCommandsAfterFirstPlaceCommand(allCommands)
    }

    def getCommandsAfterFirstPlaceCommand(allCommands: List[String]): List[String] = {
        // Ignoring commands before the first PLACE command
        val firstPlaceCommandIndex = allCommands.map(_.split(" ").head).indexOf("PLACE")
        allCommands.slice(firstPlaceCommandIndex, allCommands.length) 
    }
}