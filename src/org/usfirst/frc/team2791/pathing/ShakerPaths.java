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
		
		
		//Slightly farther
		public static final Path GoToLeftScale2ndDriveHACK = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":60},"mid1":{"x":260,"y":61},"mid2":{"x":263,"y":97},"end":{"x":312,"y":97}} */
		(3 + 210 * t + -213 * Math.pow(t, 2))/ (0 + 18 * t + 129 * Math.pow(t, 2)) 
		, 52)); // was 71, 65 was just too much, 60 was also a bit high 
		//----------------------------------------------------------------------------------
			
					
		public static final Path GoToLeftCube = new Path(new PathSegment(t -> 
			/* {"start":{"x":260,"y":60},"mid1":{"x":252,"y":97},"mid2":{"x":245,"y":132},"end":{"x":245,"y":132}} */
			(111 + -12 * t + -99 * Math.pow(t, 2))/ (-24 + 6 * t + 18 * Math.pow(t, 2)) 
			, 74));
		//----------------------------------------------------------------------------------

		//Make path slightly longer
		//UNTESTED - Run on practice field before using
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":78},"mid2":{"x":247,"y":99},"end":{"x":247,"y":99}} */
//		(54 + 18 * t + -72 * Math.pow(t, 2))/ (-21 + 6 * t + 15 * Math.pow(t, 2)) 
//		, 42));
			
		// this worked okay. changing to be a bit better.
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":78},"mid2":{"x":254,"y":99},"end":{"x":244,"y":101}} */
//		(54 + 18 * t + -66 * Math.pow(t, 2))/ (-21 + 48 * t + -57 * Math.pow(t, 2)) 
//		, 46));
		
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":257,"y":73},"mid2":{"x":253,"y":86},"end":{"x":244,"y":95}} */
//		(39 + 0 * t + -12 * Math.pow(t, 2))/ (-9 + -6 * t + -12 * Math.pow(t, 2)) 
//		, 39));
		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":60},"mid1":{"x":257,"y":73},"mid2":{"x":253,"y":84},"end":{"x":244,"y":91}} */
		(39 + -12 * t + -6 * Math.pow(t, 2))/ (-9 + -6 * t + -12 * Math.pow(t, 2)) 
		, 36));
//		// ^^ this path is pretty good
		
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":257,"y":73},"mid2":{"x":260,"y":87},"end":{"x":244,"y":91}} */
//		(39 + 6 * t + -33 * Math.pow(t, 2))/ (-9 + 36 * t + -75 * Math.pow(t, 2)) 
//		, 38));
		
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":257,"y":73},"mid2":{"x":260,"y":87},"end":{"x":247,"y":86}} */
//		(39 + 6 * t + -48 * Math.pow(t, 2))/ (-9 + 36 * t + -66 * Math.pow(t, 2)) 
//		, 33));
		//----------------------------------------------------------------------------------

//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":200,"y":310},"end":{"x":256,"y":285}} */
//		(0 + 60 * t + -135 * Math.pow(t, 2))/ (510 + -840 * t + 498 * Math.pow(t, 2)) 
//		, 259));
		
//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":246,"y":322},"end":{"x":256,"y":284}} */
//		(0 + 132 * t + -246 * Math.pow(t, 2))/
//(510 + -564 * t + 84 * Math.pow(t, 2)) 
//		, 266));
		
//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":266,"y":312},"end":{"x":276,"y":280}} */
//		(0 + 72 * t + -168 * Math.pow(t, 2))/
//(510 + -444 * t + -36 * Math.pow(t, 2)) 
//		, 283));
		
		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":266,"y":312},"end":{"x":273,"y":291}} */
		(0 + 72 * t + -135 * Math.pow(t, 2))/
(510 + -444 * t + -45 * Math.pow(t, 2)) 
		, 275)); // 277
		
		
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
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t ->
////		/* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":244},"end":{"x":228,"y":243}} */
////		(-57 + -18 * t + 72 * Math.pow(t, 2))/ (3 + -90 * t + 51 * Math.pow(t, 2))
////		, 53));
//		^
//		|
//		|
//		|
//		Oringinal

//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t ->
////			/* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":244},"end":{"x":222,"y":238}} */
////			(-57 + -18 * t + 57 * Math.pow(t, 2))/ (3 + -90 * t + 33 * Math.pow(t, 2))
////			, 60));

//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t ->
///* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":244},"end":{"x":232,"y":234}} */
//(-57 + -18 * t + 45 * Math.pow(t, 2))/ (3 + -90 * t + 63 * Math.pow(t, 2))
//, 57));
		
		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":255,"y":268},"mid2":{"x":240,"y":247},"end":{"x":220,"y":243}} */
//		(-51 + -24 * t + 63 * Math.pow(t, 2))/ (6 + -102 * t + 36 * Math.pow(t, 2)) 
//		, 58));
		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":251,"y":273},"mid2":{"x":238,"y":259},"end":{"x":210,"y":254}} */
//		(-36 + -12 * t + 33 * Math.pow(t, 2))/ (-6 + -66 * t + -12 * Math.pow(t, 2)) 
//		, 57));
		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":251,"y":265},"mid2":{"x":237,"y":246},"end":{"x":210,"y":254}} */
//		(-60 + 6 * t + 78 * Math.pow(t, 2))/
//		(-6 + -72 * t + -3 * Math.pow(t, 2)) 
//		, 63));
		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":251,"y":265},"mid2":{"x":245,"y":222},"end":{"x":210,"y":254}} */
//		(-60 + -138 * t + 294 * Math.pow(t, 2))/
//		(-6 + -24 * t + -75 * Math.pow(t, 2)) 
//		, 77));
		
////		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
////			/* {"start":{"x":253,"y":285},"mid1":{"x":253,"y":272},"mid2":{"x":225,"y":268},"end":{"x":210,"y":254}} */
////			(-39 + 54 * t + -57 * Math.pow(t, 2))/(0 + -168 * t + 123 * Math.pow(t, 2)) 
////			, 55)); // 55
//		
//		
//		^
//		|
//		|
//		Best Path So Far
		
		
		
		
		
		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":253,"y":285},"mid1":{"x":253,"y":272},"mid2":{"x":225,"y":268},"end":{"x":208,"y":248}} */
		(-39 + 54 * t + -75 * Math.pow(t, 2))/
(0 + -168 * t + 117 * Math.pow(t, 2)) 
		, 60));

		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":253,"y":285},"mid1":{"x":253,"y":272},"mid2":{"x":230,"y":253},"end":{"x":210,"y":245}} */
//		(-39 + -36 * t + 51 * Math.pow(t, 2))/
//		(0 + -138 * t + 78 * Math.pow(t, 2)) 
//		, 61));


		//----------------------------------------------------------------------------------

		//Still less overshoot
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":304,"y":302},"mid1":{"x":297,"y":269},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
//		(-99 + 366 * t + -429 * Math.pow(t, 2))/ (-21 + 42 * t + -228 * Math.pow(t, 2)) 
//		, 101));
		
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":319,"y":318},"mid1":{"x":318,"y":282},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
//		(-108 + 306 * t + -360 * Math.pow(t, 2))/ (-3 + -120 * t + -84 * Math.pow(t, 2)) 
//		, 124));
		
		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":319,"y":310},"mid1":{"x":318,"y":282},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
		(-84 + 258 * t + -336 * Math.pow(t, 2))/
		(-3 + -120 * t + -84 * Math.pow(t, 2)) 
		, 117));
		
		//----------------------------------------------------------------------------------

	}

	public static Path straightPath(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}
