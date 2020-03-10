package toyrobot

sealed trait Command
case class Place(position:Position, direction:Direction) extends Command
case object Move extends Command
case object Left extends Command
case object Right extends Command
case object Report extends Command