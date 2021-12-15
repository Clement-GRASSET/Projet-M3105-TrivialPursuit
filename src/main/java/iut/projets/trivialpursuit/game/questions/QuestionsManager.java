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
        createQuestion("Géographie", "Débutant", "", "", "", "", "", 0);
        createQuestion("Géographie", "Débutant", "", "", "", "", "", 0);
        createQuestion("Géographie", "Débutant", "", "", "", "", "", 0);
        createQuestion("Géographie", "Débutant", "", "", "", "", "", 0);
        createQuestion("Géographie", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Géographie", "Intermédiaire");
        createQuestion("Géographie", "Intermédiaire", "Quelles sont les coordonées de l'Allemagne ?", "(35.861660, 104.195397)", "(-85, 65)", "(35, -98)", "(51.165691, 10.451526)", 3);
        createQuestion("Géographie", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Géographie", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Géographie", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Géographie", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Géographie", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Géographie", "Expert");
        createQuestion("Géographie", "Expert", "Qu'est-ce qui intéresse le plus la France ?", "Pain au chocolat ou *********** ?", "La communication en entreprise", "Le salaire des allemands", "Escabeau ou échelle ?", 2);
        createQuestion("Géographie", "Expert", "", "", "", "", "", 0);
        createQuestion("Géographie", "Expert", "", "", "", "", "", 0);
        createQuestion("Géographie", "Expert", "", "", "", "", "", 0);
        createQuestion("Géographie", "Expert", "", "", "", "", "", 0);
        createQuestion("Géographie", "Expert", "", "", "", "", "", 0);

        // Divertissements

        createCategory("Divertissements");

        createDifficulty("Divertissements", "Débutant");
        createQuestion("Divertissements", "Débutant", "Qu'est-ce qui est arrivé en premier ?", "Pokémon", "Chute du mur de Berlin", "Donkey kong country", "One piece", 0);
        createQuestion("Divertissements", "Débutant", "Quelle est la bonne réponse ?", "2", "E", "Celle-ci", "La bonne réponse", 0);
        createQuestion("Divertissements", "Débutant", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Débutant", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Débutant", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Divertissements", "Intermédiaire");
        createQuestion("Divertissements", "Intermédiaire", "Quand est sorti Minecraft Bedrock Edition ?", "14 février 2014", "19 décembre 2016", "29 juillet 2014", "7 octobre 2016", 1);
        createQuestion("Divertissements", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Divertissements", "Expert");
        createQuestion("Divertissements", "Expert", "Quand est sorti Breath of the Wild ?", "28 avril 2017", "15 mars 2017", "3 mars 2017", "7 décembre 2018", 2);
        createQuestion("Divertissements", "Expert", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Expert", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Expert", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Expert", "", "", "", "", "", 0);
        createQuestion("Divertissements", "Expert", "", "", "", "", "", 0);


        // Histoire

        createCategory("Histoire");

        createDifficulty("Histoire", "Débutant");
        createQuestion("Histoire", "Débutant", "", "", "", "", "", 0);
        createQuestion("Histoire", "Débutant", "", "", "", "", "", 0);
        createQuestion("Histoire", "Débutant", "", "", "", "", "", 0);
        createQuestion("Histoire", "Débutant", "", "", "", "", "", 0);
        createQuestion("Histoire", "Débutant", "", "", "", "", "", 0);
        createQuestion("Histoire", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Histoire", "Intermédiaire");
        createQuestion("Histoire", "Intermédiaire", "Comment se nomme le dieu du vin ?", "Héra", "Déméter", "Dionysos", "La réponse D", 2);
        createQuestion("Histoire", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Histoire", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Histoire", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Histoire", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Histoire", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Histoire", "Expert");
        createQuestion("Histoire", "Expert", "", "", "", "", "", 0);
        createQuestion("Histoire", "Expert", "", "", "", "", "", 0);
        createQuestion("Histoire", "Expert", "", "", "", "", "", 0);
        createQuestion("Histoire", "Expert", "", "", "", "", "", 0);
        createQuestion("Histoire", "Expert", "", "", "", "", "", 0);
        createQuestion("Histoire", "Expert", "", "", "", "", "", 0);


        // Art et littérature

        createCategory("Art et littérature");

        createDifficulty("Art et littérature", "Débutant");
        createQuestion("Art et littérature", "Débutant", "Qu'est-ce qui indique qu'une oeuvre a une grande qualité ?", "Il coûte cher", "Il est recommmandé par Olivier Mine", "Il est bien fait", "Il a raçu un prix", 1);
        createQuestion("Art et littérature", "Débutant", "Quel est le pays ayant le plus de souffrance ?", "Allemagne", "Russie", "Espagne", "Groenland", 2);
        createQuestion("Art et littérature", "Débutant", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Débutant", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Débutant", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Art et littérature", "Intermédiaire");
        createQuestion("Art et littérature", "Intermédiaire", "Quel est le symbole de la beauté ?", "Dora", "Fabio Lanzoni", "Le nombril d'Adibou", "La beauté", 3);
        createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Art et littérature", "Expert");
        createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);
        createQuestion("Art et littérature", "Expert", "", "", "", "", "", 0);


        // Science et nature

        createCategory("Science et nature");

        createDifficulty("Science et nature", "Débutant");
        createQuestion("Science et nature", "Débutant", "Qui a le temps de fermentation le plus long ?", "Yaourt lait demi-écrémé", "L'humour", "Yaourt lait entier", "Emmental", 1);
        createQuestion("Science et nature", "Débutant", "Quelle couleur est la moins présente dans la nature ?", "Rouge", "Vert", "Bleu", "RGB", 2);
        createQuestion("Science et nature", "Débutant", "Que faut-il rajouter à un croque monsieur afin de faire un croque madame ?", "Du bacon", "Un rendez-vous chez l'esthéticienne", "Un chromosome X", "Un oeuf", 3);
        createQuestion("Science et nature", "Débutant", "Quel le vent le plus rapide sur Terre jamais enregistré ?", "408 km/h", "804 km/h", "840 km/h", "480 km/h", 0);
        createQuestion("Science et nature", "Débutant", "", "", "", "", "", 0);
        createQuestion("Science et nature", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Science et nature", "Intermédiaire");
        createQuestion("Science et nature", "Intermédiaire", "Quelle viande coûte le plus ?", "Boeuf", "Porc", "Poulet", "viande d'oiseau", 3);
        createQuestion("Science et nature", "Intermédiaire", "cos(π)(-exp(iπ))+ln(1)-1 = ?", "0", "PI", "sqrt(2)/2", "1", 0);
        createQuestion("Science et nature", "Intermédiaire", "O(n) ≺ ?", "O(log(2^n))", "O(n²log(n))", "O(nlog(n))", "O(sqrt(n))", 1);
        createQuestion("Science et nature", "Intermédiaire", "Que se passe-t-il lorsqu'il pleut sur Jupiter ?", "Il grêle", "C'est trop loin, impossible de voir", "Il pleut des diamants", "Il ne peut pas pleuvoir", 2);
        createQuestion("Science et nature", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Science et nature", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Science et nature", "Expert");
        createQuestion("Science et nature", "Expert", "Quel paiement est le plus utilisé ?", "Espèce", "Carte", "Nature", "Chèque", 2);
        createQuestion("Science et nature", "Expert", "Que faire en cas à une brûlure ?", "Mettre sa main au feu", "Mettre sa main à couper", "AAAAAAHHHH !!!", "De l'eau froide sans pression", 3);
        createQuestion("Science et nature", "Expert", "1.437 milliards de km = ?", "Distance Terre-Soleil", "Distance Soleil-Saturne", "Distance Uranus-Saturne", "Distance Vénus-Jupiter", 3);
        createQuestion("Science et nature", "Expert", "Quel intervalle de fréquence est utilisé à des fins militaires ?", "80 à 82.475 MHz", "82.475 à 83 MHz", "83 à 87.3 MHz", "87.3 à 87.5 MHz", 1);
        createQuestion("Science et nature", "Expert", "Quelle est le gravité de Saturne ?", "10.44 m/s²", "24.79 m/s²", "11.15 m/s²", "10.78 m/s²", 0);
        createQuestion("Science et nature", "Expert", "", "", "", "", "", 0);


        // Sports et loisirs

        createCategory("Sports et loisirs");

        createDifficulty("Sports et loisirs", "Débutant");
        createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Débutant", "", "", "", "", "", 0);

        createDifficulty("Sports et loisirs", "Intermédiaire");
        createQuestion("Sports et loisirs", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Intermédiaire", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Intermédiaire", "", "", "", "", "", 0);

        createDifficulty("Sports et loisirs", "Expert");
        createQuestion("Sports et loisirs", "Expert", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Expert", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Expert", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Expert", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Expert", "", "", "", "", "", 0);
        createQuestion("Sports et loisirs", "Expert", "", "", "", "", "", 0);

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
