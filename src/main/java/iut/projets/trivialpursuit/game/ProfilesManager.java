package iut.projets.trivialpursuit.game;

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


public class ProfilesManager {
    private static Document profiles_doc;
    private static File file;
    private static String path = Game.getDirectory() + "/profiles.xml";

    static List<Profile> profiles_list;

    private static Element getRoot() {
        return (Element) profiles_doc.getElementsByTagName("Root").item(0);
    }

    public static Element createProfile(String name) {
        Element element = profiles_doc.createElement("Profile");
        getRoot().appendChild(element);

        element.setAttribute("name", name);

        return element;
    }

    public static Element createColor(String profname, String colname, String category, String difficulty) {
        try {
            Element element = profiles_doc.createElement("Color");
            getProfile(profname).appendChild(element);

            element.setAttribute("color", colname);
            element.setAttribute("category", category);
            element.setAttribute("difficulty", difficulty);

            return element;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static void reset() {
        profiles_doc.removeChild(getRoot());

        Element profiles_root = profiles_doc.createElement("Root");
        profiles_doc.appendChild(profiles_root);

        createProfiles();
        save();
    }

    public static void save() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(profiles_doc);
            StreamResult result = new StreamResult(file);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void load() {
        file = new File(path);
        profiles_list = new ArrayList<>();

        try {
            if (file.exists()) {
                profiles_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                System.out.println("Profile chargé");
                //System.out.println(file.getAbsolutePath());
            }
            else {
                profiles_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                Element profile_root = profiles_doc.createElement("Root");
                profiles_doc.appendChild(profile_root);

                createProfiles();

                save();
                System.out.println("Profile créé");
                //System.out.println(file.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        NodeList proflist = getRoot().getElementsByTagName("Profile");
        //System.out.println("Length : " + proflist.getLength());

        for (int i = 0 ; i < proflist.getLength() ; i++) {
            Element profnode = (Element) proflist.item(i);
            String profname = profnode.getAttribute("name");

            NodeList collist = profnode.getElementsByTagName("Color");
            //System.out.println("Length : " + collist.getLength());

            for (int j = 0 ; j < collist.getLength() ; j++) {
                Element colnode = (Element) collist.item(j);
                Profile profile = new Profile();

                profile.setName(profname);
                profile.setCategory(TrivialPursuitColor.valueOf(colnode.getAttribute("color")), colnode.getAttribute("category"), colnode.getAttribute("difficulty"));

                profiles_list.add(profile);
                //System.out.println("Profile : " + profiles_list.get(j).getName());
            }
        }
    }

    private static Element getProfile(String name) throws Exception {
        profiles_doc.getDocumentElement().normalize();

        NodeList list = profiles_doc.getElementsByTagName("Profile");

        for (int i = 0 ; i < list.getLength() ; i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;

                if (name.equals(element.getAttribute("name")))
                    return element;
            }
        }

        throw new Exception("Profile " + name + " introuvable !");
    }

    public static void createProfiles() {
        createProfile("Profile 1");
        createColor("Profile 1", "PURPLE", "Y", "Débutant");
        createColor("Profile 1", "BLUE", "Arts", "Débutant");
        createColor("Profile 1", "PINK", "Sports", "Débutant");
        createColor("Profile 1", "YELLOW", "Science", "Débutant");
        createColor("Profile 1", "ORANGE", "Histoire", "Débutant");
        createColor("Profile 1", "GREEN", "Nature", "Débutant");

        createProfile("Profile 2");
        createColor("Profile 2", "PURPLE", "Y", "Intermédiaire");
        createColor("Profile 2", "BLUE", "Arts", "Intermédiaire");
        createColor("Profile 2", "PINK", "Sports", "Intermédiaire");
        createColor("Profile 2", "YELLOW", "Science", "Intermédiaire");
        createColor("Profile 2", "ORANGE", "Histoire", "Intermédiaire");
        createColor("Profile 2", "GREEN", "Nature", "Intermédiaire");

        createProfile("Profile 3");
        createColor("Profile 3", "PURPLE", "Y", "Expert");
        createColor("Profile 3", "BLUE", "Arts", "Expert");
        createColor("Profile 3", "PINK", "Sports", "Expert");
        createColor("Profile 3", "YELLOW", "Science", "Expert");
        createColor("Profile 3", "ORANGE", "Histoire", "Expert");
        createColor("Profile 3", "GREEN", "Nature", "Expert");
    }
}