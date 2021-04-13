package com.example.permissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

const val PERMISSION_REQUEST_PHONE_CALL = 0

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    //    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNetworkState.setOnClickListener {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetwork
            val isConnected = networkInfo != null
            if (isConnected)
                tvstatus.text = "CONNECTED"
            else
                tvstatus.text = "DISCONNECTED"
        }

        btndial.setOnClickListener {
            makePhoneCallAfterPermission(it)
        }

    }

    private fun makePhoneCallAfterPermission(view: View) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            makePhoneCall()
        } else {
            requestCallPermission(view)
        }
    }

    private fun requestCallPermission(view: View) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CALL_PHONE
            )
        ) {
            val snack = Snackbar.make(
                view,
                "We need your permission to make a call, when asked please give the permission",
                Snackbar.LENGTH_INDEFINITE
            )
            snack.setAction("OK", View.OnClickListener {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    PERMISSION_REQUEST_PHONE_CALL
                )
            })
            snack.show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSION_REQUEST_PHONE_CALL
            )
        }
    }

    private fun makePhoneCall() {
        val mobile = etmobile.text.toString()
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:$mobile")
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_PHONE_CALL) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            } else {
                Toast.makeText(this, "Permission denied to make phone call.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}