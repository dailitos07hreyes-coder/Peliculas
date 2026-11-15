package com.example.myapplication.database;


import com.example.myapplication.R;
import com.example.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de datos que suministra las películas actualmente en cartelera.
 * Proporciona información completa para la vista general y los detalles de cada film.
 */
public class MovieRepository {

    /**
     * Obtiene la lista completa de películas en exhibición.
     * @return Lista de objetos Movie con datos detallados.
     */
    public static List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(
                1,
                "Dune: Parte Dos",
                "Ciencia Ficción / Aventura",
                4.8f,
                "Paul Atreides se une a Chani y a los Fremen mientras busca venganza contra los conspiradores que destruyeron a su familia.",
                "Paul Atreides se une a Chani y a los Fremen mientras busca venganza contra los conspiradores que destruyeron a su familia. Ante una elección entre el amor de su vida y el destino del universo conocido, se esfuerza por evitar un futuro terrible que solo él puede prever.",
                "Denis Villeneuve",
                "1 de marzo de 2024",
                R.drawable.poster_dune
        ));

        movies.add(new Movie(
                2,
                "Oppenheimer",
                "Drama Histórico / Biografía",
                4.7f,
                "La apasionante historia del físico J. Robert Oppenheimer y su rol en el Proyecto Manhattan durante la Segunda Guerra Mundial.",
                "Durante la Segunda Guerra Mundial, el físico teórico J. Robert Oppenheimer es elegido para dirigir el ultrasecreto Laboratorio de Los Álamos, donde lidera el desarrollo de la primera arma nuclear del mundo en el Proyecto Manhattan, cambiando el rumbo de la humanidad para siempre.",
                "Christopher Nolan",
                "21 de julio de 2023",
                R.drawable.poster_oppenheimer
        ));

        movies.add(new Movie(
                3,
                "Interstellar",
                "Ciencia Ficción / Drama",
                4.9f,
                "Un grupo de exploradores espaciales viaja a través de un agujero de gusano para encontrar un nuevo hogar para la humanidad.",
                "Al ver que la vida en la Tierra está llegando a su fin debido a una plaga global y tormentas de polvo, un grupo de científicos y pilotos viaja a través de un agujero de gusano cerca de Saturno en busca de un nuevo planeta habitable para garantizar la supervivencia de la especie humana.",
                "Christopher Nolan",
                "7 de noviembre de 2014",
                R.drawable.poster_interstellar
        ));

        movies.add(new Movie(
                4,
                "El Padrino",
                "Crimen / Drama",
                5.0f,
                "La crónica del patriarca de una dinastía del crimen organizado de Nueva York y la transferencia de control a su hijo.",
                "Don Vito Corleone es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York. Cuando se niega a entrar en el negocio de los narcóticos, se desata una cruenta guerra de clanes que obligará a su hijo menor, Michael, a involucrarse en los asuntos de la familia.",
                "Francis Ford Coppola",
                "24 de marzo de 1972",
                R.drawable.poster_godfather
        ));

        movies.add(new Movie(
                5,
                "Spider-Man: Cruzando el Multiverso",
                "Animación / Acción",
                4.6f,
                "Miles Morales es catapultado a través del Multiverso, donde se encuentra con un equipo de Spider-Gente.",
                "Miles Morales se reúne con Gwen Stacy y es transportado al Multiverso, donde conoce a la Sociedad Araña, un grupo de héroes arácnidos encargados de proteger la existencia de las dimensiones. Cuando surge un conflicto moral, Miles debe redefinir lo que significa ser un verdadero héroe.",
                "Joaquim Dos Santos, Kemp Powers",
                "2 de junio de 2023",
                R.drawable.poster_spiderman
        ));

        movies.add(new Movie(
                6,
                "Origen (Inception)",
                "Ciencia Ficción / Suspense",
                4.8f,
                "Un ladrón que roba secretos corporativos mediante tecnología de sueños recibe la tarea inversa de implantar una idea.",
                "Dom Cobb es un ladrón experimentado, el mejor en el peligroso arte de la extracción: robar secretos valiosos desde lo profundo del subconsciente durante el sueño. Ahora se le ofrece una oportunidad de redención al realizar lo opuesto: la 'incepción', implantar una idea en la mente de un heredero.",
                "Christopher Nolan",
                "16 de julio de 2010",
                R.drawable.poster_inception
        ));

        movies.add(new Movie(
                7,
                "Gladiator II",
                "Acción / Épica Histórica",
                4.4f,
                "Años después de presenciar la muerte de Máximo, Lucio debe entrar al Coliseo tras la caída de su hogar ante los emperadores tiránicos.",
                "Lucio, nieto del exemperador Marco Aurelio e hijo de Lucila, vive en paz en Numidia. Sin embargo, una invasión del ejército romano liderada por Marco Acacio lo obliga a convertirse en esclavo y gladiador en Roma, donde luchará por devolver la gloria y libertad a su pueblo.",
                "Ridley Scott",
                "15 de noviembre de 2024",
                R.drawable.poster_gladiator
        ));

        movies.add(new Movie(
                8,
                "El Caballero Oscuro",
                "Acción / Crimen",
                4.9f,
                "Batman, con la ayuda del teniente Gordon y Harvey Dent, se enfrenta a una mente criminal implacable: el Joker.",
                "Con la ayuda del teniente Jim Gordon y el fiscal de distrito Harvey Dent, Batman se propone desmantelar las organizaciones criminales restantes que plagan las calles de Gotham. La sociedad resulta eficaz hasta que la ciudad cae en el caos provocado por una mente criminal anárquica: el Joker.",
                "Christopher Nolan",
                "18 de julio de 2008",
                R.drawable.poster_batman
        ));

        return movies;
    }
}
