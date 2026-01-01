@file:Suppress("ktlint:standard:max-line-length")

package com.example.saloneculture.data.posts.impl

import com.example.saloneculture.R
import com.example.saloneculture.model.Metadata
import com.example.saloneculture.model.Paragraph
import com.example.saloneculture.model.ParagraphType
import com.example.saloneculture.model.Post
import com.example.saloneculture.model.PostAuthor
import com.example.saloneculture.model.PostsFeed
import com.example.saloneculture.model.Publication

/**
 * Culture articles shown in Salone Culture App.
 *
 * This file intentionally uses an in-memory dataset (no network) to keep the app reliable for
 * academic submission and offline demos.
 */

private val cultureTeam = PostAuthor("Salone Culture Team", "https://en.wikipedia.org/wiki/Sierra_Leone")
private val heritageDesk = PostAuthor("Heritage Desk", "https://en.wikipedia.org/wiki/History_of_Sierra_Leone")
private val foodDesk = PostAuthor("Food & Lifestyle", "https://en.wikipedia.org/wiki/Sierra_Leonean_cuisine")

private val publication = Publication(
    name = "Salone Culture",
    logoUrl = "https://developer.android.com/images/brand/Android_Robot.png",
)

private fun meta(author: PostAuthor, date: String, mins: Int) =
    Metadata(author = author, date = date, readTimeMinutes = mins)

private fun p(type: ParagraphType, text: String) = Paragraph(type, text)

private val post1 = Post(
    id = "culture_1",
    title = "Traditional Music: Bubu, Palm-Wine, and Modern Afrobeats",
    subtitle = "How rhythm connects communities across Sierra Leone",
    url = "https://en.wikipedia.org/wiki/Music_of_Sierra_Leone",
    publication = publication,
    metadata = meta(cultureTeam, "2026-01-01", 4),
    imageId = R.drawable.post_1,
    imageThumbId = R.drawable.post_1_thumb,
    paragraphs = listOf(
        p(ParagraphType.Text, "Sierra Leone’s music blends deep tradition with modern sounds. From ceremonial rhythms to popular street performances, music remains a key way to tell stories and celebrate community life."),
        p(ParagraphType.Header, "Key styles"),
        p(ParagraphType.Bullet, "Bubu: a traditional style often linked with communal celebrations."),
        p(ParagraphType.Bullet, "Palm-wine music: relaxed guitar-driven sounds associated with social gatherings."),
        p(ParagraphType.Bullet, "Contemporary Afrobeats: modern production influenced by regional and global trends."),
        p(ParagraphType.Text, "In many communities, music supports cultural identity by preserving language, values, and shared history through lyrics and performance."),
    ),
)

private val post2 = Post(
    id = "culture_2",
    title = "Dance and Performance: The Language of Movement",
    subtitle = "From traditional dances to stage performance",
    url = "https://en.wikipedia.org/wiki/Culture_of_Sierra_Leone",
    publication = publication,
    metadata = meta(cultureTeam, "2026-01-01", 3),
    imageId = R.drawable.post_2,
    imageThumbId = R.drawable.post_2_thumb,
    paragraphs = listOf(
        p(ParagraphType.Text, "Dance is a major cultural expression in Sierra Leone. Movements often carry meaning—honoring elders, welcoming visitors, or marking important life events."),
        p(ParagraphType.Header, "Where you will see dance"),
        p(ParagraphType.Bullet, "Community ceremonies and celebrations"),
        p(ParagraphType.Bullet, "Cultural festivals and national events"),
        p(ParagraphType.Bullet, "Schools, youth groups, and creative arts programs"),
        p(ParagraphType.Text, "Costume, rhythm, and storytelling work together to create performances that are both entertaining and educational."),
    ),
)

private val post3 = Post(
    id = "culture_3",
    title = "Languages: Krio as a Bridge, Plus Many Local Languages",
    subtitle = "Communication, identity, and everyday life",
    url = "https://en.wikipedia.org/wiki/Krio_language",
    publication = publication,
    metadata = meta(cultureTeam, "2026-01-01", 3),
    imageId = R.drawable.post_3,
    imageThumbId = R.drawable.post_3_thumb,
    paragraphs = listOf(
        p(ParagraphType.Text, "Sierra Leone is linguistically diverse. Krio is widely used as a common language, while many local languages remain strong in family and community settings."),
        p(ParagraphType.Header, "Why language matters"),
        p(ParagraphType.Bullet, "It preserves heritage through proverbs, songs, and oral history"),
        p(ParagraphType.Bullet, "It supports social connection across regions and ethnic groups"),
        p(ParagraphType.Text, "In cultural practice, language is more than words—it is a living record of identity and community."),
    ),
)

private val post4 = Post(
    id = "culture_4",
    title = "Heritage Sites: Bunce Island and the Freetown Peninsula",
    subtitle = "Places that help explain the past and inspire the future",
    url = "https://en.wikipedia.org/wiki/Bunce_Island",
    publication = publication,
    metadata = meta(heritageDesk, "2026-01-01", 4),
    imageId = R.drawable.post_4,
    imageThumbId = R.drawable.post_4_thumb,
    paragraphs = listOf(
        p(ParagraphType.Text, "Heritage sites support learning and remembrance. They connect families and visitors to key moments in Sierra Leone’s history."),
        p(ParagraphType.Header, "Example heritage highlights"),
        p(ParagraphType.Bullet, "Bunce Island: a historic site with global connections and an important educational role."),
        p(ParagraphType.Bullet, "Freetown Peninsula beaches and nearby landmarks: spaces for community life, tourism, and reflection."),
        p(ParagraphType.Text, "Responsible heritage education promotes respect, preservation, and national pride."),
    ),
)

private val post5 = Post(
    id = "culture_5",
    title = "Cuisine: Cassava Leaf, Groundnut Stew, and Shared Meals",
    subtitle = "Food as culture, hospitality, and community",
    url = "https://en.wikipedia.org/wiki/Sierra_Leonean_cuisine",
    publication = publication,
    metadata = meta(foodDesk, "2026-01-01", 3),
    imageId = R.drawable.post_5,
    imageThumbId = R.drawable.post_5_thumb,
    paragraphs = listOf(
        p(ParagraphType.Text, "Food is one of the easiest ways to experience culture. In Sierra Leone, meals often reflect hospitality and togetherness."),
        p(ParagraphType.Header, "Common themes"),
        p(ParagraphType.Bullet, "Leaf-based sauces and stews served with rice"),
        p(ParagraphType.Bullet, "Peanuts (groundnut) as a key ingredient in many dishes"),
        p(ParagraphType.Bullet, "Cooking and eating as shared family and community moments"),
        p(ParagraphType.Text, "Beyond taste, cuisine carries memory—recipes are often passed down through generations."),
    ),
)

val posts: List<Post> = listOf(post1, post2, post3, post4, post5)

val postsFeed: PostsFeed = PostsFeed(
    highlightedPost = post1,
    recommendedPosts = posts.drop(1),
    popularPosts = posts,
    recentPosts = posts,
)
