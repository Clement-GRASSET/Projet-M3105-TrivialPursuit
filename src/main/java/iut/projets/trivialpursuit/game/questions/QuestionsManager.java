package iut.projets.trivialpursuit.game.questions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QuestionsManager {

    Map<String, List<Question>> question_list;
    private static Document questions_doc;

    private static Element getRoot()
    {
        return (Element) questions_doc.getElementsByTagName("Root").item(0);
    }

    public static Element createCategory(String name)
    {
        Element element = questions_doc.createElement("Category");
        getRoot().appendChild(element);

        return element;
    }

    public static void load()
    {
        File file = new File("questions.xml");

        try {
            if (file.exists()) {
                questions_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                System.out.println("Chargé");
            }
            else {
                questions_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                createQuestions();

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(questions_doc);
                StreamResult result = new StreamResult(file);

                transformer.transform(source, result);
                System.out.println("Créé");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element getCategory(String name)throws Exception
    {
        questions_doc.getDocumentElement().normalize();

        NodeList list = questions_doc.getElementsByTagName("Category");

        for (int i = 0 ; i < list.getLength() ; i++) {
            Node node  = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;

                if (name.equals(element.getAttribute("name")))
                    return element;
            }
        }

        throw new Exception("Catégorie " + name + " introuvable !");
    }

    public static void createQuestions() {/*
        try {
            //<Root>
            Element questions_root = questions_doc.createElement("Root");
            questions_doc.appendChild(questions_root);

            //<Category>
            Element category_h = questions_doc.createElement("Category");
            questions_root.appendChild(category_h);

            //<Name>
            Element cath_name = questions_doc.createElement("Name");
            cath_name.appendChild(questions_doc.createTextNode("Histoire"));
            category_h.appendChild(cath_name);
            //</Name>

            //<Difficulty>
            Element hdifficulty_e = questions_doc.createElement("Difficulty");
            category_h.appendChild(hdifficulty_e);

            //<Name>
            Element hdifficulty_ename = questions_doc.createElement("Name");
            hdifficulty_ename.appendChild(questions_doc.createTextNode("Facile"));
            hdifficulty_e.appendChild(hdifficulty_ename);
            //</Name>

            //<Question>
            Element hquestion1_e = questions_doc.createElement("Question");
            hdifficulty_e.appendChild(hquestion1_e);

            //<Text>
            Element hq1_e_text = questions_doc.createElement("Text");
            hq1_e_text.appendChild(questions_doc.createTextNode("Où se trouve l'Australie ?"));
            hquestion1_e.appendChild(hq1_e_text);
            //</Text>

            //<Answers>
            Element hq1_e_answers = questions_doc.createElement("Answers");
            hquestion1_e.appendChild(hq1_e_answers);

            //<Answer>
            Element hq1_e_answer1 = questions_doc.createElement("Answer");
            hq1_e_answer1.appendChild(questions_doc.createTextNode("A - Dans l'océan Indien"));
            hq1_e_answers.appendChild(hq1_e_answer1);
            //</Answer>

            //<Answer>
            Element hq1_e_answer2 = questions_doc.createElement("Answer");
            hq1_e_answer2.appendChild(questions_doc.createTextNode("B - En Océanie"));
            hq1_e_answers.appendChild(hq1_e_answer2);
            //</Answer>

            //<Answer>
            Element hq1_e_answer3 = questions_doc.createElement("Answer");
            hq1_e_answer3.appendChild(questions_doc.createTextNode("C - À l'envers"));
            hq1_e_answers.appendChild(hq1_e_answer3);
            //<Answer>
            //</Answers>

            //<RightAnswer>
            Element hq1_e_r_answer = questions_doc.createElement("RightAnswer");
            hq1_e_r_answer.appendChild(questions_doc.createTextNode("2"));
            hquestion1_e.appendChild(hq1_e_r_answer);
            //</RightAnswer>
            //</Question>

            //<Difficulty>
            Element hdifficulty_i = questions_doc.createElement("Difficulty");
            category_h.appendChild(hdifficulty_i);

            //<Name>
            Element hdifficulty_iname = questions_doc.createElement("Name");
            hdifficulty_iname.appendChild(questions_doc.createTextNode("Intermédiaire"));
            hdifficulty_i.appendChild(hdifficulty_iname);
            //</Name>

            //<Question>
            Element hquestion1_i  = questions_doc.createElement("Question");
            hdifficulty_i.appendChild(hquestion1_i);

            //<Text>
            Element hq1_i_text = questions_doc.createElement("Text");
            hq1_i_text.appendChild(questions_doc.createTextNode("Que doit-on faire pour être en bonne santé ?"));
            hquestion1_i.appendChild(hq1_i_text);
            //</Text>

            //<Answers>
            Element hq1_i_answers = questions_doc.createElement("Answers");
            hquestion1_i.appendChild(hq1_i_answers);

            //<Answer>
            Element hq1_i_answer1 = questions_doc.createElement("Answer");
            hq1_i_answer1.appendChild(questions_doc.createTextNode("A - Ne pas se suicider"));
            hq1_i_answers.appendChild(hq1_i_answer1);
            //</Answer>

            //<Answer>
            Element hq1_i_answer2 = questions_doc.createElement("Answer");
            hq1_i_answer2.appendChild(questions_doc.createTextNode("B - Aller dans un désert sans provisions"));
            hq1_i_answers.appendChild(hq1_i_answer2);
            //</Answer>

            //<Answer>
            Element hq1_i_answer3 = questions_doc.createElement("Answer");
            hq1_i_answer3.appendChild(questions_doc.createTextNode("C - Boire de l'eau"));
            hq1_i_answers.appendChild(hq1_i_answer3);
            //<Answer>
            //</Answers>

            //<RightAnswer>
            Element hq1_i_r_answer = questions_doc.createElement("RightAnswer");
            hq1_i_r_answer.appendChild(questions_doc.createTextNode("1"));
            hquestion1_i.appendChild(hq1_i_r_answer);
            //</RightAnswer>
            //</Question>
            //</Difficulty>




            //<Category>
            Element category_sci = questions_doc.createElement("Category");
            questions_root.appendChild(category_sci);

            //<Name>
            Element catsci_name = questions_doc.createElement("Name");
            catsci_name.appendChild(questions_doc.createTextNode("Science"));
            category_sci.appendChild(catsci_name);
            //</Name>

            //<Difficulty>
            Element scidifficulty_i = questions_doc.createElement("Difficulty");
            category_sci.appendChild(scidifficulty_i);

            //<Name>
            Element scidifficulty_iname = questions_doc.createElement("Name");
            scidifficulty_iname.appendChild(questions_doc.createTextNode("Intermédiaire"));
            scidifficulty_i.appendChild(scidifficulty_iname);
            //</Name>

            //<Question>
            Element sciquestion1_i = questions_doc.createElement("Question");
            scidifficulty_i.appendChild(sciquestion1_i);

            //<Text>
            Element sciq1_i_text = questions_doc.createElement("Text");
            sciq1_i_text.appendChild(questions_doc.createTextNode("Que doit-on faire afin d'être le plus efficace possible ?"));
            sciquestion1_i.appendChild(sciq1_i_text);
            //</Text>

            //<Answers>
            Element sciq1_i_answers = questions_doc.createElement("Answers");
            sciquestion1_i.appendChild(sciq1_i_answers);

            //<Answer>
            Element sciq1_i_answer1 = questions_doc.createElement("Answer");
            sciq1_i_answer1.appendChild(questions_doc.createTextNode("A - Avoir une approche scientifique"));
            sciq1_i_answers.appendChild(sciq1_i_answer1);
            //</Answer>

            //<Answer>
            Element sciq1_i_answer2 = questions_doc.createElement("Answer");
            sciq1_i_answer2.appendChild(questions_doc.createTextNode("B - Être méthodique"));
            sciq1_i_answers.appendChild(sciq1_i_answer2);
            //</Answer>

            //<Answer>
            Element sciq1_i_answer3 = questions_doc.createElement("Answer");
            sciq1_i_answer3.appendChild(questions_doc.createTextNode("C - Laisser tomber"));
            sciq1_i_answers.appendChild(sciq1_i_answer3);
            //<Answer>
            //</Answers>

            //<RightAnswer>
            Element sciq1_i_r_answer = questions_doc.createElement("RightAnswer");
            sciq1_i_r_answer.appendChild(questions_doc.createTextNode("0"));
            sciquestion1_i.appendChild(sciq1_i_r_answer);
            //</RightAnswer>
            //</Question>
            //</Difficulty>
            //</Category>
            //</Root>
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    public static void getQuestion(String searched)
    {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("questions.xml"));

            //root element
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("category");

            for (int i = 0 ; i < list.getLength() ; i++)
            {
                //category
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    //element.getAttribute();
                    System.out.println(element.getElementsByTagName(searched).item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
