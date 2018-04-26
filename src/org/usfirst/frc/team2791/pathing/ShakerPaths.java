package org.usfirst.frc.team2791.pathing;

public class ShakerPaths {
	public static class FROM_CENTER {
		public static final Path SWITCH_RIGHT = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":100},"mid2":{"x":51,"y":138},"end":{"x":112,"y":138}} */
				(0 + 228 * t + -228 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
				, 121));
//----------------------------------------------------------------------------------
		public static final Path SWITCH_TO_CUBE_LEFT = new Path(new PathSegment(t -> 
		/* {"start":{"x":55,"y":154},"mid1":{"x":80,"y":160},"mid2":{"x":50,"y":110},"end":{"x":132,"y":108}} */
		(18 + -336 * t + 312 * Math.pow(t, 2))/ (75 + -330 * t + 501 * Math.pow(t, 2)) 
		, 99));
		
		//----------------------------------------------------------------------------------		
		//Less wide
		public static final Path SWITCH_LEFT = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":54},"end":{"x":112,"y":56}} */
		(-3 + -264 * t + 273 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
		, 124));
	}
	//----------------------------------------------------------------------------------

	public static class FROM_RIGHT {
		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":173,"y":260},"end":{"x":171,"y":6}} */
		(-60 + 780 * t + -1482 * Math.pow(t, 2))/ (327 + -270 * t + -63 * Math.pow(t, 2)) 
		, 314));
		//----------------------------------------------------------------------------------
		
		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":268,"y":170},"mid1":{"x":260,"y":120},"mid2":{"x":271,"y":83},"end":{"x":303,"y":99}} */
		(-150 + 78 * t + 120 * Math.pow(t, 2))/ (-24 + 114 * t + 6 * Math.pow(t, 2)) 
		, 101));
		//----------------------------------------------------------------------------------
		
		//Switched points
		public static final Path BackupFromLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":50},"mid1":{"x":260,"y":70},"mid2":{"x":273,"y":81},"end":{"x":296,"y":90}} */
		(60 + -54 * t + 21 * Math.pow(t, 2))/ (0 + 78 * t + -9 * Math.pow(t, 2)) 
		, 58));
		//----------------------------------------------------------------------------------

		//Slightly farther
		public static final Path GoToLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":60},"mid1":{"x":260,"y":61},"mid2":{"x":263,"y":97},"end":{"x":312,"y":97}} */
		(3 + 210 * t + -213 * Math.pow(t, 2))/ (0 + 18 * t + 129 * Math.pow(t, 2)) 
		, 71));
		//----------------------------------------------------------------------------------
			
		public static final Path GoToLeftCube = new Path(new PathSegment(t -> 
			/* {"start":{"x":260,"y":60},"mid1":{"x":252,"y":97},"mid2":{"x":245,"y":132},"end":{"x":245,"y":132}} */
			(111 + -12 * t + -99 * Math.pow(t, 2))/ (-24 + 6 * t + 18 * Math.pow(t, 2)) 
			, 74));
		//----------------------------------------------------------------------------------

		//Make path slightly longer
		//UNTESTED - Run on practice field before using
		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":78},"mid2":{"x":247,"y":99},"end":{"x":247,"y":99}} */
		(54 + 18 * t + -72 * Math.pow(t, 2))/ (-21 + 6 * t + 15 * Math.pow(t, 2)) 
		, 42));
		//----------------------------------------------------------------------------------

		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":200,"y":310},"end":{"x":256,"y":285}} */
		(0 + 60 * t + -135 * Math.pow(t, 2))/ (510 + -840 * t + 498 * Math.pow(t, 2)) 
		, 259));
		//----------------------------------------------------------------------------------
		
		// Switched points
		public static final Path BackRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":230,"y":320},"mid1":{"x":227,"y":305},"mid2":{"x":242,"y":288},"end":{"x":256,"y":285}} */
		(-45 + -12 * t + 48 * Math.pow(t, 2))/ (-9 + 108 * t + -57 * Math.pow(t, 2)) 
		, 48));
		//----------------------------------------------------------------------------------
	
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":253},"end":{"x":228,"y":248}} */
//		(-57 + 36 * t + 6 * Math.pow(t, 2))/ (3 + -90 * t + 51 * Math.pow(t, 2)) 
//		, 48));
		//subtracted 3 inches from the x and subtracted 10 inches from the y to get closer to the 2nd cube
		
		//too far other way
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":244},"end":{"x":225,"y":238}} */
//		(-57 + -18 * t + 57 * Math.pow(t, 2))/ (3 + -90 * t + 42 * Math.pow(t, 2)) 
//		, 58));
		
		//kept original x and decreased original y by 5
		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":244},"end":{"x":228,"y":243}} */
		(-57 + -18 * t + 72 * Math.pow(t, 2))/ (3 + -90 * t + 51 * Math.pow(t, 2)) 
		, 53));
		//----------------------------------------------------------------------------------

		//Still less overshoot
		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":304,"y":302},"mid1":{"x":297,"y":269},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
		(-99 + 366 * t + -429 * Math.pow(t, 2))/ (-21 + 42 * t + -228 * Math.pow(t, 2)) 
		, 101));
		//----------------------------------------------------------------------------------

	}

	public static Path straightPath(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}
