package eugene.bondarev.lifehacker.data_layer.dto

/**
 * This class can be initialized using the JSON file serialization library,
 * Here is one of them "Jackson Support for Kotlin" https://www.baeldung.com/jackson-kotlin
 */
//TODO(you can change the DTO class)
data class PostParcelable (
    val id: Int,
    val title: Body,
    val content: Body,
    val cat_cover: ImageSizes
)