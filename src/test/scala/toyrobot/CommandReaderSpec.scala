import org.specs2.mutable.Specification
import toyrobot.CommandReader.{getCommands, validate, getCommandsAfterFirstPlaceCommand}
class CommandReaderSpec extends Specification {
    "CommandReader" should {
        "getCommands" should {
            "return the commands wrapped in type List[String] " in {
                val path ="src/test/scala/resources/input_1.txt"
                val result = getCommands(path)
                result must beAnInstanceOf[List[String]]
            }
        }
        "validate" should {
            "return false when including invalid commands in input file" in {
                val path ="src/test/scala/resources/invalidInput_1.txt"
                val result = validate(path)
                result must beEqualTo(false)
            }
            "return true when including valid commands in input file" in {
                val path ="src/test/scala/resources/input_1.txt"
                val result = validate(path)
                result must beEqualTo(true)
            }
        }
        "getCommandsAfterFirstPlaceCommand" should {
            "return a list ignoring commands before PLACE command in input " in {
                val allCommands: List[String] = List("MOVE","MOVE","REPORT","PLACE 0,4,NORTH","MOVE","MOVE","LEFT","RIGHT","REPORT")
                val result = getCommandsAfterFirstPlaceCommand(allCommands)
                val expected = List("PLACE 0,4,NORTH","MOVE","MOVE","LEFT","RIGHT","REPORT")
                result must beEqualTo(expected)
            }
        }
    }
}


