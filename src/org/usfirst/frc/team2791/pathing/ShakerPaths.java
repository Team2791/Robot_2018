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
		
		// Was still close to Platform
		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":190,"y":260},"end":{"x":178,"y":0}} */
		(-60 + 780 * t + -1500 * Math.pow(t, 2))/ (327 + -168 * t + -195 * Math.pow(t, 2)) 
		, 328));
		// ^^ this path gave a good enough result. Still would like to drive further but that's okay for now.
		
		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":178,"y":100},"mid1":{"x":171,"y":80},"mid2":{"x":181,"y":46},"end":{"x":203,"y":60}} */
		(-60 + -84 * t + 186 * Math.pow(t, 2))/ (-21 + 102 * t + -15 * Math.pow(t, 2)) 
		, 63));
		
		
		
		
		
		// vv not sure what this path is doing.
//		public static final Path TravelToLeftScale = new Path(
//				straightLength(156).getPathAtDistance(10),
//				new PathSegment(t -> 
//				/* {"start":{"x":0,"y":230},"mid1":{"x":109,"y":242},"mid2":{"x":34,"y":39},"end":{"x":69,"y":0}} */
//				(36 + -1290 * t + 1137 * Math.pow(t, 2))/ (327 + -1104 * t + 882 * Math.pow(t, 2)) 
//				, 271)
//				);
	}

	public static Path straightLength(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}