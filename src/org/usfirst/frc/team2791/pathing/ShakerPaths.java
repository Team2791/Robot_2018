package org.usfirst.frc.team2791.pathing;

public class ShakerPaths {
	public static class FROM_CENTER {
		public static final Path SWITCH_RIGHT = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":50},"mid1":{"x":46,"y":48},"mid2":{"x":51,"y":109},"end":{"x":112,"y":100}} */
				(-6 + 378 * t + -399 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
				, 128));
		public static final Path SWITCH_LEFT = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":42},"end":{"x":112,"y":44}} */
				(-3 + -336 * t + 345 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
				, 130));
	}

	public static class FROM_RIGHT {
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":170,"y":241},"end":{"x":169,"y":22}} */
//		(-60 + 666 * t + -1263 * Math.pow(t, 2))/ (327 + -288 * t + -42 * Math.pow(t, 2)) 
//		, 291));
		// ^^ Path went too close to cubes. Maybe short on crossing field?
		
//		// vv added 18'' to end x and midpoint2 x to not hit cubes.
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":188,"y":241},"end":{"x":187,"y":22}} */
//		(-60 + 666 * t + -1263 * Math.pow(t, 2))/ (327 + -180 * t + -150 * Math.pow(t, 2)) 
//		, 305));
		
//		//Was hitting cubes on turn. Made turn sharper
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":220,"y":250},"end":{"x":187,"y":20}} */
//		(-60 + 720 * t + -1350 * Math.pow(t, 2))/ (327 + 12 * t + -438 * Math.pow(t, 2)) 
//		, 324));
		
//		//Was close to Platform, also changed target distance to be a longer cross.
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":190,"y":260},"end":{"x":187,"y":0}} */
//		(-60 + 780 * t + -1500 * Math.pow(t, 2))/ (327 + -168 * t + -168 * Math.pow(t, 2)) 
//		, 331));

//		// Was still close to Platform
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":190,"y":260},"end":{"x":178,"y":0}} */
//		(-60 + 780 * t + -1500 * Math.pow(t, 2))/ (327 + -168 * t + -195 * Math.pow(t, 2)) 
//		, 328));
		
		
//		// Moving away from platform
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":170,"y":260},"end":{"x":155,"y":0}} */
//		(-60 + 780 * t + -1500 * Math.pow(t, 2))/ (327 + -288 * t + -84 * Math.pow(t, 2)) 
//		, 312));

		
		// moved too far away from platform
		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":170,"y":260},"end":{"x":168,"y":0}} */
		(-60 + 780 * t + -1500 * Math.pow(t, 2))/ (327 + -288 * t + -45 * Math.pow(t, 2)) 
		, 317));

		
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":178,"y":100},"mid1":{"x":171,"y":80},"mid2":{"x":181,"y":46},"end":{"x":203,"y":60}} */
//		(-60 + -84 * t + 186 * Math.pow(t, 2))/ (-21 + 102 * t + -15 * Math.pow(t, 2)) 
//		, 63));
		// ^ old DriveIntoLeftScale
		
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/*{"start":{"x":268, "y":170}, "mid1":{"x":270, "y":65}, "mid2":{"x":281, "y":66}, "end":{"x":300, "y":60}} */
//		 (-339 * Math.pow(t, 2) + 636 * t + -315) / (-3 * Math.pow(t, 2) + 54 * t + 6), 120));
//		// ^ New DriveIntoLeftScale


//		// just took 30 in off forward drive and fixed angle
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":232,"y":100},"mid2":{"x":250,"y":37},"end":{"x":270,"y":60}} */
//		(-210 + 42 * t + 237 * Math.pow(t, 2))/ (-108 + 324 * t + -156 * Math.pow(t, 2)) 
//		, 134));
		
//		// Moved 10 right and attempt to make 30 d angle
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":233,"y":100},"mid2":{"x":220,"y":37},"end":{"x":270,"y":70}} */
//		(-210 + 42 * t + 267 * Math.pow(t, 2))/ (-105 + 132 * t + 123 * Math.pow(t, 2)) 
//		, 145));
		
		// increased angle to 43
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":233,"y":100},"mid2":{"x":225,"y":27},"end":{"x":270,"y":70}} */
//		(-210 + -18 * t + 357 * Math.pow(t, 2))/ (-105 + 162 * t + 78 * Math.pow(t, 2)) 
//		, 149));
		
		// fixed driving into the cubes
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":259,"y":102},"mid2":{"x":242,"y":38},"end":{"x":270,"y":70}} */
//		(-204 + 24 * t + 276 * Math.pow(t, 2))/ (-27 + -48 * t + 159 * Math.pow(t, 2)) 
//		, 126));
		
		//Move end point 26 in. forward
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":260,"y":102},"mid2":{"x":242,"y":25},"end":{"x":296,"y":70}} */
//		(-204 + -54 * t + 393 * Math.pow(t, 2))/ (-24 + -60 * t + 246 * Math.pow(t, 2)) 
//		, 149));
		
		//Move end point 8 in. back
		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":268,"y":170},"mid1":{"x":260,"y":102},"mid2":{"x":242,"y":25},"end":{"x":296,"y":70}} */
		(-204 + -54 * t + 393 * Math.pow(t, 2))/ (-24 + -60 * t + 246 * Math.pow(t, 2)) 
		, 149));
		
	}

	public static Path straightLength(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}