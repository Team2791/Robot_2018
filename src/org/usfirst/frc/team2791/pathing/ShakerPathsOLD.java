package org.usfirst.frc.team2791.pathing;

public class ShakerPathsOLD {
	public static class FROM_CENTER {
		public static final Path SWITCH_RIGHT = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":100},"mid2":{"x":51,"y":138},"end":{"x":112,"y":138}} */
				(0 + 228 * t + -228 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
				, 121));
		//Initial Path
//		public static final Path SWITCH_TO_CUBE_LEFT = new Path(new PathSegment(t -> 
//		/* {"start":{"x":70,"y":160},"mid1":{"x":120,"y":160},"mid2":{"x":90,"y":110},"end":{"x":132,"y":108}} */
//		(0 + -300 * t + 294 * Math.pow(t, 2))/ (150 + -480 * t + 456 * Math.pow(t, 2)) 
//		, 90));
		
		// Good Path
//		public static final Path SWITCH_TO_CUBE_LEFT = new Path(new PathSegment(t -> 
//		/* {"start":{"x":55,"y":160},"mid1":{"x":80,"y":160},"mid2":{"x":50,"y":110},"end":{"x":132,"y":108}} */
//		(0 + -300 * t + 294 * Math.pow(t, 2))/ (75 + -330 * t + 501 * Math.pow(t, 2)) 
//		, 103));
		public static final Path SWITCH_TO_CUBE_LEFT = new Path(new PathSegment(t -> 
		/* {"start":{"x":55,"y":154},"mid1":{"x":80,"y":160},"mid2":{"x":50,"y":110},"end":{"x":132,"y":108}} */
		(18 + -336 * t + 312 * Math.pow(t, 2))/ (75 + -330 * t + 501 * Math.pow(t, 2)) 
		, 99));
		
		
//		public static final Path SWITCH_LEFT = new Path(new PathSegment(t -> 
//			/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":42},"end":{"x":112,"y":44}} */
//			(-3 + -336 * t + 345 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
//			, 130));
		
		//Less wide
		public static final Path SWITCH_LEFT = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":54},"end":{"x":112,"y":56}} */
		(-3 + -264 * t + 273 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
		, 124));
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
//		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":170,"y":260},"end":{"x":168,"y":0}} */
//		(-60 + 780 * t + -1500 * Math.pow(t, 2))/ (327 + -288 * t + -45 * Math.pow(t, 2)) 
//		, 317));

		//Farther forwards and less far sideways
		public static final Path TravelToLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":170},"mid1":{"x":109,"y":150},"mid2":{"x":173,"y":260},"end":{"x":171,"y":6}} */
		(-60 + 780 * t + -1482 * Math.pow(t, 2))/ (327 + -270 * t + -63 * Math.pow(t, 2)) 
		, 314));
		
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
		
//		//Move end point 8 in. back
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":260,"y":102},"mid2":{"x":242,"y":25},"end":{"x":296,"y":70}} */
//		(-204 + -54 * t + 393 * Math.pow(t, 2))/ (-24 + -60 * t + 246 * Math.pow(t, 2)) 
//		, 149));
		
//		//Make turn less sharp
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":255,"y":120},"mid2":{"x":260,"y":35},"end":{"x":288,"y":70}} */
//		(-150 + -210 * t + 465 * Math.pow(t, 2))/ (-39 + 108 * t + 15 * Math.pow(t, 2)) 
//		, 129));
		
//		// changed to a 90 degree turn
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":255,"y":120},"mid2":{"x":258,"y":78},"end":{"x":288,"y":78}} */
//		(-150 + 48 * t + 102 * Math.pow(t, 2))/ (-39 + 96 * t + 33 * Math.pow(t, 2)) 
//		, 108));

		// made the turn a touch less sharp to compensate for over turn and changed the end point.
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":255,"y":120},"mid2":{"x":260,"y":91},"end":{"x":290,"y":86}} */
//		(-150 + 126 * t + 9 * Math.pow(t, 2))/ (-39 + 108 * t + 21 * Math.pow(t, 2)) 
//		, 100));

