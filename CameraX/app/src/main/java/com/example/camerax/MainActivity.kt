package com.example.camerax

import android.content.pm.PackageManager
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.concurrent.Executor

//const val PERMISSION_REQUEST_CAMERA = 1234

class MainActivity : AppCompatActivity(), Executor {

    override fun execute(command: Runnable?) {
        command?.run()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // Start your work for camera
            textureView.post { // Creating a separate thread in which implementation of camera should start only
                // after texture view has been properly inflated
                startCamera()
            }
        } else {
            // Ask for permission
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.CAMERA),
                1234
            )
        }

    }

    private fun startCamera() {

        bindCameraUseCases()

        // button to save image
        btnSave.setOnClickListener {
            val file = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
            imageCapture?.takePicture(file, this, object : ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    // Toast.makeText( this@MainActivity, "Image Captured ${file.absolutePath}", Toast.LENGTH_LONG
                    //).show()// We cannot print toast here, bcoz this is not Main thread
                    Log.i("IMAGECAPTURE", "Image Captured ${file.absolutePath}")
                }

                override fun onError(
                    imageCaptureError: ImageCapture.ImageCaptureError,
                    message: String,
                    cause: Throwable?
                ) {
                    //Toast.makeText( this@MainActivity, "Error Capturing $message", Toast.LENGTH_LONG ).show()
                    Log.i("IMAGECAPTURE", "Error Capturing $message")
                }
            })
        }

        // button to swap lens
        btnSwap.setOnClickListener {
            Log.i("LENSSWAPPED", "Lens Swapped")
            lensFacing = if (CameraX.LensFacing.FRONT == lensFacing) {
                CameraX.LensFacing.BACK
            } else {
                CameraX.LensFacing.FRONT
            }
            try {
                CameraX.getCameraControl(lensFacing)
                bindCameraUseCases()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//            preview.setOnPreviewOutputUpdateListener {
//                textureView.setSurfaceTexture(it.surfaceTexture)
//            }
//            CameraX.bindToLifecycle(this, preview, imageCapture)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        // Get the x & Y coordinates of center
        val centerX = textureView.width / 2f
        val centerY = textureView.height / 2f

        val rotationDegrees = when (textureView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }

        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        textureView.setTransform(matrix)
    }

    private var lensFacing = CameraX.LensFacing.BACK
    private var imageCapture: ImageCapture? = null

    private fun bindCameraUseCases() {
        CameraX.unbindAll()

        val previewConfig = PreviewConfig.Builder().apply {
//            setTargetResolution(Size(1080, 1080))java.lang.IllegalArgumentException: Cannot use both setTargetResolution and setTargetAspectRatio on the same config.
            setTargetAspectRatio(AspectRatio.RATIO_16_9)
            // These 2 methods are required to set the aspect ration & resolution of the screen
            // you want to display on. Different devices have different aspect ratio and resolution
            // depending on their screen sizes. This is just a sample, so here we're using target aspect
            // ratio as 16:9 & target resolution 1080/1080. In real-life apps, you have to calculate
            // the aspect ratio and resolution depending on the screen size and user modifications.
            setLensFacing(lensFacing)// To set default lens
        }.build()

        val preview = Preview(previewConfig)

        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(AspectRatio.RATIO_16_9)// optional
            setCaptureMode(ImageCapture.CaptureMode.MAX_QUALITY)
            setLensFacing(lensFacing)//So that image can be captured even after changing lens
        }.build()

        imageCapture = ImageCapture(imageCaptureConfig)

        preview.setOnPreviewOutputUpdateListener {
            // textureView.parent gives us the whole screen
            val parent = textureView.parent as ViewGroup// get current root of the textureView
            parent.removeView(textureView)//remove the current view(TextureView) from parent(whole screen)
            parent.addView(textureView, 0)// add a new textureView to parent(screen)
            //In cases where you stack views on top of each other, index value will be required in that case
            // as the new view to be added should be at the top of stack. In our case, this doesn't has
            // much significance here bcoz we have zero textureViews when we're adding this one
            updateTransform()// make this function for handling rotation & other transformations if there
//            textureView.surfaceTexture = it.surfaceTexture

            textureView.setSurfaceTexture(it.surfaceTexture)
            // The preview we're getting, we need to update that preview's surface texture to our own textureView
        }

        // Bind the preview along with textureView to our camera.
        // we do not have to handle lifecycle of the camera. It'll be handled on its own.
        // We do this to ensure that our app doesn't uses camera when its in the background.
        // Our app can only use camera when its in foreground
        CameraX.bindToLifecycle(
            this,
            preview,
            imageCapture
        )// Apply declared configs to CameraX using the same lifecycle owner

    }
}
