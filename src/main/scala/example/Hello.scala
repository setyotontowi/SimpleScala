package example
import java.sql.{Connection,DriverManager};

object Hello extends Greeting with App {

  var run:Boolean = true
  while(run) {
    println("\n Scala Basic CRUD")
    println("1. Show All Data")
    println("2. Search Data")
    println("3. Insert Data")
    println("4. Modify Data")
    println("5. Delete Data")
    println("6. Exit")
    print("Pilih :")
    var a = scala.io.StdIn.readLine()

    a match {
      case "1" =>
        DatabaseHelper.getData()
      case "2" =>
        print("NIM : ");
        val nim: Int = scala.io.StdIn.readLine().toInt
        DatabaseHelper.getData(nim)
      case "3" =>
        print("NIM : ");
        val nim: Int = scala.io.StdIn.readLine().toInt
        print("Nama : ");
        val nama: String = scala.io.StdIn.readLine()
        print("Tahun : ");
        val tahun: Int = scala.io.StdIn.readLine().toInt
        DatabaseHelper.setData(nim, nama, tahun)
      case "4" =>
        print("NIM : ");
        val nim: Int = scala.io.StdIn.readLine().toInt
        DatabaseHelper.setData(nim)
      case "5" =>
        print("NIM : ");
        val nim: Int = scala.io.StdIn.readLine().toInt
        DatabaseHelper.deleteData(nim)
      case "6" =>
        println("Bye")
        run = false
        System.exit(0)
      case default => println("Doesn't Match!")
    }
  }
}

trait Greeting {
  lazy val greeting: String = "hello"
}