//		// made turn a touch sharper and moved end point closer to ramps
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":245,"y":120},"mid2":{"x":260,"y":91},"end":{"x":290,"y":100}} */
//		(-150 + 126 * t + 51 * Math.pow(t, 2))/ (-69 + 228 * t + -69 * Math.pow(t, 2)) 
//		, 97));
		
		// made turn sharper to hit scale more reliabley
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":245,"y":120},"mid2":{"x":260,"y":86},"end":{"x":290,"y":100}} */
//		(-150 + 96 * t + 96 * Math.pow(t, 2))/ (-69 + 228 * t + -69 * Math.pow(t, 2)) 
//		, 99));
		
//		// get further from ramps and back up a hair.
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":245,"y":120},"mid2":{"x":260,"y":92},"end":{"x":286,"y":106}} */
//		(-150 + 132 * t + 60 * Math.pow(t, 2))/ (-69 + 228 * t + -81 * Math.pow(t, 2)) 
//		, 92));
		
//		// get a hair closer
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":245,"y":120},"mid2":{"x":260,"y":90},"end":{"x":292,"y":106}} */
//		(-150 + 120 * t + 78 * Math.pow(t, 2))/ (-69 + 228 * t + -63 * Math.pow(t, 2)) 
//		, 98));
		
//		//Even closer
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":245,"y":120},"mid2":{"x":263,"y":87},"end":{"x":295,"y":103}} */
//		(-150 + 102 * t + 96 * Math.pow(t, 2))/ (-69 + 246 * t + -81 * Math.pow(t, 2)) 
//		, 101));
		
		//Too close to platform
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":245,"y":120},"mid2":{"x":263,"y":93},"end":{"x":295,"y":109}} */
//		(-150 + 138 * t + 60 * Math.pow(t, 2))/ (-69 + 246 * t + -81 * Math.pow(t, 2)) 
//		, 97));
		
		//10 inch closer to wall and swing less
//		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":268,"y":170},"mid1":{"x":256,"y":120},"mid2":{"x":263,"y":83},"end":{"x":295,"y":99}} */
//		(-150 + 78 * t + 120 * Math.pow(t, 2))/ (-36 + 114 * t + 18 * Math.pow(t, 2)) 
//		, 99));
		
		//Launching foul so we move closer to scale
		
		public static final Path DriveIntoLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":268,"y":170},"mid1":{"x":260,"y":120},"mid2":{"x":271,"y":83},"end":{"x":303,"y":99}} */
		(-150 + 78 * t + 120 * Math.pow(t, 2))/ (-24 + 114 * t + 6 * Math.pow(t, 2)) 
		, 101));
		
		
//		public static final Path BackupFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":296,"y":90},"mid1":{"x":280,"y":90},"mid2":{"x":260,"y":50},"end":{"x":260,"y":0}} */
//		(0 + -240 * t + 90 * Math.pow(t, 2))/ (-48 + -24 * t + 72 * Math.pow(t, 2)) 
//		, 103));
		
		// increased start angle
//		public static final Path BackupFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":296,"y":90},"mid1":{"x":273,"y":82},"mid2":{"x":263,"y":50},"end":{"x":260,"y":0}} */
//		(-24 + -144 * t + 18 * Math.pow(t, 2))/ (-69 + 78 * t + -18 * Math.pow(t, 2)) 
//		, 102));
		
		// made path WAY shorter
//		public static final Path BackupFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":296,"y":90},"mid1":{"x":273,"y":81},"mid2":{"x":260,"y":70},"end":{"x":260,"y":50}} */
//		(-27 + -12 * t + -21 * Math.pow(t, 2))/ (-69 + 60 * t + 9 * Math.pow(t, 2)) 
//		, 58));
		
		//Switched points
		public static final Path BackupFromLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":50},"mid1":{"x":260,"y":70},"mid2":{"x":273,"y":81},"end":{"x":296,"y":90}} */
		(60 + -54 * t + 21 * Math.pow(t, 2))/ (0 + 78 * t + -9 * Math.pow(t, 2)) 
		, 58));
		
