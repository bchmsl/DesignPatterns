package com.space.designpatterns.structural

/** Pretend it is 3rd party library to [DisplayData] */
data class DisplayDataModel(val index: Int, val data: String)
class DisplayData(data: List<DisplayDataModel>) {
    init {
        data.forEach { datum ->
            displayData(datum)
        }
    }

    private fun displayData(datum: DisplayDataModel) {
        println("Data Displayed: ${datum.index} - ${datum.data}")
    }
}

/**  Application data generator (read from [Database]) */
data class DatabaseDataModel(val id: Int, val amount: Float)
class Database {
    fun retrieve(): List<DatabaseDataModel> {
        val list = mutableListOf<DatabaseDataModel>()
        list.add(DatabaseDataModel(0, 5f))
        list.add(DatabaseDataModel(1, 4.2f))
        list.add(DatabaseDataModel(2, 5.2f))
        list.add(DatabaseDataModel(3, 3f))
        list.add(DatabaseDataModel(4, 1.4f))
        list.add(DatabaseDataModel(5, 0.2f))
        list.add(DatabaseDataModel(6, 9.4f))
        list.add(DatabaseDataModel(7, 1.3f))
        return list.toList()
    }
}

/** Adapter Interface to depend on abstraction */
interface DatabaseDisplayAdapter<T> {
    fun submitList(list: List<T>) {
        convertData(list)
    }

    fun convertData(data: List<T>)
}

/** Adapter Implementation to convert [DatabaseDataModel] to [DisplayDataModel] */
class MyDatabaseDisplayAdapter : DatabaseDisplayAdapter<DatabaseDataModel> {

    override fun convertData(data: List<DatabaseDataModel>) {
        val displayDataList = data.map { DisplayDataModel(it.id, it.amount.toString()) }
        DisplayData(displayDataList)
    }

}

/** Data to Ui Implementation */
fun main() {
    val database = Database()
    val data = database.retrieve()
    val adapter = MyDatabaseDisplayAdapter()
    adapter.submitList(data)
}
