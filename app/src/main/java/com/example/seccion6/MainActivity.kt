package com.example.seccion6

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvEjemplo: TextView = findViewById(R.id.eje)

        var etEjemplo: EditText = findViewById(R.id.editTextTextPersonName)

        etEjemplo.addTextChangedListener {
            if (etEjemplo.text.isEmpty()){
                etEjemplo.setError("Campo vacío")
            }
        }

        var autocomplete: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        var paises: Array<String> = resources.getStringArray(R.array.paises)
        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, paises)
        autocomplete.setAdapter(adapter)

        //Multi

        var multiAutoCompleteTextView: MultiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView)
        multiAutoCompleteTextView.setAdapter(adapter)
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())


        /**
         * CHIPS
         */
        var chipMigrupo: ChipGroup = findViewById(R.id.chipMigrupo)
        var chip: Chip

        for (i in 0..chipMigrupo.childCount-1){
            chip = chipMigrupo.getChildAt(i) as Chip

            // Chip toast al pulsar
            chip.setOnClickListener {
                var aux = it as Chip
                Toast.makeText(this, "${it.text} pulsado", Toast.LENGTH_SHORT).show()
            }

            // Chip cerrar
            chip.setOnCloseIconClickListener {
                chipMigrupo.removeView(it)
            }
        }

        //Chip nuevo
        var newChip = Chip(this)
        newChip.text = "Opcion"
        newChip.checkedIcon = ContextCompat.getDrawable(this, R.drawable.ic_mailicon)
        newChip.isCheckedIconVisible = true
        newChip.isChipIconVisible = false
        newChip.isChipIconVisible = true
        newChip.isCloseIconVisible = true
        newChip.isClickable = true
        newChip.isCheckable = true

        chipMigrupo.addView(newChip as View)
        newChip.setOnCloseIconClickListener {
            chipMigrupo.removeView(newChip as View)
        }


        /**
         * RadioButton y radioGroup
         */

        var rGroup: RadioGroup = findViewById(R.id.rGroup)
        rGroup.check(R.id.radioPlaya)

        /**
         * CheckBox
         */
        var checkSeguro: CheckBox = findViewById(R.id.checkBox)
        checkSeguro.isEnabled = false
        checkSeguro.isChecked = true

        /**
         * ToggleButton
         */
        var tbNormal: ToggleButton = findViewById(R.id.tbNormal)

        tbNormal.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) Toast.makeText(this, "Toggle normal activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Toggle desactivado", Toast.LENGTH_SHORT).show()
        }

        /**
         * Switch
         */
        var switchNormal: Switch = findViewById(R.id.switch1)

        switchNormal.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) Toast.makeText(this, "Switch normal activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Switch desactivado", Toast.LENGTH_SHORT).show()
        }


    }

    fun checkClicked(v: View){
        if (v is RadioButton) {
            var checked = v.isChecked

            when(v.id){
                R.id.radioPlaya -> {
                    if(checked) Toast.makeText(this, "Vamos a la playa", Toast.LENGTH_SHORT).show()
                }
                R.id.radioMontaña -> {
                    if(checked)  Toast.makeText(this, "VAmos a la montaña", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun checkBoxClicked(v: View){
        if (v is CheckBox) {
            var checked = v.isChecked

            when(v.getId()){
                R.id.checkBox -> {
                    if(checked) Toast.makeText(this, "Contratamos seguro de Viaje", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "NO contratamos seguro de Viaje", Toast.LENGTH_SHORT).show()
                }
                R.id.checkBox2 -> {
                    if(checked)  Toast.makeText(this, "El viaje se puede cancelar sin coste.", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "El NO viaje se puede cancelar sin coste.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}