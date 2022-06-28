package assignment;

public abstract class GraphicalObject {
	public abstract void transform(double[][] transformMatrix);
	
	public void rotateXAxis(double angle) {		// use transform with rotation matrix around x axis
		double [][] rotationMatrix = {	{	1,		0,					0					},
										{	0, 		Math.cos(angle), 	-Math.sin(angle)	}, 
										{	0,		Math.sin(angle),	Math.cos(angle)		}};
		transform(rotationMatrix);
	}
	
	public void rotateYAxis(double angle) {		// use transform with rotation matrix around y axis
		double [][] rotationMatrix = {	{	Math.cos(angle),	0,	Math.sin(angle)	},
										{	0, 					1, 	0				}, 
										{	-Math.sin(angle),	0,	Math.cos(angle)	}};
		transform(rotationMatrix);
	}
	
	public void rotateZAxis(double angle) {		// use transform with rotation matrix around z axis
		double [][] rotationMatrix = {	{	Math.cos(angle),	-Math.sin(angle),	0	},
										{	Math.sin(angle), 	Math.cos(angle), 	0	}, 
										{	0,					0,					1	}};
		transform(rotationMatrix);
	}
}