//		public static final Path GetCube = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":265,"y":50},"mid2":{"x":250,"y":80},"end":{"x":230,"y":93}} */
//		(-30 + 240 * t + -171 * Math.pow(t, 2))/ (15 + -120 * t + 45 * Math.pow(t, 2)) 
//		, 50));
		
//		//Take of six inches
//		public static final Path GetCube = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":265,"y":50},"mid2":{"x":250,"y":80},"end":{"x":224,"y":84}} */
//		(-30 + 240 * t + -198 * Math.pow(t, 2))/ (15 + -120 * t + 27 * Math.pow(t, 2)) 
//		, 49));
		
		// Move toward cube more
//		public static final Path GetCube = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":265,"y":50},"mid2":{"x":250,"y":80},"end":{"x":235,"y":79}} */
//		(-30 + 240 * t + -213 * Math.pow(t, 2))/ (15 + -120 * t + 60 * Math.pow(t, 2)) 
//		, 38));
//		
		//Adding 10 inches to grab cube
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":265,"y":50},"mid2":{"x":250,"y":80},"end":{"x":245,"y":79}} */
//		(-30 + 240 * t + -213 * Math.pow(t, 2))/ (15 + -120 * t + 90 * Math.pow(t, 2)) 
//		, 30));
		
		//Make robot go more right
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":265,"y":50},"mid2":{"x":250,"y":80},"end":{"x":250,"y":79}} */
//		(-30 + 240 * t + -213 * Math.pow(t, 2))/ (15 + -120 * t + 105 * Math.pow(t, 2)) 
//		, 27));
		
