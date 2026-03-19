package org.coderic.protective.mobile.data

import android.content.Context
import android.content.SharedPreferences
import org.coderic.protective.mobile.model.datos.Device
import org.coderic.protective.mobile.model.datos.Gender
import org.coderic.protective.mobile.model.datos.Pet
import org.json.JSONObject
import androidx.core.content.edit

const val ARCHIVE_PREFERENCE = "almacenamiento"
class MacronutrientPreferences (context: Context ) : Almacenamiento {

    private val preferences: SharedPreferences = context.getSharedPreferences( ARCHIVE_PREFERENCE, Context.MODE_PRIVATE )
    override fun savePet( pet: Pet ) {
        preferences.edit {
            val petJson = JSONObject()
                .put("name", pet.name)
                .put("typePet", pet.typePet)
                .put("age", pet.age)
                .put("weight", pet.weight)
                .put("height", pet.height)
                .put("color", pet.color)
                .put("description", pet.description)
                .put("image", pet.image)
                .put("id", pet.id)
                .put("gender", pet.gender.type)
            putString("pet${pet.id}", petJson.toString())
            putString("id", pet.id.toString())
        }
    }

    override fun saveDevice( device: Device ) {
        TODO("Not yet implemented")
    }

    override fun getPet(): Pet? {
        val petId = preferences.getString("id", "")
        if ( petId == "" ) return null
        val petString = preferences.getString("pet$petId", "")
        val petJSON = JSONObject( petString!! )

        return Pet(
            name = petJSON.getString("name"),
            gender = if (petJSON.getInt("gender") == 0) Gender.MAN else Gender.FEMALE,
            typePet = petJSON.getString("typePet"),
            age = petJSON.getInt("age"),
            weight = petJSON.getDouble("weight"),
            height = petJSON.getInt("height"),
            color = petJSON.getString("color"),
            description = petJSON.getString("description"),
            image = petJSON.getString("image"),
            id = petJSON.getLong("id")
        )
    }

    override fun deletePet( pet: Pet ) {
        preferences.edit {
            putString("pet${pet.id}", null)
            putString("id", null)
        }
    }

    override fun getDevice(): Device {
        return Device(
            preferences.getString("device_name", "")!!,
            false,
            0
        )
    }
}