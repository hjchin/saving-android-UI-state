package world.trav.savinguistate.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test_fragment2.view.*
import world.trav.savinguistate.viewModel.MainViewModel
import world.trav.savinguistate.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TestFragment2.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TestFragment2.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TestFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FragmentInterface? = null
    private lateinit var model : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        model = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view  = inflater.inflate(R.layout.fragment_test_fragment2, container, false)

        view.nextButton.setOnClickListener {
            listener?.onNextPageClick(this)
        }

        view.edit.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus && !view.edit.text.isEmpty()){
                model.setEditText2(view.edit.text.toString())
            }
        }

        model.getEditText2().value?.let{  it ->
            view.edit.setText(it)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInterface) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentInterface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        const val TAG = "testFragment2"
    }
}
