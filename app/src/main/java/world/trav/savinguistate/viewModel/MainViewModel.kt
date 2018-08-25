package world.trav.savinguistate.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import world.trav.savinguistate.view.CacheableMutableLiveString


class MainViewModel(var app : Application) : AndroidViewModel(app) {

    private var editText1 = CacheableMutableLiveString("editText1",app)
    private var editText2 = CacheableMutableLiveString("editText2",app)
    private var editText3 = CacheableMutableLiveString("editText3",app)

    fun setEditText1(value : String){
        editText1.setValue(value)
    }

    fun getEditText1(): LiveData<String>{
        return editText1
    }

    fun setEditText2(value : String){
        editText2.setValue(value)
    }

    fun getEditText2() : LiveData<String>{
        return editText2
    }

    fun setEditText3(value : String){
        editText3.setValue(value)
    }

    fun getEditText3() : LiveData<String>{
        return editText3
    }

    fun submit(){
        CacheableMutableLiveString.clear()
    }
}