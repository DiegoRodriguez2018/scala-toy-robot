package toyrobot

sealed trait Command
case class Place(position:Position, direction:Direction) extends Command
object Move extends Command
object Left extends Command
object Right extends Command
object Report extends Command