import org.specs2.mutable.Specification
import toyrobot.CommandReader.{getCommands}
class CommandReaderSpec extends Specification {
    "CommandReader" should {
        "return a commands as type List[String] " in {
            val path ="src/test/scala/resources/input.txt"
            val result = getCommands(path)
            result must haveClass[List[String]]
        }
    }
}


