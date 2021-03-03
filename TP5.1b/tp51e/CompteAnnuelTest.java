import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CompteAnnuelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CompteAnnuelTest
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
        sClassName = "CompteAnnuel";
        sFil = sClassName + ".java";
        if ( sPkg != "" ) {
            veref.ClassContent.setRefPkg( sPkg );
            sFil = sPkg + "/" + sFil;
        }
        
        sProtoC = "( double p1, double p2 )";
    }
    
    @Test
    public void testClasse_1()
    {
        sCla = veref.V.getVClaFName( sClassName );
        sAbstract = sCla.classType().equals( "abstract class" );
        veref.V.vrai( sAbstract, "Vous ne voulez pas avoir le droit de creer des objets de la classe "+sClassName+" ???" );
        veref.V.failIf(); 
        veref.V.verifExtends( sFil, "CompteRemunere" );
    } // testClasse_1()

    @Test
    public void testAttribut_2()
    {
        testClasse_1();
        veref.V.vrai( veref.V.nbAtt( sCla ) == 1, "Etes-vous sur d'avoir besoin d'un attribut en plus de ceux d'un CompteRemunere ?" );
        veref.V.verifNbAttOp( sCla, "==", 0 );
    } // testAttribut_2()
    
    @Test
    public void testConstructeur_3()
    {
        testClasse_1();
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();
        // appeler constructeur (d'abord debugger dans CompteRemunereTest)
    } // testConstructeur_3()

    @Test
    public void testProcedure_4()
    {
        testConstructeur_3();
        sMetName = "capitaliseUnAn";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM );
        veref.V.vrai( !sMet.getModifiers().hasModifier( "abstract" ),
            "Dans "+sClassName+", on sait ce que doit faire "+sMetName+" !" );
        veref.V.failIfNot();        
        veref.V.vrai( veref.V.nbMet( sCla ) <= 1 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot();        
        veref.V.verifOverride( sFil, sMetRT, sMetName );
        veref.V.verifCount( sFil, "this.credite\\(", 1 );
        veref.V.verifCount( sFil, "this.getSolde\\(", 1 );
        veref.V.verifCount( sFil, "this.getTaux\\(", 1 );
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
}
