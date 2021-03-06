package academy.bangkit.project.capstone.vaccinekituser

import academy.bangkit.project.capstone.vaccinekituser.Helper.PreferenceHelper
import academy.bangkit.project.capstone.vaccinekituser.auth.LoginUserActivity
import academy.bangkit.project.capstone.vaccinekituser.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cn.pedant.SweetAlert.SweetAlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var sharedpref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPermission()

        setSupportActionBar(binding.appBarMain.toolbar)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home_user,
            R.id.nav_prof,
            R.id.nav_lose_band
        ), binding.drawerLayout)

        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))

        sharedpref = PreferenceHelper(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        showDialog()
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

    private fun showDialog() {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Warning....!")
            .setContentText("Are you sure to logout?")
            .setConfirmText("Yes")
            .setConfirmClickListener {
                sharedpref.clear()
                startActivity(Intent(this, LoginUserActivity::class.java))
            }
            .setCancelButton(
                "No"
            ) { it.dismissWithAnimation() }
            .show()
    }
}