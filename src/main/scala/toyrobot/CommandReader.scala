package toyrobot.commandreader
import scala.io.Source

object CommandReader {
    
    def main(args: Array[String]): Unit = {   
        getCommands("src/main/scala/resources/commands.txt")
    }

    def getCommands(path:String): List[String] = {
        println(path)
        val lines = Source.fromFile(path).getLines()
        
        for(l<-lines) println(l)

        println("done")
        List("1")
 
    }
}

