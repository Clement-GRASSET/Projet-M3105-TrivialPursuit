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
        createCategory("Histoire");
        createCategory("Science");
        createCategory("Y");
        createCategory("Nature");
        createCategory("Arts");
        createCategory("Sports");

        createDifficulty("Histoire", "Débutant");
        createDifficulty("Histoire", "Intermédiaire");
        createDifficulty("Histoire", "Expert");

        createDifficulty("Science", "Débutant");
        createDifficulty("Science", "Intermédiaire");
        createDifficulty("Science", "Expert");

        createDifficulty("Y", "Débutant");
        createDifficulty("Y", "Intermédiaire");
        createDifficulty("Y", "Expert");

        createDifficulty("Nature", "Débutant");
        createDifficulty("Nature", "Intermédiaire");
        createDifficulty("Nature", "Expert");

        createDifficulty("Arts", "Débutant");
        createDifficulty("Arts", "Intermédiaire");
        createDifficulty("Arts", "Expert");

        createDifficulty("Sports", "Débutant");
        createDifficulty("Sports", "Intermédiaire");
        createDifficulty("Sports", "Expert");

        createQuestion("Y", "Débutant", "Quelle est la bonne méthode pour être le plus efficace possible ?", "Avoir une approche scientifique", "Être rigoureux", "Avoir du talent", "Laisser tomber", 0);
        createQuestion("Y", "Intermédiaire", "Que doit-on faire afin d'être en bonne santé ?", "Aller chez le médecin", "Aller dans un désert sans provisions", "Ne pas se suicider", "Boire beaucoup d'eau", 1);
        createQuestion("Y", "Expert", "Quelle est la meilleure qualification lors d'un entretien professionnel ?", "Être qualifié", "Être allemand", "Être riche", "Avoir un background", 3);

        createQuestion("Arts", "Débutant", "Qu'est-ce qui indique qu'un produit a une grande qualité ?", "Il coûte cher de fou", "Il est recommandé par Olivier Mine", "Sardoche a tapé dessus", "Il a reçu un prix", 2);
        createQuestion("Arts", "Intermédiaire", "Quel est le symbole de la beauté ?", "Dora", "Fabio Lanzoni", "Le nombril d'Adibou", "La beauté", 3);
        createQuestion("Arts", "Expert", "Quelle viande coûte le plus ?", "Le boeuf", "Le porc", "Le poulet", "La viande d'oiseau", 3);

        createQuestion("Science", "Débutant", "Quel est le pays ayant le plus de souffrance ?", "Allemagne", "Russie", "Espagne", "Groenland", 2);
        createQuestion("Science", "Intermédiaire", "cos(π)(-exp(iπ))+ln(1) = ?", "0", "π", "√2/2", "1", 0);
        createQuestion("Science", "Expert", "Si lorsqu'une fille a deux ans, sa soeur en a la moitié soit un an, quel âge a la petite soeur quand la grande en a cent ?", "50", "99", "75", "98", 1);

        createQuestion("Histoire", "Débutant", "Où se trouve l'Australie ?", "En (-33.868857, 151.206079)", "Entre l'Afrique et l'Amérique du Sud", "À l'envers", "En Allemagne", 2);
        createQuestion("Histoire", "Intermédiaire", "Quelles sont les coordonnées de l'Allemagne ?", "(35.861660, 104.195397)", "(-85, 65)", "(35, -98)", "(51.165691, 10.451526)", 3);
        createQuestion("Histoire", "Expert", "Qu'est-ce qui intéresse le plus le France ?", "Le nombre de bureaux cassés par Sardoche", "La communication en entreprise", "Le salaire des allemands", "Si le top 15 est possible", 0);

        createQuestion("Nature", "Débutant", "Qui a le temps de fermentation le plus long ?", "Yaourt lait demi-écrémé", "L'humour", "Yaourt lait entier", "Emmental", 1);
        createQuestion("Nature", "Intermédiaire", "Qu'est-ce que la tomate ?", "Un fruit", "Un légume", "Un maskass en boule", "Mario", 2);
        createQuestion("Nature", "Expert", "Quel paiement est le plus utilisé ?", "Espèce", "Carte", "Nature", "Chèque", 2);
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