//		//Saturday 4/21
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":255,"y":70},"mid2":{"x":260,"y":114},"end":{"x":245,"y":114}} */
//		(30 + 204 * t + -234 * Math.pow(t, 2))/ (-15 + 60 * t + -90 * Math.pow(t, 2)) 
//		, 60));
		
		//Move less far right
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":255,"y":70},"mid2":{"x":255,"y":104},"end":{"x":235,"y":104}} */
//		(30 + 144 * t + -174 * Math.pow(t, 2))/ (-15 + 30 * t + -75 * Math.pow(t, 2)) 
//		, 55));
		
		//Come in sharper
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":255,"y":70},"mid2":{"x":265,"y":104},"end":{"x":235,"y":104}} */
//		(30 + 144 * t + -174 * Math.pow(t, 2))/ (-15 + 90 * t + -165 * Math.pow(t, 2)) 
//		, 58));
		
		//Come in shallower
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":255,"y":70},"mid2":{"x":265,"y":80},"end":{"x":235,"y":104}} */
//		(30 + 0 * t + 42 * Math.pow(t, 2))/ (-15 + 90 * t + -165 * Math.pow(t, 2)) 
//		, 53));
		
		//Path too long
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":255,"y":70},"mid2":{"x":265,"y":80},"end":{"x":245,"y":94}} */
//		(30 + 0 * t + 12 * Math.pow(t, 2))/ (-15 + 90 * t + -135 * Math.pow(t, 2)) 
//		, 40));
		
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":268,"y":82},"mid2":{"x":245,"y":94},"end":{"x":245,"y":94}} */
//		(66 + -60 * t + -6 * Math.pow(t, 2))/ (24 + -186 * t + 162 * Math.pow(t, 2)) 
//		, 41));
		//too far
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":271,"y":105},"mid2":{"x":245,"y":104},"end":{"x":245,"y":104}} */
//		(135 + -276 * t + 141 * Math.pow(t, 2))/ (33 + -222 * t + 189 * Math.pow(t, 2)) 
//		, 54));
//		
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":252,"y":88},"mid2":{"x":245,"y":104},"end":{"x":245,"y":104}} */
//		(84 + -72 * t + -12 * Math.pow(t, 2))/ (-24 + 6 * t + 18 * Math.pow(t, 2)) 
//		, 47));
//		
		//endpoint too far out
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":73},"mid2":{"x":238,"y":96},"end":{"x":238,"y":96}} */
//		(39 + 60 * t + -99 * Math.pow(t, 2))/ (-21 + -48 * t + 69 * Math.pow(t, 2)) 
//		, 43));
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":72},"mid2":{"x":238,"y":96},"end":{"x":242,"y":97}} */
//		(36 + 72 * t + -105 * Math.pow(t, 2))/ (-21 + -48 * t + 81 * Math.pow(t, 2)) 
//		, 43));
//		public static final Path GetCubeFromLeftScale= new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":80},"mid2":{"x":245,"y":96},"end":{"x":245,"y":96}} */
//		(60 + -24 * t + -36 * Math.pow(t, 2))/ (-21 + -6 * t + 27 * Math.pow(t, 2)) 
//		, 40));
//		public static final Path GoToLeftScale = new Path(new PathSegment(t->
//		/* {"start":{"x":260,"y":60},"mid1":{"x":263,"y":91},"mid2":{"x":295,"y":84},"end":{"x":295,"y":84}} */
//		(93 + -288 * t + 135 * Math.pow(t, 2))/ (9 + 174 * t + -183 * Math.pow(t, 2)) 
//		, 49));
//		public static final Path GoToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":260,"y":61},"mid2":{"x":263,"y":91},"end":{"x":295,"y":84}} */
//		(3 + 174 * t + -198 * Math.pow(t, 2))/ (0 + 18 * t + 78 * Math.pow(t, 2)) 
//		, 50));
//		public static final Path GoToLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":260,"y":61},"mid2":{"x":263,"y":91},"end":{"x":295,"y":91}} */
//		(3 + 174 * t + -177 * Math.pow(t, 2))/ (0 + 18 * t + 78 * Math.pow(t, 2)) 
//		, 52));
//			public static final Path GoToLeftScale = new Path(new PathSegment(t -> 
//	/* {"start":{"x":260,"y":60},"mid1":{"x":260,"y":61},"mid2":{"x":263,"y":91},"end":{"x":309,"y":91}} */
//	(3 + 174 * t + -177 * Math.pow(t, 2))/ (0 + 18 * t + 120 * Math.pow(t, 2)) 
//	, 64));
		
		//Slightly farther
		public static final Path GoToLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":60},"mid1":{"x":260,"y":61},"mid2":{"x":263,"y":97},"end":{"x":312,"y":97}} */
		(3 + 210 * t + -213 * Math.pow(t, 2))/ (0 + 18 * t + 129 * Math.pow(t, 2)) 
		, 71));
			
		public static final Path GoToLeftCube = new Path(new PathSegment(t -> 
			/* {"start":{"x":260,"y":60},"mid1":{"x":252,"y":97},"mid2":{"x":245,"y":132},"end":{"x":245,"y":132}} */
			(111 + -12 * t + -99 * Math.pow(t, 2))/ (-24 + 6 * t + 18 * Math.pow(t, 2)) 
			, 74));
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//	/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":79},"mid2":{"x":249,"y":93},"end":{"x":249,"y":93}} */
//	(57 + -30 * t + -27 * Math.pow(t, 2))/ (-21 + 18 * t + 3 * Math.pow(t, 2)) 
//	, 35));
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":79},"mid2":{"x":249,"y":95},"end":{"x":249,"y":95}} */
//		(57 + -18 * t + -39 * Math.pow(t, 2))/ (-21 + 18 * t + 3 * Math.pow(t, 2)) 
//		, 37));
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":79},"mid2":{"x":242,"y":95},"end":{"x":242,"y":95}} */
//		(57 + -18 * t + -39 * Math.pow(t, 2))/ (-21 + -24 * t + 45 * Math.pow(t, 2)) 
//		, 40));
		
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":78},"mid2":{"x":245,"y":98},"end":{"x":245,"y":98}} */
//		(54 + 12 * t + -66 * Math.pow(t, 2))/ (-21 + -6 * t + 27 * Math.pow(t, 2)) 
//		, 41));
		
