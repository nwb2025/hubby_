package com.example.core



class RepoImp (/*private val hDao: Dao?*/ )
{
    /*companion object
    {
        @Volatile
        var instance : Repo? = null

        fun getRepo(hDao: Dao?) : Repo?
        {
            instance ?:
            synchronized(Repo::class)
            {
                instance =
                        RepoImp(hDao)
            }
            return instance
        }
    }

    override fun getAll(): LiveData<List<Habit>>? = hDao?.getAll()

    override fun getByType(type:String) : LiveData<List<Habit>>? = hDao?.getHabitsByType(type)

    override fun getHabitsFromServer() : LiveData<List<HabitResponse>>  = retofitInstance.api.getHabits(Constants.API_KEY)

    override suspend fun insertHabit(habit: Habit)  = hDao?.addHabit(habit)

    override suspend fun putHabit(habit: HabitResponse) {
        retofitInstance.api.putHabit(Constants.API_KEY, habit)
    }

    override suspend fun deleteHabit(habit: Habit){
        hDao?.deleteHabit(habit)
    }
    override suspend fun deleteHabitServer(uid:String) {
        retofitInstance.api.deleteHabit(Constants.API_KEY, uid)
    }*/

}