package com.example.storyapp.ui.primary.add

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.storyapp.R
import com.example.storyapp.data.network.ApiConfig
import com.example.storyapp.data.response.upload.FileUploadResponse
import com.example.storyapp.databinding.FragmentAddStoryBinding
import com.example.storyapp.ui.authentication.login.LoginViewModel
import com.example.storyapp.ui.primary.camera.CameraActivity
import com.example.storyapp.ui.primary.camera.CameraActivity.Companion.CAMERAX_RESULT
import com.example.storyapp.utils.PrimaryViewModelFactory
import com.example.storyapp.utils.Result
import com.example.storyapp.utils.reduceFileImage
import com.example.storyapp.utils.uriToFile
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class AddStoryFragment : Fragment() {

    private val factory: PrimaryViewModelFactory by lazy {
        PrimaryViewModelFactory.getInstance(requireActivity())
    }

    private val viewModel: AddStoryViewModel by viewModels {
        factory
    }

    private var _binding: FragmentAddStoryBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCamera.setOnClickListener{
            startCameraX()
        }

        binding.btnGalery.setOnClickListener{
            startGallery()
        }

        binding.btnUpload.setOnClickListener {
            uploadImage()
        }

    }

    private fun startCameraX(){
        val intent = Intent(requireActivity(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireActivity()).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            // desc
            val description = binding.tvInputDesc.text.toString() ?: "Nothing"

            val requestBody = description.toRequestBody("text/plain".toMediaType())
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo",
                imageFile.name,
                requestImageFile
            )

            lifecycleScope.launch {
                viewModel.postStory(multipartBody,requestBody).observe(viewLifecycleOwner){result->
                    when(result) {
                        is Result.Loading -> {
                            binding.progressBarAddStory.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBarAddStory.visibility = View.GONE
                            Toast.makeText(context, "Upload Success", Toast.LENGTH_LONG).show()
                            view?.findNavController()?.navigate(R.id.action_addStoryFragment_to_listStoryFragment)
                        }
                        is Result.Error -> {
                            binding.progressBarAddStory.visibility = View.GONE
                            Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
                        }

                        else -> {
                            Toast.makeText(context, "Something Error", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        } ?: showToast(getString(R.string.empty_image_warning))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPreview.setImageURI(it)
        }
    }

}