

/**
 * Cette classe represente un simple dessin. Vous pouvez l'afficher en appelant
 * la methode dessine(). Mais il y a mieux : comme c'est un dessin electronique,
 * vous pouvez facilement le modifier. Par exemple, le passer en noir et blanc,
 * puis le remettre en couleurs (seulement apres l'avoir dessine, bien sur).
 *
 * (ecrite pour servir d'exemple au debut de l'apprentissage de Java avec BlueJ)
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  mod.by Denis BUREAU
 * @version 2006.03.30/2012.02.06
 */
public class Maison
{
    private Carre    aMur;
    private Carre    aFenetre;
    private Triangle aToit;
    private Cercle   aSoleil, aSoleil2;
    private boolean  aDejaPlace;

    /**
     * Constructor for objects of class Picture
     */
    public Maison()
    {
        this.aDejaPlace= false; // place() is needed
        
        this.aMur= new Carre();
        this.aMur.changeTaille( 100 );
        //this.aMur.depVertical(60);
        //this.aMur.depHorizontal(50);
        
        this.aFenetre= new Carre();
        this.aFenetre.changeCouleur( "black" );
        //this.aFenetre.depVertical(70);
        //this.aFenetre.depHorizontal(70);

        this.aToit= new Triangle();  
        this.aToit.changeTaille( 140, 50 );
        //this.aToit.depVertical(45);
        //this.aToit.depHorizontal(110);

        this.aSoleil= new Cercle();
        this.aSoleil.changeCouleur( "yellow" );
        this.aSoleil.changeTaille( 60 );
        
        this.aSoleil2= new Cercle();
        this.aSoleil2.changeCouleur( "blue" );
        this.aSoleil2.changeTaille( 60 );
        //this.aSoleil2.depHorizontal(210);
        
        this.dessine();
        this.place();
    } // Picture()
    
    /**
     * Returns the position of the two suns
     * @return positions the position of the 2 suns
     */
    public String getPositionsDeuxSoleils(){
        return this.getPositionSoleil(this.aSoleil, "Soleil 1") + "|" + this.getPositionSoleil(this.aSoleil2, "Soleil 1");
    }
    
    /**
     * Returns the position of a sun with his name
     * @param pNom name of the sun
     * @param pSoleil the sun
     * @return position the position of a sun
     */
    private String getPositionSoleil(final Cercle pSoleil, final String pNom){
        final int vPosition = pSoleil.getPosition();
        return pNom + "x=" + (int)(vPosition / 1000) + ", y=" + (vPosition % 1000);
    }
    
    /**
     * Draw this picture.
     */
    public void dessine()
    {
        this.aMur.rendVisible();
        this.aFenetre.rendVisible();
        this.aToit.rendVisible();
        this.aSoleil.rendVisible();
        this.aSoleil2.rendVisible();
    } // dessine()

    /**
     * Slowly move the elements to their place.
     */
    public void place()
    {
        if ( ! aDejaPlace ) {
            this.aMur.depLentVertical(80);
            this.aFenetre.depLentHorizontal(20);
            this.aFenetre.depLentVertical(100);
            this.aToit.depLentHorizontal(60);
            this.aToit.depLentVertical(70);
            this.aSoleil.depLentHorizontal(180);
            this.aSoleil.depLentVertical(-10);
            this.aDejaPlace= true;
        }
    } // place()

    /**
     * Erase the house from this picture.
     */
    public void effaceMaison()
    {
        this.aMur.rendInvisible();
        this.aFenetre.rendInvisible();
        this.aToit.rendInvisible();
    } // effaceMaison()

    /**
     * Change this picture to black/white display
     */
    public void metNoirEtBlanc()
    {
        if (this.aMur != null) { // only if it's painted already...
            this.aMur.changeCouleur(  "black");
            this.aFenetre.changeCouleur("white");
            this.aToit.changeCouleur(  "black");
            this.aSoleil.changeCouleur(   "black");
        } // if
        else {}
    } // metNoirEtBlanc()

    /**
     * Change this picture to use color display
     */
    public void metCouleurs()
    {
        if (this.aMur != null) { // only if it's painted already...
            this.aMur.changeCouleur(  "red"   );
            this.aFenetre.changeCouleur("black" );
            this.aToit.changeCouleur(  "green" );
            this.aSoleil.changeCouleur(   "yellow");
        } // if
        else {}
    } // metCouleurs()
    
} // Maison
