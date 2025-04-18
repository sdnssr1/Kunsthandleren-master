package com.example.kunsthandleren

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.kunsthandleren.DataSource.artists

object DataSource {
    val frames: List<FrameType> = FrameType.values().toList()
    val photoSizes: List<PhotoSize> = PhotoSize.values().toList()
    val frameSizes: List<FrameSize> = FrameSize.values().toList()
    val categories: List<Category> = Category.values().toList()
    val artists = listOf(
        Artist(3L, "Sofie", "Solberg"),
        Artist(4L, "Lars", "Sandvik"),
        Artist(5L, "Mina", "Ødegård"),
        Artist(6L, "Oskar", "Hansen"),
    )
    val naturePhotos: List<Photo> = listOf(
        Photo(1L, "Nature Image 1", R.drawable.image1, artists[1], Category.NATURE, 100f),
        Photo(2L, "Nature Image 2", R.drawable.image2, artists[1], Category.NATURE, 110f),
        Photo(3L, "Nature Image 3", R.drawable.image3, artists[1], Category.NATURE, 120f),
        Photo(4L, "Nature Image 4", R.drawable.image4, artists[1], Category.NATURE, 130f),
        Photo(5L, "Nature Image 5", R.drawable.image5, artists[1], Category.NATURE, 140f),
        Photo(6L, "Nature Image 6", R.drawable.image6, artists[1], Category.NATURE, 150f),
        Photo(7L, "Nature Image 7", R.drawable.image7, artists[1], Category.NATURE, 160f),
        Photo(8L, "Nature Image 8", R.drawable.image8, artists[1], Category.NATURE, 170f),
        Photo(9L, "Nature Image 9", R.drawable.image9, artists[1], Category.NATURE, 180f)
    )
    val superHeroPhotos: List<Photo> = listOf(
        Photo(1L, "Nature Image 1", R.drawable.android_superhero1, artists[2], Category.FOOD, 100f),
        Photo(2L, "Nature Image 2", R.drawable.android_superhero2, artists[2], Category.FOOD, 110f),
        Photo(3L, "Nature Image 3", R.drawable.android_superhero3, artists[2], Category.FOOD, 120f),
        Photo(4L, "Nature Image 4", R.drawable.android_superhero4, artists[2], Category.FOOD, 130f),
        Photo(5L, "Nature Image 5", R.drawable.android_superhero5, artists[2], Category.FOOD, 140f),
        Photo(6L, "Nature Image 6", R.drawable.android_superhero6, artists[2], Category.FOOD, 150f)
    )
    val animalPhotos: List<Photo> = listOf(
<<<<<<< HEAD
        Photo(4L, "animal Image 1", R.drawable.faye, artists[3], Category.DYR, 130f),
        Photo(5L, "animal Image 2", R.drawable.frankie, artists[3], Category.DYR, 140f),
        Photo(6L, "animal Image 3", R.drawable.koda, artists[3], Category.DYR, 150f),
        Photo(7L, "animal Image 4", R.drawable.leroy, artists[3], Category.DYR, 150f),
        Photo(8L, "animal Image 5", R.drawable.lola, artists[3], Category.DYR, 150f),
        Photo(9L, "animal Image 6", R.drawable.moana, artists[3], Category.DYR, 150f),
        Photo(10L, "animal Image 7", R.drawable.nox, artists[3], Category.DYR, 150f),
        Photo(11L, "animal Image 8", R.drawable.tzeitel, artists[3], Category.DYR, 150f)
=======
        Photo(1L, "Faye", R.drawable.faye, artists[3], Category.ANIMAL, 130f),
        Photo(2L, "Frankie", R.drawable.frankie, artists[3], Category.ANIMAL, 140f),
        Photo(3L, "Koda", R.drawable.koda, artists[3], Category.ANIMAL, 150f),
        Photo(4L, "Leroy", R.drawable.leroy, artists[3], Category.ANIMAL, 150f),
        Photo(5L, "Lola", R.drawable.lola, artists[3], Category.ANIMAL, 150f),
        Photo(6L, "Mona", R.drawable.moana, artists[3], Category.ANIMAL, 150f),
        Photo(7L, "Nox", R.drawable.nox, artists[3], Category.ANIMAL, 150f),
        Photo(8L, "Tz", R.drawable.tzeitel, artists[3], Category.ANIMAL, 150f),
        Photo(9L, "sheep", R.drawable.tzeitel, artists[3], Category.ANIMAL, 150f)

>>>>>>> main
    )
    val paintingPhotos: List<Photo> = listOf(
        Photo(4L, "Nature Image 4", R.drawable.paint1, artists[0], Category.PAINT, 130f),
        Photo(5L, "Nature Image 5", R.drawable.paint2, artists[0], Category.PAINT, 140f),
        Photo(6L, "Nature Image 6", R.drawable.paint3, artists[0], Category.PAINT, 150f),
        Photo(7L, "Nature Image 6", R.drawable.paint4, artists[0], Category.PAINT, 150f),
        Photo(8L, "Nature Image 6", R.drawable.paint5, artists[0], Category.PAINT, 150f),
        Photo(9L, "Nature Image 6", R.drawable.paint6, artists[0], Category.PAINT, 150f),
        Photo(10L, "Nature Image 6", R.drawable.paint7, artists[0], Category.PAINT, 150f),
        Photo(11L, "Nature Image 6", R.drawable.paint8, artists[0], Category.PAINT, 150f),
        Photo(11L, "Nature Image 6", R.drawable.paint9, artists[0], Category.PAINT, 150f)
    )
    val photos: List<Photo> = naturePhotos + superHeroPhotos + animalPhotos + paintingPhotos
}

