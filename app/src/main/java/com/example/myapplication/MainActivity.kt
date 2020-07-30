package com.example.myapplication

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.myapplication.interfaces.MainInterface
import com.example.myapplication.interfaces.MainPInterface
import com.example.myapplication.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, MainInterface {
    var TAKE_PICTURE = 1
    var presenter: MainPInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume(){
        super.onResume()
        //fechaNac
        fechaNac.setOnFocusChangeListener { view, b ->
            if (view.isFocused){
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(this, this, year, month, day)
                dpd.show()
            }
        }

        btn.setOnClickListener{
            var num = numEmpleado.text.toString()
            var name = nombreEmpleado.text.toString()
            var date = fechaNac.text.toString()

            if(num.equals("")){
                numEmpleado.setError("Campo obligatorio")
            }else if(name.equals("")){
                nombreEmpleado.setError("Campo obligatorio")
            }else if (date.equals("")){
                fechaNac.setError("Campo obligatorio")
            }else{
                //Continuar (Capturar documento cámara)
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(packageManager)?.also {
                        startActivityForResult(takePictureIntent, TAKE_PICTURE)
                    }
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
            var pic = data!!.extras!!.get("data") as Bitmap
            var baos = ByteArrayOutputStream()
            pic.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var b = baos.toByteArray()
            var b64 = Base64.encodeToString(b, Base64.DEFAULT)

            val map = HashMap<String, String>()
            map.put("numEmpleado", numEmpleado.text.toString())
            map.put("nombreEmpleado", nombreEmpleado.text.toString())
            map.put("fechaNac", fechaNac.text.toString())
            map.put("dirEmpleado", dirEmpleado.text.toString())
            map.put("picture", b64)

            var json = JSONObject(map as Map<*, *>)

            saveData(json)
        }
    }

    private fun saveData(json: JSONObject) {
        presenter!!.storeData(json, this)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        fechaNac.setText("$day/${month + 1}/$year")
    }

    override fun savedData(i: Int) {
        if (i == 1){
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Ocurrió algo, por favor, intenta más tarde", Toast.LENGTH_LONG).show()
        }
    }
}