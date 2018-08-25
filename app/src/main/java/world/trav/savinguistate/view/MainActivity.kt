package world.trav.savinguistate.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import world.trav.savinguistate.*
import world.trav.savinguistate.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), FragmentInterface {

    private lateinit var model : MainViewModel
    private var currentFragment = TestFragment1.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        if(supportFragmentManager.findFragmentByTag(currentFragment) == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment, TestFragment1(), TestFragment1.TAG).commit()
        }

        model.getEditText1().observe(this, Observer {
            Log.i("log","edit text 1 has been changed = $it")
        })

        model.getEditText2().observe(this, Observer {
            Log.i("log", "edit text 2 has been changed = $it")
        })

        model.getEditText3().observe(this, Observer {
            Log.i("log","edit text 3 has been changed = $it")
        })
    }

    override fun onNextPageClick(fragment : Fragment){

        if(fragment is TestFragment1){
           supportFragmentManager.beginTransaction().replace(R.id.fragment, TestFragment2(), TestFragment2.TAG).addToBackStack(null).commit()
            currentFragment = TestFragment2.TAG
        }

        if(fragment is TestFragment2){
            supportFragmentManager.beginTransaction().replace(R.id.fragment, TestFragment3(), TestFragment3.TAG).addToBackStack(null).commit()
            currentFragment = TestFragment3.TAG
        }
    }

    override fun onDoneClick() {
        model.submit()
    }
}