//		//Move slightly farther toward scale
//		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":78},"mid2":{"x":248,"y":98},"end":{"x":248,"y":98}} */
//		(54 + 12 * t + -66 * Math.pow(t, 2))/ (-21 + 12 * t + 9 * Math.pow(t, 2)) 
//		, 40));
		
		//Make path slightly longer
		//UNTESTED - Run on practice field before using
		public static final Path GetCubeFromLeftScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":260,"y":60},"mid1":{"x":253,"y":78},"mid2":{"x":247,"y":99},"end":{"x":247,"y":99}} */
		(54 + 18 * t + -72 * Math.pow(t, 2))/ (-21 + 6 * t + 15 * Math.pow(t, 2)) 
		, 42));
		
		// THESE PATHS BELOW MAY BE OUT OF ORDER =
		// 3 inches forwaard and six inches left
//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":200,"y":310},"end":{"x":253,"y":291}} */
//		(0 + 60 * t + -117 * Math.pow(t, 2))/ (510 + -840 * t + 489 * Math.pow(t, 2)) 
//		, 255));
		
		// Hit the Platform
//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":200,"y":310},"end":{"x":253,"y":256}} */
//		(0 + 60 * t + -222 * Math.pow(t, 2))/ (510 + -840 * t + 489 * Math.pow(t, 2)) 
//		, 266));
		// subtracted 24 inches from the end point in the y and added 45 inches in the x
//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":300},"mid1":{"x":50,"y":300},"mid2":{"x":200,"y":270},"end":{"x":253,"y":256}} */
//		(0 + -180 * t + 138 * Math.pow(t, 2))/ (150 + 600 * t + -591 * Math.pow(t, 2)) 
//		, 258));
//
//		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":0,"y":0},"mid1":{"x":50,"y":0},"mid2":{"x":200,"y":-30},"end":{"x":218,"y":-30}} */
//		(0 + -180 * t + 180 * Math.pow(t, 2))/ (150 + 600 * t + -696 * Math.pow(t, 2)) 
//	, 221));

		// changing to move end point to the right and turn later
//		new PathSegment(t -> 
//		/* {"start":{"x":0,"y":0},"mid1":{"x":200,"y":10},"mid2":{"x":200,"y":-30},"end":{"x":212,"y":-30}} */
//		(30 + -300 * t + 270 * Math.pow(t, 2))/ (600 + -1200 * t + 636 * Math.pow(t, 2)) 
//		, 219)

		public static final Path DriveIntoRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":0,"y":300},"mid1":{"x":170,"y":300},"mid2":{"x":200,"y":310},"end":{"x":256,"y":285}} */
		(0 + 60 * t + -135 * Math.pow(t, 2))/ (510 + -840 * t + 498 * Math.pow(t, 2)) 
		, 259));
		
//		public static final Path BackRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":256,"y":285},"mid1":{"x":242,"y":288},"mid2":{"x":227,"y":305},"end":{"x":230,"y":320}} */
//		(9 + 84 * t + -48 * Math.pow(t, 2))/ (-42 + -6 * t + 57 * Math.pow(t, 2)) 
//		, 48));	
		
		// Switched points
		public static final Path BackRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":230,"y":320},"mid1":{"x":227,"y":305},"mid2":{"x":242,"y":288},"end":{"x":256,"y":285}} */
		(-45 + -12 * t + 48 * Math.pow(t, 2))/ (-9 + 108 * t + -57 * Math.pow(t, 2)) 
		, 48));
	
		// deceasing distance and changing angle to be toward cube
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":256,"y":285},"mid1":{"x":240,"y":240},"mid2":{"x":214,"y":233},"end":{"x":214,"y":233}} */
//		(-135 + 228 * t + -93 * Math.pow(t, 2))/ (-48 + -60 * t + 108 * Math.pow(t, 2)) 
//		, 69));
		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":256,"y":285},"mid1":{"x":240,"y":240},"mid2":{"x":220,"y":220},"end":{"x":220,"y":225}} */
