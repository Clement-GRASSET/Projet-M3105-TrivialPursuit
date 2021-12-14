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

/**
 * Gère la création de profiles
 */

public class ProfilesManager {
    private static Document profiles_doc;
    private static File file;
    private static String path = Game.getDirectory() + "/profiles.xml";

    static List<Profile> profiles_list;

    /**
     * @return Racine du XML
     */
    private static Element getRoot() {
        return (Element) profiles_doc.getElementsByTagName("Root").item(0);
    }

    /**
     * Crée une balise Profile dans le XML
     * @param name Nom du profile
     * @return Profile créé
     */
    public static Element createProfile(String name) {
        Element element = profiles_doc.createElement("Profile");
        getRoot().appendChild(element);

        element.setAttribute("name", name);

        return element;
    }

    /**
     * Crée une balise Color dans le XML
     * @param profname Nom du profile père de la couleur
     * @param colname Couleur
     * @param category Catégorie de la question à laquelle la couleur est affiliée
     * @param difficulty Difficulté de la question à laquelle la couleur est affiliée
     * @return Color créée
     */
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

    /**
     * Redéfinie le XML
     */
    public static void reset() {
        profiles_doc.removeChild(getRoot());

        Element profiles_root = profiles_doc.createElement("Root");
        profiles_doc.appendChild(profiles_root);

        createProfiles();
        save();
    }

    /**
     * Crée le fichier XML
     */
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

    /**
     * Charge le fichier XML s'il existe déjà sinon le crée
     */
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

            Profile profile = new Profile();
            profile.setName(profname);
            profiles_list.add(profile);

            for (int j = 0 ; j < collist.getLength() ; j++) {
                Element colnode = (Element) collist.item(j);

                profile.setCategory(TrivialPursuitColor.valueOf(colnode.getAttribute("color")), colnode.getAttribute("category"), colnode.getAttribute("difficulty"));

                //System.out.println("Profile : " + profiles_list.get(j).getName());
            }
        }
    }

    /**
     *
     * @param name Nom du Profile
     * @return Profile recherché
     * @throws Exception
     */
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

    /**
     * Crée le contenu du fichier XML
     */
    public static void createProfiles() {
        createProfile("Débutant");
        createColor("Débutant", "PURPLE", "Géographie", "Débutant");
        createColor("Débutant", "BLUE", "Divertissements", "Débutant");
        createColor("Débutant", "PINK", "Histoire", "Débutant");
        createColor("Débutant", "YELLOW", "Art et littérature", "Débutant");
        createColor("Débutant", "ORANGE", "Science et nature", "Débutant");
        createColor("Débutant", "GREEN", "Sports et loisirs", "Débutant");

        createProfile("Intermédiaire");
        createColor("Intermédiaire", "PURPLE", "Géographie", "Intermédiaire");
        createColor("Intermédiaire", "BLUE", "Divertissements", "Intermédiaire");
        createColor("Intermédiaire", "PINK", "Histoire", "Intermédiaire");
        createColor("Intermédiaire", "YELLOW", "Art et littérature", "Intermédiaire");
        createColor("Intermédiaire", "ORANGE", "Science et nature", "Intermédiaire");
        createColor("Intermédiaire", "GREEN", "Sports et loisirs", "Intermédiaire");

        createProfile("Expert");
        createColor("Expert", "PURPLE", "Géographie", "Expert");
        createColor("Expert", "BLUE", "Divertissements", "Expert");
        createColor("Expert", "PINK", "Histoire", "Expert");
        createColor("Expert", "YELLOW", "Art et littérature", "Expert");
        createColor("Expert", "ORANGE", "Science et nature", "Expert");
        createColor("Expert", "GREEN", "Sports et loisirs", "Expert");
    }

    public static int getProfilesListSize() {
        return  profiles_list.size();
    }

    public static Profile getProfiles(int i) {
        return profiles_list.get(i);
    }

    public static Profile getDefaultProfile() {
        return profiles_list.get(0);
    }
}