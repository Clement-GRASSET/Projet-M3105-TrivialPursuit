package iut.projets.trivialpursuit.game.questions;

import iut.projets.trivialpursuit.engine.Game;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * Gère la création de questions
 */

public class QuestionsManager {
    private static boolean fexists = false;
    static Map<String, Map<String, List<Question>>> question_list;

    private static Document questions_doc;
    private static File file;
    private static String path = Game.getDirectory() + "/questions.xml";

    /**
     * @return Racine du XML
     */
    private static Element getRoot() {
        return (Element) questions_doc.getElementsByTagName("Root").item(0);
    }

    /**
     * Crée une balise Category dans le XML
     * @param name Nom de la catégorie
     * @return Category créée
     */
    public static Element createCategory(String name) {
        Element element = questions_doc.createElement("Category");
        getRoot().appendChild(element);

        element.setAttribute("name", name);

        return element;
    }

    /**
     * Crée une balise Difficulty dans le XML
     * @param catname Nom de la catégorie à laquelle sera affiliée la difficulté
     * @param name Difficulté de la question
     * @return Difficulty créée
     */
    public static Element createDifficulty(String catname, String name) {
        try {
            Element element = questions_doc.createElement("Difficulty");
            getCategory(catname).appendChild(element);

            element.setAttribute("name", name);

            return element;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }


    /**
     * Crée une balise Question représentant une question et ses réponses et la rajoute à la questions_list
     * @param catname Catégorie de la question
     * @param diffname Difficulté de la question
     * @param content Question
     * @param sanswer0 Première proposition de réponse
     * @param sanswer1 Deuxième proposition de réponse
     * @param sanswer2 Troisième proposition de réponse
     * @param sanswer3 Quatrième proposition de réponse
     * @param r_answer Indice de la bonne réponse
     */
    public static void createQuestion(String catname, String diffname, String content, String sanswer0, String sanswer1, String sanswer2, String sanswer3, int r_answer) {
        try {
            Element element = questions_doc.createElement("Question");
            getDifficulty(diffname, catname).appendChild(element);

            //Answer
            Element answer0 = questions_doc.createElement("Answer");
            answer0.appendChild(questions_doc.createTextNode(sanswer0));
            element.appendChild(answer0);

            Element answer1 = questions_doc.createElement("Answer");
            answer1.appendChild(questions_doc.createTextNode(sanswer1));
            element.appendChild(answer1);

            Element answer2 = questions_doc.createElement("Answer");
            answer2.appendChild(questions_doc.createTextNode(sanswer2));
            element.appendChild(answer2);

            Element answer3 = questions_doc.createElement("Answer");
            answer3.appendChild(questions_doc.createTextNode(sanswer3));
            element.appendChild(answer3);


            element.setAttribute("name", content);
            element.setAttribute("right", String.valueOf(r_answer));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Crée le contenu du fichier XML
     */
    public static void createQuestions() {
        // Géographie

        createCategory("Géographie");

        createDifficulty("Géographie", "Débutant");
        createQuestion("Géographie", "Débutant", "Où se trouve l'Australie ?", "En (-33.868857, 151.206079)", "En (-33.868857, 151.206079)", "À l'envers", "En Allemagne", 2);
        createQuestion("Géographie", "Débutant", "Combien d'étoiles ornent le drapeau chinois ?", "2", "5", "6.66", "3", 1);
        createQuestion("Géographie", "Débutant", "Combien d'étoiles ornent le drapeau européen ?", "27", "24", "16", "12", 3);
        createQuestion("Géographie", "Débutant", "De quel pays les habitants de Tahiti sont-ils citoyens ?", "France", "États-Unis", "Angleterre", "Espagne", 0);
        createQuestion("Géographie", "Débutant", "Quelle gare se trouve à Toulouse ?", "Saint-Lazare", "Gare du Nord", "Matabiau", "Austerlitz", 2);
        createQuestion("Géographie", "Débutant", "Dans quel état américain se trouve la vallée de la Mort ?", "Texas", "Californie", "Floride", "Louisiane", 1);

        createDifficulty("Géographie", "Intermédiaire");
        createQuestion("Géographie", "Intermédiaire", "Quelles sont les coordonées de l'Allemagne ?", "(35.861660, 104.195397)", "(-85, 65)", "(35, -98)", "(51.165691, 10.451526)", 3);
        createQuestion("Géographie", "Intermédiaire", "Quelle est le plus grande île de la Méditerranée ?", "La grande ourse", "Sicile", "Corse", "L'Italie", 1);
        createQuestion("Géographie", "Intermédiaire", "Combien de pays ont une frontière commune avec la France ?", "7", "8", "6", "5", 1);
        createQuestion("Géographie", "Intermédiaire", "Comment appelle-t-on les habitants des Bouches-du-Rhône ?", "Bucco dentaires", "Bouchers", "Bucco-rhodaniens", "Rhonins", 2);
        createQuestion("Géographie", "Intermédiaire", "À quelle région appartient le mont Saint Michel ?", "Normandie", "Bretagne", "Normandie et Bretagne", "Ils y font des buiscuits", 0);
        createQuestion("Géographie", "Intermédiaire", "Dans le Sud, comment appelle-t-on les criques bordées de rochers escarpés ?", "Arone", "En-Vau", "Calanques", "Cassis", 2);

        createDifficulty("Géographie", "Expert");
        createQuestion("Géographie", "Expert", "Qu'est-ce qui intéresse le plus la France ?", "Pain au chocolat ou *********** ?", "La communication en entreprise", "Le salaire des allemands", "Escabeau ou échelle ?", 2);
        createQuestion("Géographie", "Expert", "Sur quel continent vit le kinkajou ?", "Amérique du Sud", "Europe", "Antarctique", "Amérique du Nord", 0);
        createQuestion("Géographie", "Expert", "Combien de fuseaux horaires traversent le Canada ?", "3", "4", "5", "6", 3);
        createQuestion("Géographie", "Expert", "Comment s'appelle la ville souterraine de Montréal ?", "Souterin", "Parking", "Réso", "Metrop", 2);
        createQuestion("Géographie", "Expert", "Quelle est la capitale sur Sri Lanka ", "Anuradhapura", "Colombo", "Kandy", "Sri Jayawardenapura Kotte", 3);
        createQuestion("Géographie", "Expert", "Dans quel pays a été relevée la température ambiante la plus élevée jamais enregistrée ?", "Australie", "États-Unis", "Brésil", "Équateur", 1);

        // Divertissements

        createCategory("Divertissements");

        createDifficulty("Divertissements", "Débutant");
        createQuestion("Divertissements", "Débutant", "Qu'est-ce qui est arrivé en premier ?", "Pokémon", "Chute du mur de Berlin", "Donkey kong country", "One piece", 1);
        createQuestion("Divertissements", "Débutant", "Quelle est la bonne réponse ?", "2", "E", "Celle-ci", "La bonne réponse", 3);
        createQuestion("Divertissements", "Débutant", "Elle chante, elle garde des enfants et vole grâce à son parapluie. Qui est-elle ?", "Nanny Mcphee", "Anne Hidalgo", "Mary Poppins", "Angela Merkel", 0);
        createQuestion("Divertissements", "Débutant", "Compléter : Je cherche un job ...", "I Joba", "Afin de gagner beaucoup d'agent", "De métiers", "Qui me sert de travail", 3);
        createQuestion("Divertissements", "Débutant", "Qui est le compositeur de l'OST de Minecraft", "C418", "Koji kondo", "Toby Fox", "Jeb", 0);
        createQuestion("Divertissements", "Débutant", "En quelle année est sorti The Elder Scrolls : Skyrim ?", "2010", "2011", "2012", "2013", 1);

        createDifficulty("Divertissements", "Intermédiaire");
        createQuestion("Divertissements", "Intermédiaire", "Quand est sorti Minecraft Bedrock Edition ?", "14 février 2014", "19 décembre 2016", "29 juillet 2014", "7 octobre 2016", 1);
        createQuestion("Divertissements", "Intermédiaire", "Compléter : Tu es triste ? ...", "Moi non", "Arrête", "Calme-toi", "Ne t'inquiète pas", 1);
        createQuestion("Divertissements", "Intermédiaire", "Dans quelle licence a travaillé David Wise ?", "", "Donkey Kong Country", "", "Banjo & Kazooie", 1);
        createQuestion("Divertissements", "Intermédiaire", "Combien y a-t-il de Pokémon (Décembre 2021) ?", "998", "988", "889", "898", 3);
        createQuestion("Divertissements", "Intermédiaire", "Qu'est-ce que le procureur Godot aime ?", "Le café", "Le thé", "Le chocolat", "Les masques", 0);
        createQuestion("Divertissements", "Intermédiaire", "NOW'S YOUR CHANCE TO BE A...", "[hyperlink blocked]", "Lightner", "BIG SHOT", "Kramer", 2);

        createDifficulty("Divertissements", "Expert");
        createQuestion("Divertissements", "Expert", "Quand est sorti Breath of the Wild ?", "28 avril 2017", "15 mars 2017", "3 mars 2017", "7 décembre 2018", 2);
        createQuestion("Divertissements", "Expert", "Quel est l'id d'une dalle en bois de sapin ?", "126:1", "125:1", "126:5", "125:3", 0);
        createQuestion("Divertissements", "Expert", "Quel est le 57e personnage de Super Smash Bros. Ultimate ?", "Ryu", "Pac-man", "Shulk", "Mii gunner", 2);
        createQuestion("Divertissements", "Expert", "Comment le professeur de solfège Rémy est-il mort ?", "Il s'est cogné le petit orteil dans un piano", "De dos face au sol, là", "Bonne question", "Poignardé de face contre un mur, ici", 2);
        createQuestion("Divertissements", "Expert", "Pour quelle oeuvre Lena Raine n'a jamais composé ?", "Celeste", "Minecraft", "Deltarune", "VVVVVV", 0);
        createQuestion("Divertissements", "Expert", "De combien de jeux est composée la licence Mega man (licences principales)?", "19", "23", "25", "32", 0);


        // Histoire

        createCategory("Histoire");

        createDifficulty("Histoire", "Débutant");
        createQuestion("Histoire", "Débutant", "Qu'est-ce qui a provoqué une dizaine de milliers de morts en France pendant l'été 2003 ?", "La canicule", "La glaciation", "Une tempête de sable", "Une attaque au lance-flamme", 0);
        createQuestion("Histoire", "Débutant", "Combien de temps a durée le Moyen Âge ?", "La moitié de l'âge de l'humanité", "52 596 000 000 secondes", "La moitié de l'âge de la Terre", "8 766 000 heures", 3);
        createQuestion("Histoire", "Débutant", "Qu'est-ce que le Commune de Paris ?", "Manifestation Pacifique pendant la querre Franco-prussienne", "Période d'insurrection", "Le centre-ville de Paris", "Élément déclencheur de la Révolution française", 1);
        createQuestion("Histoire", "Débutant", "Quel événemen à conduit à la Première Guerre Mondiale ?", "L'Avant Guerre Mondiale", "Occupation de la Pologne par l'URSS", "Assassnat de François Ferdinand", "Annexion de l'Autriche par l'Allemagne", 2);
        createQuestion("Histoire", "Débutant", "Comment appelle-t-on la chasse aux sorcières de Richelieu ?", "Affaire des soeurs de la Rochelle", "Affaire des possédées de Loudun", "Affaire Dreyfus", "Affaire du collier de la reine", 1);
        createQuestion("Histoire", "Débutant", "Qui a dessiné l'homme de Vitruve ?", "Arcimboldo", "Van Gogh", "Léonrad de Vinci", "Michel-Ange", 2);

        createDifficulty("Histoire", "Intermédiaire");
        createQuestion("Histoire", "Intermédiaire", "Comment se nomme le dieu du vin ?", "Héra", "Déméter", "Dionysos", "La réponse D", 2);
        createQuestion("Histoire", "Intermédiaire", "Quel pays a été victime d'une invasion soviétique en 1956 ?", "Espagne", "URSS", "Corée du Nord", "Hongrie", 3);
        createQuestion("Histoire", "Intermédiaire", "Combien de pyramides de Gizeh ont été construites ?", "2", "2x-5 = 0", "2x^2+4x+2 = 0", "1.5", 0);
        createQuestion("Histoire", "Intermédiaire", "Quel traité institue la création de la Communauté Économique Européenne ?", "Rome", "Versailles", "Maastricht", "Lisbonne", 0);
        createQuestion("Histoire", "Intermédiaire", "Dans la mythologie aztèque, qui est le Quetzalcoalt ?", "Dieu serpent à plumes", "Die à tête de chien ", "Roi des morts", "Dieu soleil", 0);
        createQuestion("Histoire", "Intermédiaire", "Quelle était la profession initale de Gandhi M", "Juge", "Procureur", "Médecin", "Avocat", 3);

        createDifficulty("Histoire", "Expert");
        createQuestion("Histoire", "Expert", "Combien de mort y a-t-il eu en été 2003 ? ", "15 830", "19 490", "21 272", "Beaucoup", 1);
        createQuestion("Histoire", "Expert", "Quel était le slogan de campagne de François Mitterrand en 1981 ?", "Au revoir", "Je suis l'homme le plus honnête du monde", "La république c'est moi", "Force tranquille", 3);
        createQuestion("Histoire", "Expert", "Quel est le premier occidental à avoir mis un pied en Chine ?", "Fernand de Magellan", "Marco Polo", "René Caillié", "Jules Dumont d'Urville", 1);
        createQuestion("Histoire", "Expert", "Pour quelles raison Louais XIII ordonne-t-il le siège de La Rochelle ?", "Reprendre la cité aux anglais", "Lutter contre les protestants", "Mettre fin à la révolte des Girondins", "S'approprier les terres", 1);
        //createQuestion("Histoire", "Expert", "", "", "", "", "", 0);
        //createQuestion("Histoire", "Expert", "", "", "", "", "", 0);


        // Art et littérature

        createCategory("Art et littérature");

        createDifficulty("Art et littérature", "Débutant");
        createQuestion("Art et littérature", "Débutant", "Qu'est-ce qui indique qu'une oeuvre a une grande qualité ?", "Il coûte cher", "Il est recommmandé par Olivier Mine", "Il est bien fait", "Il a reçu un prix", 1);
        createQuestion("Art et littérature", "Débutant", "Quel est le pays ayant le plus de souffrance ?", "Allemagne", "Russie", "Espagne", "Groenland", 2);
        createQuestion("Art et littérature", "Débutant", "Pourquoi Frodon va-t-il sur un volcan ?", "Il avait froid", "Il n'a plus d'argent", "Pour tuer Gollum", "Pour y jeter l'anneau", 3);
        createQuestion("Art et littérature", "Débutant", "Quel est le nom de le personne ayant servit de modèle pour la Joconde ?", "Sara Zezza", "Cora Gennari", "Mina Loria", "Mona Lisa", 0);
        //createQuestion("Art et littérature", "Débutant", "", "", "", "", "", 0);
        //createQuestion("Art et littérature", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Art et littérature", "Intermédiaire");
        createQuestion("Art et littérature", "Intermédiaire", "Quel est le symbole de la beauté ?", "Dora", "Fabio Lanzoni", "Le nombril d'Adibou", "La beauté", 3);
        createQuestion("Art et littérature", "Intermédiaire", "Qu'est-ce qu'un guéridon ?", "Une entreprise de médicaments", "Une table à un pied", "Une action bénévole ayant pour but de soigner", "Un insect volant", 1);
        createQuestion("Art et littérature", "Intermédiaire", "Quel groupe chanta \"Dancing queen\" ?", "Pink Floyd", "Daft Punk", "U2", "ABBA", 0);
        //createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);
        //createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);
        //createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Art et littérature", "Expert");
        createQuestion("Art et littérature", "Expert", "Qui a écrit \"Le Marchand de Venise\" ?", "Shakespeare", "Orwell", "Molière", "Voltaire", 0);
        createQuestion("Art et littérature", "Expert", "Quelle est la taille de la baguette de Luna Lovegood ?", "32 cm", "27.5 cm", "34 cm", "25 cm", 1);
        createQuestion("Art et littérature", "Expert", "Qui a écrit \"Le joueur d'échec \" ?", "Juli Zeh", "Marguerite Duras", "Bernard Marie-Koltès", "Stefan Zweig", 0);
        //createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        //createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        //createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);


        // Science et nature

        createCategory("Science et nature");

        createDifficulty("Science et nature", "Débutant");
        createQuestion("Science et nature", "Débutant", "Qui a le temps de fermentation le plus long ?", "Yaourt lait demi-écrémé", "L'humour", "Yaourt lait entier", "Emmental", 1);
        createQuestion("Science et nature", "Débutant", "Quelle couleur est la moins présente dans la nature ?", "Rouge", "Vert", "Bleu", "RGB", 2);
        createQuestion("Science et nature", "Débutant", "Que faut-il rajouter à un croque monsieur afin de faire un croque madame ?", "Du bacon", "Un rendez-vous chez l'esthéticienne", "Un chromosome X", "Un oeuf", 3);
        createQuestion("Science et nature", "Débutant", "Quel le vent le plus rapide jamais enregistré sur Terre ?", "408 km/h", "804 km/h", "840 km/h", "480 km/h", 0);
        createQuestion("Science et nature", "Débutant", "Que veut dire CSS ?", "Cascading Style Sheets", "Creating Sheets Style", "Cascading Stylesheet Sheets", "Composing Style Sheets", 0);
        createQuestion("Science et nature", "Débutant", "Comment réagir face à une situation complexe ?", "Vous gardez calme", "La résoudre", "Vous devienne fou", "Laisser tomber", 2);

        createDifficulty("Science et nature", "Intermédiaire");
        createQuestion("Science et nature", "Intermédiaire", "Quelle viande coûte le plus ?", "Boeuf", "Porc", "Poulet", "viande d'oiseau", 3);
        createQuestion("Science et nature", "Intermédiaire", "cos(PI)(-exp(iPI))+ln(1)-1 = ?", "0", "PI", "sqrt(2)/2", "1", 0);
        createQuestion("Science et nature", "Intermédiaire", "O(n) ≺ ?", "O(log(2^n))", "O(n^2log(n))", "O(nlog(n))", "O(sqrt(n))", 1);
        createQuestion("Science et nature", "Intermédiaire", "Que se passe-t-il lorsqu'il pleut sur Jupiter ?", "Il grêle", "C'est trop loin, impossible de voir", "Il pleut des diamants", "Il ne peut pas pleuvoir", 2);
        createQuestion("Science et nature", "Intermédiaire", "Comment se nomme le viande de grands gibiers ?", "Longe", "Tret", "Venaison", "Gite", 2);
        createQuestion("Science et nature", "Intermédiaire", "De combien de sommets est composé le dodécaèdre ?", "24", "48", "12", "20", 3);

        createDifficulty("Science et nature", "Expert");
        createQuestion("Science et nature", "Expert", "Quel paiement est le plus utilisé ?", "Espèce", "Carte", "Nature", "Chèque", 2);
        createQuestion("Science et nature", "Expert", "Que faire en cas à une brûlure ?", "Mettre sa main au feu", "Mettre sa main à couper", "AAAAAAHHHH !!!", "De l'eau froide sans pression", 3);
        createQuestion("Science et nature", "Expert", "1.437 milliards de km = ?", "Distance Terre-Soleil", "Distance Soleil-Saturne", "Distance Uranus-Saturne", "Distance Vénus-Jupiter", 3);
        createQuestion("Science et nature", "Expert", "Quel intervalle de fréquence est utilisé à des fins militaires ?", "80 à 82.475 MHz", "82.475 à 83 MHz", "83 à 87.3 MHz", "87.3 à 87.5 MHz", 1);
        createQuestion("Science et nature", "Expert", "Quelle est le gravité de Saturne ?", "10.44 m/s^2", "24.79 m/s^2", "11.15 m/s^2", "10.78 m/s^2", 0);
        createQuestion("Science et nature", "Expert", "Quel est le temps record sans dormir ?", "22 heures c'est déjà beaucoup", "10 jours et 22 heures et 14 minutes", "11 jours et 25 minutes", "11 jours et 8 heures", 3);


        // Sports et loisirs

        createCategory("Sports et loisirs");

        createDifficulty("Sports et loisirs", "Débutant");
        createQuestion("Sports et loisirs", "Débutant", "Comment s'appelle une personne qui pratique un sport sans toucher une rémunération ?", "Un chômeur", "Un amateur", "Un pauvre", "Un sportif", 1);
        createQuestion("Sports et loisirs", "Débutant", "Que doit-on obligatoirement réunir comme preuves afin de finir une partie de Cluedo ?", "Tueur", "Tueur et arme", "Aucune", "Tueur, arme et pièce", 2);
        createQuestion("Sports et loisirs", "Débutant", "Combien d'argent faut-il au total dans un Monopoly pour tout acheter ?", "5 280", "7 450", "8 000", "11 550", 1);
        createQuestion("Sports et loisirs", "Débutant", "Comment s'appelle l'action au Volley où le passeur envoie volontairement la balle du côté adverse ?", "Smash", "1ère main", "2nd main", "Passe en profondeur", 2);
        createQuestion("Sports et loisirs", "Débutant", "Quel est le temps record d'Usain Bolt au 100 m ?", "10.7 secondes", "11.45 secondes", "11.08 secondes", "9.58 secondes", 3);
        //createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Sports et loisirs", "Intermédiaire");
        createQuestion("Sports et loisirs", "Intermédiaire", "Quel coureur a gagné le plus de Grand prix ?", "Sébastien Thon", "Sébastien Loeb", "Micheal Schumacher", "Alain Prost", 2);
        createQuestion("Sports et loisirs", "Intermédiaire", "Combien de NBA Micheal Jordan a-t-il remporté chez les Chicago Bulls ?", "4", "12", "8", "6", 3);
        createQuestion("Sports et loisirs", "Intermédiaire", "Qui appelle Adrian ?", "Sylvester Stallone", "Rambo", "Rocky Balboa", "Son père", 2);
        createQuestion("Sports et loisirs", "Intermédiaire", "Si on enlève les carte asymétriques à un paquet de 52 cartes combien en reste-t-il ?", "45", "39", "36", "30", 3);
        createQuestion("Sports et loisirs", "Intermédiaire", "Quel est le deuxième sport le plus pratiqué en France ?", "Tennis", "Equitation", "Basket-ball", "Judo", 0);
        createQuestion("Sports et loisirs", "Intermédiaire", "Quel sport a le plus grand terrain au monde ?", "Rugby", "Joute", "Polo", "Football", 2);

        createDifficulty("Sports et loisirs", "Expert");
        createQuestion("Sports et loisirs", "Expert", "Combien de cartes y a-t-il dans un jeu de Uno ?", "108", "115", "128", "Ça fait beaucoup là non ?", 0);
        createQuestion("Sports et loisirs", "Expert", "Que signifie DC ?", "Diversity Club", "Directive Company", "Detective Comics", "Digital Comics", 2);
        createQuestion("Sports et loisirs", "Expert", "Quel était le nom originel de Superman ?", "Suzus", "Tus-noz", "Nil-bex", "Kal-El", 3);
        createQuestion("Sports et loisirs", "Expert", "Dans combien de James Bond Sean Connery a-t-il joué ?", "7", "7.12", "6", "5", 0);
        createQuestion("Sports et loisirs", "Expert", "Combien de questions compose se Trivial Pursuit ?", "Bien trop c'était long à faire", "L'équivalent d'une nuit", "108", "480 en ressenti", 2);
        createQuestion("Sports et loisirs", "Expert", "Quand est sorti le Trivial Pursuit pour la première fois ?", "1981", "1978", "1974", "1972", 0);

    }

    /**
     * Redéfinie le XML
     */
    public static void reset() {
        questions_doc.removeChild(getRoot());

        Element questions_root = questions_doc.createElement("Root");
        questions_doc.appendChild(questions_root);

        createQuestions();
        save();
    }

    /**
     * Crée le fichier XML
     */
    public static void save() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(questions_doc);
            StreamResult result = new StreamResult(file);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Charge le fichier XML s'il existe déjà sinon le crée
     */
    public static void load() {
        file = new File(path);
        question_list = new HashMap<>();

        try {
            if (file.exists()) {
                questions_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                System.out.println("Chargé");
                fexists = true;
            } else {
                questions_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                Element questions_root = questions_doc.createElement("Root");
                questions_doc.appendChild(questions_root);

                createQuestions();

                save();
                System.out.println("Créé");
                fexists = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList catlist = getRoot().getElementsByTagName("Category");

        for (int i = 0; i < catlist.getLength(); i++) {
            Element catnode = (Element) catlist.item(i);
            String catname = catnode.getAttribute("name");
            question_list.put(catname, new HashMap<>());

            NodeList difflist = catnode.getElementsByTagName("Difficulty");

            for (int j = 0; j < difflist.getLength(); j++) {
                Element diffnode = (Element) difflist.item(j);
                String diffname = diffnode.getAttribute("name");
                NodeList qlist = diffnode.getElementsByTagName("Question");

                question_list.get(catname).put(diffname, new ArrayList<>());

                for (int k = 0; k < qlist.getLength(); k++) {
                    Element qnode = (Element) qlist.item(k);
                    Question question = new Question();

                    question.question = qnode.getAttribute("name");

                    /*System.out.println(qnode.getChildNodes().item(0).getTextContent() + " "
                            + qnode.getChildNodes().item(1).getTextContent() + " "
                            + qnode.getChildNodes().item(2).getTextContent() + " "
                            + qnode.getChildNodes().item(3).getTextContent());*/

                    if (!fexists)
                    {
                        //System.out.println("! fexists " + fexists);
                        question.answer = new String[]{ qnode.getChildNodes().item(0).getTextContent(),
                                                        qnode.getChildNodes().item(1).getTextContent(),
                                                        qnode.getChildNodes().item(2).getTextContent(),
                                                        qnode.getChildNodes().item(3).getTextContent()};
                    }
                    else {
                        //System.out.println("fexists " + fexists);
                        question.answer = new String[]{ qnode.getChildNodes().item(1).getTextContent(),
                                                        qnode.getChildNodes().item(3).getTextContent(),
                                                        qnode.getChildNodes().item(5).getTextContent(),
                                                        qnode.getChildNodes().item(7).getTextContent()};
                    }

                    question.right = Integer.parseInt( qnode.getAttribute("right") );

                    showQuestion(question);

                    question_list.get(catname).get(diffname).add(question);
                }
            }
        }

        //showQuestion(question_list.get("Y").get("Débutant").get(0));
    }


    /**
     * Renvoie une catégorie recherchée en fonction de son nom
     * @param name Nom de la catégorie
     * @return Catégorie recherchée
     * @throws Exception Catégorie introuvable dans le fichier
     */
    private static Element getCategory(String name) throws Exception {
        questions_doc.getDocumentElement().normalize();

        NodeList list = questions_doc.getElementsByTagName("Category");

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (name.equals(element.getAttribute("name")))
                    return element;
            }
        }

        throw new Exception("Catégorie " + name + " introuvable !");
    }

    /**
     * Renvoie une difficulté recherchée en fonction de son nom et de celui de sa catégorie
     * @param name Difficulté de la question
     * @param catname Catégorie à laquelle la difficulté est affiliée
     * @return Difficulté recherchée
     * @throws Exception  Catégorie à laquelle correspond la difficulté introuvable dans le fichier
     */
    private static Element getDifficulty(String name, String catname) throws Exception {
        questions_doc.getDocumentElement().normalize();

        NodeList list = getCategory(catname).getElementsByTagName("Difficulty");

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (name.equals(element.getAttribute("name")))
                    return element;
            }
        }

        throw new Exception("Catégorie " + name + " introuvable !");
    }

    /**
     * Affiche dans la console une question suivie des propositions de réponses et de l'indice de la bonne réponse
     * @param question Question que l'on souhaite afficher
     */
    private static void showQuestion(Question question)
    {
        System.out.println("Question : " + question.question + " - Réponses : "
                + question.answer[0] + " ; " + question.answer[1] + " ; " + question.answer[2] + " ; " + question.answer[3]
                + " -  Bonne réponse : " + question.right);
    }

    /**
     * Renvoie une question aléatoirement
     * @param category Catégorie de la question
     * @param difficulty Difficulté de la question
     * @return Question aléatoire
     */
    public static Question getRandomQuestion(String category, String difficulty) {
        Map<String, Map<String, List<Question>>> categories = question_list;
        //System.out.println("Categories :");
        //System.out.println(categories);

        Map<String, List<Question>> difficulties = question_list.get(category);
        //System.out.println("Difficulties :");
        //System.out.println(difficulties);

        List<Question> questions = difficulties.get(difficulty);
        //System.out.println("Questions :");
        //System.out.println(questions);

        Random random = new Random();
        Question question = questions.get( random.nextInt(questions.size()) );
        //System.out.println("Question :");
        //System.out.println(question);

        return question;
    }
}
