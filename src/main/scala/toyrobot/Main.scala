import toyrobot.{CommandReader, CommandExecuter}

object Main {
  def main(args: Array[String]): Unit = {   
    val commandsPath = "src/main/scala/resources/input.txt"
    println("\n Welcome to ToyRobot")
    println(s"Input file: ${commandsPath} \n")
    
    if (!CommandReader.validate(commandsPath)){
      println(s"Invalid Input from ${commandsPath}. \n Lines must start with one of the following commands: PLACE, MOVE, LEFT, RIGHT, REPORT")
    } else { 
      val commands = CommandReader.getCommands(commandsPath)
      val results = CommandExecuter.execute(commands)
      results.map(_.log).foreach(println)
    } 
  }
}