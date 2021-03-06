package academy.bangkit.project.capstone.vaccinekit

import academy.bangkit.project.capstone.vaccinekit.auth.LoginActivity
import academy.bangkit.project.capstone.vaccinekit.databinding.ActivityMainBinding
import academy.bangkit.project.capstone.vaccinekit.regisforuser.UserRegisterActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPermission()

        setSupportActionBar(binding.appBarMain.toolbar)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.nav_lost
        ), binding.drawerLayout)

        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))

        binding.navView.menu.findItem(R.id.nav_insert).setCheckable(false)
        binding.navView.menu.findItem(R.id.nav_insert).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.nav_insert ->{
                    binding.drawerLayout.close()
                    val Intent = Intent(this, UserRegisterActivity::class.java)
                    startActivity(Intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.signOut) {
            showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 1
            )
        }
    }

    override fun onBackPressed() {
        showDialog()
    }

    private fun showDialog() {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Warning....!")
            .setContentText("Are you sure to logout?")
            .setConfirmText("Yes")
            .setConfirmClickListener { sDialog ->
                mAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java)) }
            .setCancelButton(
                "No"
            ) { sDialog ->
                sDialog.dismissWithAnimation() }
            .show()
    }
}