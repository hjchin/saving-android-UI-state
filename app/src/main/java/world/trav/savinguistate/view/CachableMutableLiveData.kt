package world.trav.savinguistate.view

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import android.util.Log


class CacheableMutableLiveString(var name : String, var context : Context) : MutableLiveData<String>() {

    init{
        CacheableMutableLiveString.init(context)
        restore()
    }

    override fun postValue(value: String) {
        super.postValue(value)
        cache(name, value)
    }

    override fun setValue(value: String) {
        super.setValue(value)
        cache(name, value)
    }

    private fun restore(){
        super.setValue(retrieve(name))
    }


    companion object {
        private var sharedPreference : SharedPreferences? = null

        private fun getEditor() : SharedPreferences.Editor{
            return sharedPreference!!.edit()
        }

        private fun cache(name : String, value : String){
            getEditor().putString(name, value).apply()
            Log.i("log","cache name=$name, value=$value")
        }

        private fun retrieve(name : String) : String{
            val value = sharedPreference?.getString(name,"") ?: ""
            Log.i("log","retrieve name=$name, value=$value")
            return value
        }

        fun init(context : Context){
            synchronized(this){
                if(sharedPreference == null) {
                    sharedPreference = context.getSharedPreferences("CachableMutableLiveData", 0)
                }
            }
        }

        fun clear(){
            sharedPreference?.edit()?.clear()?.apply()
        }
    }
}