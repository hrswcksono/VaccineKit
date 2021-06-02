package academy.bangkit.project.capstone.vaccinekit.regisforuser

import academy.bangkit.project.capstone.vaccinekit.databinding.ActivityUploadBinding
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.random.Random

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private var imagePath: String? = null
    private var mStorageRef: StorageReference? = null

    companion object {
        const val EXTRA_UPLOAD = "EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mStorageRef = FirebaseStorage.getInstance().reference

        binding.btnUpload.setOnClickListener {
            upload(imagePath.toString())
        }

        binding.btnOpen.setOnClickListener {
            cameraIntent()
        }
    }

    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 101)
    }

    private fun upload(path: String){
        val file = Uri.fromFile(File(path))
        val meta = File(path)
        val nik = UploadActivityArgs.fromBundle(arguments as Bundle).nikuser
        val storageRef = mStorageRef?.child("photo_request_verif/${meta.name}")
        storageRef?.putFile(file)
            ?.addOnSuccessListener {
                Toast.makeText(this, "File already uploaded", Toast.LENGTH_SHORT).show()
//                finish()
            }?.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun resultCamera(data: Intent?) {
        val image = data?.extras?.get("data")
        val random = Random.nextInt(0, 999999)
        val name_file = "Camera$random"
        imagePath = persistImage(image as Bitmap, name_file)
        binding.imgView.setImageBitmap(BitmapFactory.decodeFile(imagePath))
    }

    private fun persistImage(bitmap: Bitmap, name: String): String {
        val filesDir = filesDir
        val imageFile = File(filesDir, "${name}.png")
        val image_path = imageFile.path
        val os: OutputStream?
        try {
            os = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
            os.flush()
            os.close()
        } catch (e: Exception){
            Log.e("TAG", "persistImage: ${e.message.toString()} ", e )
        }
        return image_path
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 101) {
                resultCamera(data)
            }
        }
    }

}