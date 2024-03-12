package org.coderic.protective.mobile.model.datos

import androidx.annotation.DrawableRes
import org.coderic.protective.mobile.R


data class Pet(
    val name: String = "",
    val gender: Gender = Gender.FEMALE,
    val typePet: String = "",
    var age : Int = 0,
    var weight: Float = 0f,
    var height: Int = 0,
    val color: String = "",
    var description: String = "",
    var image: String = ""
)
enum class Gender( @DrawableRes val type: Int ) {
    MAN( R.drawable.male ),
    FEMALE(R.drawable.female );
}