data class Photo(
    val id: Long,
    val title: String = "",
    @DrawableRes
    val imageResId: Int,
    val artist: Artist,
    val category: Category,
    val price: Float = 0.0f
)

data class Artist(
    val id: Long,
    val name: String = "",
    val familyName: String = ""
)

enum class Category {
    NATURE(),
<<<<<<< HEAD
    FOOD(),
    DYR(),
=======
    SUPERHERO(),
    ANIMAL(),
>>>>>>> main
    PAINT()
}

enum class Filters {
    ARTIST(),
    CATEGORY()
}

enum class FrameType(
    val title: String,
    val extraPrice: Float,
    val color: Color,
    val imageRes: Int
) {
    WOOD("Wood", 10f, Color(0xFF8B4513), R.drawable.wood_texture),
    METAL("Metal", 15f, Color.Gray, R.drawable.metal_texture),
    NICE("Nice", 10f, Color(0xFFDAA520), R.drawable.nice_texture),
    GOLD("Gold", 10f, Color.Yellow, R.drawable.gold_texture),
}




enum class PhotoSize(val extraPrice: Float, val size: Int = 170, @StringRes val title: Int) {
    SMALL(0f, size=170, title = R.string.small),
    MEDIUM(130f, size=200, title = R.string.medium),
    LARGE(230f, size=250, title = R.string.large)
}

enum class FrameSize(val extraPrice: Float, val size: Int = 10){
    SMALL(0f, size=10),
    MEDIUM(25f, size=15),
    LARGE(45f, size=20)

}

val testArtist = Artist(-1L)
val testPhoto = Photo(id= -1L,imageResId=R.drawable.ic_launcher_background, artist=testArtist, category=Category.PAINT)

data class PurchaseItem(
    val photo: Photo = testPhoto,
    val size: PhotoSize = PhotoSize.SMALL,
    val frameType: FrameType = FrameType.WOOD,
    val frameSize: FrameSize = FrameSize.SMALL
) {
    fun getCost(): Float {
        return photo.price + size.extraPrice + frameType.extraPrice + frameSize.extraPrice
    }
}

