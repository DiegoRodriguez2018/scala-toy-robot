import org.specs2.mutable.Specification
import toyrobot.CommandReader.{getCommands, validate}
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
    }
}


