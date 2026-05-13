package com.example.vtavaresmusicapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String,
    val image: String
)

val albumList = listOf(
    Album(
        id = "69f90c5e2fa93270c1903690",
        title = "The Dark Side of the Moon",
        artist = "Pink Floyd",
        description = "Album conceptual de 1973 que explora temas como el conflicto, la avaricia, el tiempo y la enfermedad mental.",
        image = "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png"
    ),
    Album(
        id = "69f90c5e2fa93270c1903691",
        title = "Abbey Road",
        artist = "The Beatles",
        description = "Undecimo album de estudio de The Beatles, lanzado en 1969, famoso por su portada y su medley en el lado B.",
        image = "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg"
    ),
    Album(
        id = "69f90c5e2fa93270c1903692",
        title = "Thriller",
        artist = "Michael Jackson",
        description = "Album de 1982 que se convirtio en el mas vendido de la historia, con exitos como Billie Jean y Beat It.",
        image = "https://upload.wikimedia.org/wikipedia/en/5/55/Michael_Jackson_-_Thriller.png"
    ),
    Album(
        id = "69f90c5e2fa93270c1903693",
        title = "Back in Black",
        artist = "AC/DC",
        description = "Septimo album de estudio de AC/DC, lanzado en 1980 como tributo al fallecido vocalista Bon Scott.",
        image = "https://upload.wikimedia.org/wikipedia/commons/9/91/ACDC_Back_in_Black.png"
    ),
    Album(
        id = "69f90c5e2fa93270c1903694",
        title = "Rumours",
        artist = "Fleetwood Mac",
        description = "Album de 1977 que captura las tensiones romanticas dentro de la banda, con clasicos como Go Your Own Way.",
        image = "https://upload.wikimedia.org/wikipedia/en/f/fb/FMacRumours.PNG"
    ),
    Album(
        id = "69f90c5e2fa93270c1903695",
        title = "Nevermind",
        artist = "Nirvana",
        description = "Segundo album de Nirvana, lanzado en 1991, que llevo el grunge al mainstream con Smells Like Teen Spirit.",
        image = "https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg"
    ),
    Album(
        id = "69f90c5e2fa93270c1903696",
        title = "OK Computer",
        artist = "Radiohead",
        description = "Tercer album de Radiohead, de 1997, una obra introspectiva sobre la alienacion en la era moderna.",
        image = "https://upload.wikimedia.org/wikipedia/en/b/ba/Radioheadokcomputer.png"
    ),
    Album(
        id = "69f90c5e2fa93270c1903697",
        title = "Lemonade",
        artist = "Beyonce",
        description = "Album visual de 2016 que aborda la infidelidad, el empoderamiento femenino y la identidad afroamericana.",
        image = "https://upload.wikimedia.org/wikipedia/en/a/a8/Beyonce_-_Lemonade_%28Official_Album_Cover%29.png"
    ),
    Album(
        id = "69f90c5e2fa93270c1903698",
        title = "Random Access Memories",
        artist = "Daft Punk",
        description = "Cuarto album de Daft Punk, de 2013, con un sonido organico inspirado en la musica de los 70 y 80.",
        image = "https://upload.wikimedia.org/wikipedia/en/a/a7/Random_Access_Memories.jpg"
    ),
    Album(
        id = "69f90c5e2fa93270c1903699",
        title = "To Pimp a Butterfly",
        artist = "Kendrick Lamar",
        description = "Tercer album de estudio de Kendrick Lamar, de 2015, que mezcla jazz, funk y hip-hop con una potente narrativa social.",
        image = "https://upload.wikimedia.org/wikipedia/en/f/f6/Kendrick_Lamar_-_To_Pimp_a_Butterfly.png"
    )
)