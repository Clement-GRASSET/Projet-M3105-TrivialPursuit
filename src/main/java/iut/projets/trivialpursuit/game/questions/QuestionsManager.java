package iut.projets.trivialpursuit.game.questions;

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

public class QuestionsManager {

    static Map<String, Map<String, List<Question>>> question_list;

    private static Document questions_doc;
    private static File file;

    private static Element getRoot() {
        return (Element) questions_doc.getElementsByTagName("Root").item(0);
    }

    public static Element createCategory(String name) {
        Element element = questions_doc.createElement("Category");
        getRoot().appendChild(element);

        element.setAttribute("name", name);

        return element;
    }

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

    public static void createQuestions() {
        createCategory("Histoire");
        createCategory("Science");
        createCategory("Y");
        createCategory("Nature");

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

        createQuestion("Y", "Intermédiaire", "Que doit-on faire afin d'être en bonne santé ?", "Aller chez le médecin", "Aller dans un désert sans provisions", "Ne pas se suicider", "Boire beaucoup d'eau", 1);
        createQuestion("Y", "Débutant", "Quel est la bonne méthode pour être le plus efficace possible ?", "Avoir une approche scientifique", "Être rigoureux", "Avoir du talent", "Laisser tomber", 0);
    }


    public static void reset() {
        questions_doc.removeChild(getRoot());

        Element questions_root = questions_doc.createElement("Root");
        questions_doc.appendChild(questions_root);

        createQuestions();
        save();
    }

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

    public static void load() {
        file = new File("questions.xml");
        question_list = new HashMap<>();

        try {
            if (file.exists()) {
                questions_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                System.out.println("Chargé");
            } else {
                questions_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                Element questions_root = questions_doc.createElement("Root");
                questions_doc.appendChild(questions_root);

                createQuestions();

                save();
                System.out.println("Créé");
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

                        question.answer = new String[] {qnode.getChildNodes().item(1).getTextContent(),
                                                        qnode.getChildNodes().item(3).getTextContent(),
                                                        qnode.getChildNodes().item(5).getTextContent(),
                                                        qnode.getChildNodes().item(7).getTextContent()};

                        question.right = Integer.parseInt( qnode.getAttribute("right") );

                        //showQuestion(question);

                        question_list.get(catname).get(diffname).add(question);
                    }
                }
            }

        //showQuestion(question_list.get("Y").get("Débutant").get(0));
    }


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

    private static void showQuestion(Question question)
    {
        System.out.println("Question : " + question.question + " - Réponses : "
                + question.answer[0] + " ; " + question.answer[1] + " ; " + question.answer[2] + " ; " + question.answer[3]
                + " -  Bonne réponse : " + question.right);
    }

    public static Question getRandomQuestion(String category, String difficulty) {
        Map<String, Map<String, List<Question>>> categories = question_list;
        System.out.println("Categories :");
        System.out.println(categories);

        Map<String, List<Question>> difficulties = question_list.get(category);
        System.out.println("Difficulties :");
        System.out.println(difficulties);

        List<Question> questions = difficulties.get(difficulty);
        System.out.println("Questions :");
        System.out.println(questions);

        Random random = new Random();
        Question question = questions.get( random.nextInt(questions.size()) );
        System.out.println("Question :");
        System.out.println(question);

        return question;
    }
}
