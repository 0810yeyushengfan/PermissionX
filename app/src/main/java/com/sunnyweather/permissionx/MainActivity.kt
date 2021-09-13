package com.sunnyweather.permissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Permission.yeyu.PermissionX


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeCallBtn=findViewById(R.id.makeCallBtn) as Button
        makeCallBtn.setOnClickListener{
            PermissionX.request(this, Manifest.permission.CALL_PHONE){allGranted,deniedList->
                if(allGranted){
                    call()
                }else{
                    Toast.makeText(this,"You denied $deniedList",Toast.LENGTH_SHORT).show()
                }
            }
        }
        val manyRequest=findViewById(R.id.manyRequest) as Button
        manyRequest.setOnClickListener{
            PermissionX.request(this,Manifest.permission.CALL_PHONE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS){allGranted,deniedList->
                if(allGranted){
                    Toast.makeText(this,"All permissions are granted",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"You denied $deniedList",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun call(){
        try{
            val intent= Intent(Intent.ACTION_CALL)
            intent.data= Uri.parse("tel:1008611")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}