//		(-135 + 150 * t + 0 * Math.pow(t, 2))/ (-48 + -24 * t + 72 * Math.pow(t, 2)) 
//		, 73));
		
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":256,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":252,"y":239},"end":{"x":228,"y":233}} */
//		(-57 + -48 * t + 87 * Math.pow(t, 2))/ (-6 + 0 * t + -66 * Math.pow(t, 2)) 
//		, 64));
//		
		
		// 15 inch less and angle less
//		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":256,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":253},"end":{"x":228,"y":248}} */
//		(-57 + 36 * t + 6 * Math.pow(t, 2))/ (-6 + -72 * t + 42 * Math.pow(t, 2)) 
//		, 49));
		// took off 3 inches from the x endpoint
		public static final Path GetCubeFromRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":253,"y":285},"mid1":{"x":254,"y":266},"mid2":{"x":240,"y":253},"end":{"x":228,"y":248}} */
		(-57 + 36 * t + 6 * Math.pow(t, 2))/ (3 + -90 * t + 51 * Math.pow(t, 2)) 
		, 48));
		
//			public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//	/* {"start":{"x":328,"y":315},"mid1":{"x":331,"y":270},"mid2":{"x":276,"y":257},"end":{"x":228,"y":243}} */
//			(-135 + 192 * t + -99 * Math.pow(t, 2))/ (9 + -348 * t + 195 * Math.pow(t, 2)) 
//			, 134));
//		//Took off 15 inches on the y end point
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":328,"y":300},"mid1":{"x":331,"y":270},"mid2":{"x":276,"y":256},"end":{"x":228,"y":243}} */
//		(-90 + 96 * t + -45 * Math.pow(t, 2))/ (9 + -348 * t + 195 * Math.pow(t, 2)) 
//		, 123));

//	public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//	/* {"start":{"x":328,"y":290},"mid1":{"x":331,"y":270},"mid2":{"x":276,"y":256},"end":{"x":228,"y":243}} */
//	(-60 + 36 * t + -15 * Math.pow(t, 2))/ (9 + -348 * t + 195 * Math.pow(t, 2)) 
//	, 116));
//		}
		
		//trying to miss scale
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":328,"y":290},"mid1":{"x":331,"y":270},"mid2":{"x":297,"y":288},"end":{"x":228,"y":243}} */
//		(-60 + 228 * t + -303 * Math.pow(t, 2))/ (9 + -222 * t + 6 * Math.pow(t, 2)) 
//		, 115));
		
		//Trying to avoid Scale
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":328,"y":302},"mid1":{"x":331,"y":270},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
//		(-96 + 354 * t + -420 * Math.pow(t, 2))/ (9 + -222 * t + 6 * Math.pow(t, 2)) 
//		, 123));
		
		
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":328,"y":302},"mid1":{"x":321,"y":269},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
//		(-99 + 366 * t + -429 * Math.pow(t, 2))/ (-21 + -102 * t + -84 * Math.pow(t, 2)) 
//		, 121));
		
		//Less overshoot
//		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
//		/* {"start":{"x":316,"y":302},"mid1":{"x":309,"y":269},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
//		(-99 + 366 * t + -429 * Math.pow(t, 2))/ (-21 + -30 * t + -156 * Math.pow(t, 2)) 
//		, 110));
		
		//Still less overshoot
		public static final Path BackUpToRightScale = new Path(new PathSegment(t -> 
		/* {"start":{"x":304,"y":302},"mid1":{"x":297,"y":269},"mid2":{"x":297,"y":297},"end":{"x":228,"y":243}} */
		(-99 + 366 * t + -429 * Math.pow(t, 2))/ (-21 + 42 * t + -228 * Math.pow(t, 2)) 
		, 101));
	}

	public static Path straightPath(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}
