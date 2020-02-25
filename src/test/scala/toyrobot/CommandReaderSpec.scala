import org.specs2.mutable.Specification
import toyrobot.CommandReader.{getCommands}
class CommandReaderSpec extends Specification {
    "CommandReader" should {
        "return the commands wrapped in type List[String] " in {
            val path ="src/test/scala/resources/input_1.txt"
            val result = getCommands(path)
            // result must haveClass[List[String]]
            result must beAnInstanceOf[List[String]]
        }
    }
}


