import java.awt.Color;
import libraries.Point;
import libraries.StdDraw;

public class InterfaceCarte{

    public InterfaceCarte (){

    }

	public void construitCarte(Carte c1){
		StdDraw.setCanvasSize(1024, 720);
		StdDraw.setXscale(-12, 1012);
		StdDraw.setYscale(-10, 710);
		StdDraw.enableDoubleBuffering();
	
		// Dessiner la carte (zone principale)
		construitZoneMap(Color.BLACK, 350, 350, 350);
		construitZoneLevel(Color.BLACK, 856, 688, 144, 12);
		construitZonePlayeur(Color.BLACK, 856, 641, 144, 25);
		construitZoneStore(Color.BLACK, 856, 303, 144, 303);
		c1.creerCase();
		construitCoeur( 972, 641, 144, 25);
		construitPiece( 740, 641, 0, 0,21);
		c1.construitChemin();
	}
	public void construitZoneMap(Color color, int centerX, int centerY, int halfLength){
		StdDraw.setPenColor (color);
		StdDraw.filledSquare ( centerX , centerY , halfLength );//carré plein
/* 		Point center = new Point(centerX, centerY);
		Point halfDist = new Point(halfLength, halfLength);
		StdDraw.rectangle (center.getX(), center.getY(), halfDist.getX (), halfDist.getY());//contoure de ma zone map */
		StdDraw.show();
		
		/*//le chemin
		StdDraw . setPenColor ( Color . BLACK );
		*/
	}
	public void construitZoneLevel(Color color, int centerX, int centerY, int halfLengthX, int halfLengthY){ 
		StdDraw.setPenColor(color);
		//StdDraw.filledRectangle(centerX, centerY, halfLengthX, halfLengthY);
		StdDraw.setPenColor(color);
		Point center = new Point(centerX, centerY);
		Point halfDist = new Point(halfLengthX, halfLengthY);
		StdDraw.rectangle (center.getX(), center.getY(), halfDist.getX (), halfDist.getY());
		StdDraw.show();
		
	}
	public void construitZonePlayeur(Color color, int centerX, int centerY, int halfLengthX, int halfLengthY){
		StdDraw.setPenColor ( color );
		//StdDraw.filledRectangle(centerX, centerY, halfLengthX, halfLengthY);
		//StdDraw.setPenColor (color);
		Point center = new Point(centerX, centerY);
		Point halfDist = new Point(halfLengthX, halfLengthY);
		StdDraw.rectangle (center.getX(), center.getY(), halfDist.getX (), halfDist.getY());
		StdDraw.show();
	}
	public void construitPiece(int centerX, int centerY, int halfLengthX, int halfLengthY, int radius){
		Point center = new Point(centerX, centerY);
		StdDraw . setPenColor ( new Color (212 , 175 ,55) );
		StdDraw . filledCircle ( center . getX () , center . getY () , radius );
		StdDraw . setPenColor ( new Color (192 , 192 ,192) );
		StdDraw . filledCircle ( center . getX () , center . getY () , 0.7 * radius );
		StdDraw.show();
	}
	public void construitCoeur(int centerX, int centerY, int halfHeightX, int halfHeightY){		//trace un coeur
		Point center = new Point(centerX, centerY);
		int halfHeight = 21;
		StdDraw . setPenColor ( new Color (223 , 75 , 95) );
		double [] listX = new double []{
			center . getX (),
			center . getX () - halfHeight,
			center . getX () - halfHeight,
			center . getX () - 0.66 * halfHeight ,
			center . getX () - 0.33 * halfHeight ,
			center . getX () ,
			center . getX () + 0.33 * halfHeight ,
			center . getX () + 0.66 * halfHeight ,
			center . getX () + halfHeight ,
			center . getX () + halfHeight ,
		};
		double [] listY = new double []{
			center . getY () - halfHeight ,
			center . getY () ,
			center . getY () + 0.5 * halfHeight ,
			center . getY () + halfHeight ,
			center . getY () + halfHeight ,
			center . getY () + 0.5 * halfHeight ,
			center . getY () + halfHeight ,
			center . getY () + halfHeight ,
			center . getY () + 0.5 * halfHeight ,
			center . getY () ,
		};
		StdDraw . filledPolygon ( listX , listY );
		StdDraw.show();
	}
	public void construitZoneStore(Color color, int centerX, int centerY, int halfLengthX, int halfLengthY){ 
		StdDraw.setPenColor (color);
		Point center = new Point(centerX, centerY);
		Point halfDist = new Point(halfLengthX, halfLengthY);
		StdDraw.rectangle (center.getX(), center.getY(), halfDist.getX (), halfDist.getY());
		StdDraw.show();
		
	}
}