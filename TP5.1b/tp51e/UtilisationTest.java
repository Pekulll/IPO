import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UtilisationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UtilisationTest
{
    private static String                   sClassName;
    private static String                   sPkg;
    private static String                   sFil;
    private static veref.ClassContent       sCla;
    private static boolean                  sAbstract;
    private static String                   sAttName;
    private static String                   sAttType;
    private static veref.FieldContent       sAtt;
    private static String                   sProtoC;
    private static veref.ConstructorContent sCon;
    private static String                   sMetName;
    private static String                   sMetRT;
    private static String                   sProtoM;
    private static veref.MethodContent      sMet;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        sPkg       = "";
        sClassName = "Utilisation";
        sFil = sClassName + ".java";
        if ( sPkg != "" ) {
            veref.ClassContent.setRefPkg( sPkg );
            sFil = sPkg + "/" + sFil;
        }
        sProtoC = "()";
    }

    @Test
    public void testClasse_1()
    {
        sCla = veref.V.getVClaFName( sClassName );
        // sAbstract = sCla.classType().equals( "abstract class" ); // facultatif
        // veref.V.vrai( sAbstract, "Vous ne voulez pas avoir le droit de creer des objets de la classe "+sClassName+" ???" );
        // veref.V.failIf(); 
        veref.V.verifCount( sFil, " extends ", -1 );
        // veref.V.verifNotExtends( sFil ); // methode a creer !
    } // testClasse_1()

    @Test
    public void testAttribut_2()
    {
        testClasse_1();
        veref.V.verifNbAttOp( sCla, "==", 0 );
    } // testAttribut_2()

    @Test
    public void testConstructeur_3()
    {
        testAttribut_2();
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();        
        sCon = veref.V.getVConFProto( sCla, sProtoC ); // constructeur fourni par Java, mais
        veref.V.verifCount( sFil, sClassName+"\\(", -1 ); // pas de constructeur utilisateur
    } // testConstructeur_3()

    @Test
    public void testProcedure_4()
    {
        testConstructeur_3();
        sMetName = "essai";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProtoMod( sCla, sMetName, sMetRT, sProtoM, "public", "abstract final" );
        veref.V.vrai( sMet.getModifiers().hasModifier( "static" ),
            "Voulez-vous vraiment etre oblige de creer un objet de la classe "+sClassName
            +" pour pouvoir lancer la methode "+sMetName+" ?" );
        veref.V.failIfNot();        
        veref.V.vrai( veref.V.nbMet( sCla ) <= 1 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot();        
        // veref.V.verifCountOp( sFil, "CompteAnnuel\\(", ">=", 1 ); // methode a creer
        veref.V.verifCount(    sFil, "CompteAnnuel\\(", 1 );
        veref.V.verifCount(    sFil, "CompteMensuel\\(", 1 );
        veref.V.verifCount(    sFil, "bilanAnnuel\\(\\)", 1 );
        veref.V.verifCount(    sFil, "for\\s*\\(", 1 );
        // veref.V.verifCount(    sFil, "CompteCourant\\(", 2 );
        // veref.V.verifCount(    sFil, ".compareTo\\(", 9 );
        // veref.V.verifCount(    sFil, "System.out.println\\(", 9 );
    } // testProcedure_4()

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
} // UtilisationTest
