package example

import java.sql.{Connection, DriverManager, PreparedStatement}

object DatabaseHelper {

  var URL  = "jdbc:mysql://localhost:3306/test"
  var DRIVER = "com.mysql.jdbc.Driver"
  var USERNAME = "artaisen"
  var PASSWORD = "2504"
  var connection:Connection = _

  def DatabaseHelper(): Unit ={
    connect()
  }
  def connect(): Unit ={
    try {
      Class.forName(DRIVER)
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)
    } catch {
      case e: Exception => e.printStackTrace
    }
  }

  /**
   * Get All Data
   */
  def getData(): Unit ={
    connect()
    val statement = connection.createStatement
    val rs = statement.executeQuery("SELECT * FROM mahasiswa")
    System.out.format("| %-8s | %-32s | %-5s | %n", "NIM", "Nama", "Tahun")
    System.out.println("=======================================================")
    while (rs.next) {
      val nim = rs.getString("nim")
      val nama = rs.getString("nama")
      val tahun = rs.getString("tahun")

      println("| %-8s | %-32s | %-5s |".format(nim, nama, tahun))
    }
  }

  /**
   * Search Data
   * @param nim
   */
  def getData(nim:Int): Unit ={
    connect()
    val statement = connection.createStatement
    val rs = statement.executeQuery("SELECT * FROM mahasiswa WHERE nim = %s".format(nim))

    System.out.format("| %-8s | %-32s | %-5s | %n", "NIM", "Nama", "Tahun")
    System.out.println("=======================================================")
    while (rs.next) {
      val nim = rs.getString("nim")
      val nama = rs.getString("nama")
      val tahun = rs.getString("tahun")

      println("| %-8s | %-32s | %-5s |".format(nim, nama, tahun))
    }
  }

  /**
   * Input Data
   * @param nim nim Mahasiswa
   * @param nama nama Mahasiswa
   * @param tahun tahun angkatan
   */
  def setData(nim:Int, nama:String, tahun:Int): Unit ={
    connect()
    val query:String = "INSERT INTO mahasiswa(nim, nama, tahun) "+"VALUES (?, ?, ?)"
    val mahasiswa:PreparedStatement = connection.prepareStatement(query);
    mahasiswa.setInt(1, nim)
    mahasiswa.setString(2, nama)
    mahasiswa.setInt(3, tahun)

    mahasiswa.execute()
  }

  /**
   * Update Data
   * @param nim mahasiswa
   */
  def setData(nim:Int): Unit={
    getData(nim);
    print("Nama :"); val nama:String = scala.io.StdIn.readLine()
    print("Tahun :"); val tahun:Int = scala.io.StdIn.readLine().toInt

    val query:String = "UPDATE mahasiswa "+"SET nama = ?, tahun = ? WHERE nim = ?"
    val mahasiswa:PreparedStatement = connection.prepareStatement(query);
    mahasiswa.setString(1, nama)
    mahasiswa.setInt(2, tahun)
    mahasiswa.setInt(3, nim)

    mahasiswa.execute()

  }

  /**
   * Delete Data
   * @param nim  Mahasiswa
   */
  def deleteData(nim:Int): Unit ={
    connect()
    val query:String = "DELETE FROM mahasiswa WHERE nim=?"
    val mahasiswa:PreparedStatement = connection.prepareStatement(query)
    mahasiswa.setInt(1, nim)
    mahasiswa.execute()
  }

}
