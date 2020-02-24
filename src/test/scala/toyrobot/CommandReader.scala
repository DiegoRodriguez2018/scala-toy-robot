import org.specs2.mutable.Specification
import toyrobot.commandreader.{CommandReader}

class CommandReaderTest extends Specification {
    "CommandReader" should {
        "return a commands as type List[String] " in {
            val path ="../resources/commands.txt"
            val result = CommandReader.getCommands(path)
            
            result must haveClass[List[String]]
        }
    }
}


