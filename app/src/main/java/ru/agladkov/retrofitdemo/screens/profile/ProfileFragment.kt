package ru.agladkov.retrofitdemo.screens.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vk.api.sdk.requests.VKRequest
import dagger.hilt.android.AndroidEntryPoint
import ru.agladkov.retrofitdemo.R
import ru.agladkov.retrofitdemo.extensions.createBitmapFromResult

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                addPhotoFromIntent()
            } else {
                Toast.makeText(requireContext(), getString(R.string.grant_permission), Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.postRequestButtonView).setOnClickListener { sendPostRequest() }
        view.findViewById<AppCompatButton>(R.id.fileRequestButtonView).setOnClickListener { loadFileFromDevice() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMG_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            val imageBitmap = data.createBitmapFromResult(requireActivity())
            viewModel.sendFileRequest(image = imageBitmap!!)
        }
    }

    private fun sendPostRequest() {
        viewModel.sendPostRequest()
    }

    private fun loadFileFromDevice() {
//        when (ContextCompat.checkSelfPermission(requireContext(), CAMERA_PERMISSION)) {
//            PackageManager.PERMISSION_GRANTED -> addPhotoFromIntent()
//            else -> requestPermissionLauncher.launch(CAMERA_PERMISSION)
//        }

        addPhotoFromIntent()
    }

    private fun addPhotoFromIntent() {
        val cameraIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE).takeIf { intent ->
            intent.resolveActivity(requireActivity().packageManager) != null
        }

        val galleryIntent = Intent(Intent.ACTION_PICK).apply { this.type = "image/*" }

        val intentChooser = Intent(Intent.ACTION_CHOOSER).apply {
            this.putExtra(Intent.EXTRA_INTENT, galleryIntent)
            cameraIntent?.let { intent ->
                this.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayListOf(intent).toTypedArray<Parcelable>())
            }
            this.putExtra(Intent.EXTRA_TITLE, resources.getString(R.string.gallery_title))
        }

        startActivityForResult(intentChooser, CODE_IMG_GALLERY)
    }

    companion object {
        private const val CODE_IMG_GALLERY = 111
        private const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }
}