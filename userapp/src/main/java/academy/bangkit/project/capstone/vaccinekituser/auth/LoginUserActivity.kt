package academy.bangkit.project.capstone.vaccinekituser.auth

import academy.bangkit.project.capstone.vaccinekituser.Helper.Constant
import academy.bangkit.project.capstone.vaccinekituser.Helper.PreferenceHelper
import academy.bangkit.project.capstone.vaccinekituser.MainActivity
import academy.bangkit.project.capstone.vaccinekituser.R
import academy.bangkit.project.capstone.vaccinekituser.databinding.ActivityLoginBinding
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel


class LoginUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModel()
    lateinit var sharedpref: PreferenceHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showHideBtn.setOnClickListener {
            if(binding.showHideBtn.background.equals(resources.getDrawable(R.drawable.show_pass,theme))){
                binding.pass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showHideBtn.background = resources.getDrawable(R.drawable.show_pass,theme)
                binding.showHideBtn.text = "show"
            } else{
                binding.pass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showHideBtn.background = resources.getDrawable(R.drawable.ic_baseline_remove_red_eye_24,theme)
                binding.showHideBtn.text = "hide"
            }
        }

        sharedpref = PreferenceHelper(this)

        binding.loginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        var nik = binding.user.editableText.toString()
        var pass = binding.pass.editableText.toString()
        lifecycleScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {
                viewModel.LoginUser(nik, pass).collectLatest {
                    saveSession(nik,pass)
                    onStart()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if(sharedpref.getBoolean(Constant.PREF_IS_LOGIN)){
            moveIntent()
        }
    }

    private fun moveIntent(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveSession (nik: String, password:String){
        sharedpref.put(Constant.PREF_NIK,nik)
//        sharedpref.put(Constant.PREF_PASSWORD,password)
        sharedpref.put(Constant.PREF_IS_LOGIN,true)
    }
}

