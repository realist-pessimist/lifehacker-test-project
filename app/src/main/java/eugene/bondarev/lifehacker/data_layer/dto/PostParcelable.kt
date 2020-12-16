package eugene.bondarev.lifehacker.data_layer.dto

data class PostParcelable (
    val id: Int,
    val title: Body,
    val content: Body,
    val cat_cover: ImageSizes
)