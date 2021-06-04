package academy.bangkit.project.capstone.vaccinekit.regisforuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.project.capstone.vaccinekit.databinding.FragmentRegisUserBinding
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisUserFragment : Fragment() {

    private var _binding: FragmentRegisUserBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            insertVaccine()
        }
    }

    private fun insertVaccine() {
        val nik = binding.edtNik.text.toString()
        val name = binding.edtName.text.toString()
        val ttl = binding.edtTtl.text.toString()
        val address = binding.edtAddress.text.toString()
        val first = binding.edtFirstVaccine.text.toString()
        val second = binding.edtSecondVaccine.text.toString()
        val status = binding.edtStatusVaccine.text.toString()

        val user = hashMapOf(
            "nik" to nik,
            "name" to name,
            "ttl" to ttl,
            "address" to address,
            "photo" to nik,
            "barcode" to "${nik}${getRandomString(3)}",
            "password" to "123456",
            "firstVaccineDate" to first,
            "secondVaccineDate" to second,
            "vaccineStatus" to status
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                moveToUpload(nik)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun moveToUpload(string: String) {
        val intent = Intent(getActivity(), UploadActivity::class.java)
        intent.putExtra(UploadActivity.EXTRA_DATA, string)
        getActivity()?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}