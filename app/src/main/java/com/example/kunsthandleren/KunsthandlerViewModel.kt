package com.example.kunsthandleren

import androidx.lifecycle.ViewModel

class KunsthandlerViewModel : ViewModel() {

    // Hardkodet liste med bilder
    private val _bilder = listOf(
        Bilde(1, "Hund", Kategori.Dyr),
        Bilde(2, "Katt", Kategori.Dyr),
        Bilde(3, "Pizza", Kategori.Mat),
        Bilde(4, "Taco", Kategori.Mat)
    )

    fun hentBilder(kategori: Kategori): List<Bilde> {
        return _bilder.filter { it.kategori == kategori }
    }
}
