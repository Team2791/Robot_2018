function equation(start, cp1, cp2, end, len) {
	var xt = start.x + " + " + (3 * (cp1.x-start.x)) +"t + " + (3 * (start.x+cp2.x-2*cp1.x)) + "t^2 + " + ((end.x - start.x + 3 * cp1.x - 3 * cp2.x))+ "t^3"
	$('#xt').text("x(t) = " + xt);
	var yt = start.y + " + " + (3 * (cp1.y-start.y)) +"t + " + (3 * (start.y+cp2.y-2*cp1.y)) + "t^2 + " + ((end.y - start.y + 3 * cp1.y - 3 * cp2.y))+ "t^3"
	$('#yt').text("y(t) = " + yt);

	var dx = (3 * (cp1.x-start.x)) +" + " + (6 * (start.x+cp2.x-2*cp1.x)) + "t + " + (3 * (end.x - start.x + 3 * cp1.x - 3 * cp2.x))+ "t^2"
	var dy = (3 * (cp1.y-start.y)) +" + " + (6 * (start.y+cp2.y-2*cp1.y)) + "t + " + (3 * (end.y - start.y + 3 * cp1.y - 3 * cp2.y))+ "t^2"
	var dydx = `(${(3 * (cp1.y-start.y)) +" + " + (6 * (start.y+cp2.y-2*cp1.y)) + " * t + " + (3 * (end.y - start.y + 3 * cp1.y - 3 * cp2.y))+ " * Math.pow(t, 2)"})/
	(${(3 * (cp1.x-start.x)) +" + " + (6 * (start.x+cp2.x-2*cp1.x)) + " * t + " + (3 * (end.x - start.x + 3 * cp1.x - 3 * cp2.x))+ " * Math.pow(t, 2)"})`
	
	var endAngle = Math.atan2(end.y - cp2.y, end.x-cp2.x) * 180 / Math.PI;
	
	$("#dx").text("dx/dt = " + dx);
	$('#dy').text("dy/dt = " + dy);
	$('#len').text("len = " + Math.round(len*1000)/1000 + " end angle: " + Math.round(endAngle*1000)/1000) + "&#176;";
	$('#export').text("/* " + JSON.stringify({start: start, mid1: cp1, mid2: cp2, end:end}) + " */");
	$('#dydx').text(dydx);

	$('#java').html(`new PathSegment(t -> <br />
		/* ${JSON.stringify({start: start, mid1: cp1, mid2: cp2, end:end})} */<br />
		${dydx} <br />
		, ${Math.ceil(len)})`);
}

$(document).ready(function() {
	start = {x: 100, y: 25};
	mid1 = {x: 10, y: 90};
	mid2 = {x: 110, y: 100};
	end = {x: 150, y: 195};
	$('input').change(function() {
		curve =new Bezier(start , mid1 , mid2 , end);
		draw(curve);
		equation(start, mid1, mid2, end, curve.length());
	});
	$('#startx').change(function() {
		start.x = parseFloat($(this).val());
	});
	$('#starty').change(function() {
		start.y = parseFloat($(this).val());
	});

	$('#mid1x').change(function() {
		mid1.x = parseFloat($(this).val());
	});
	$('#mid1y').change(function() {
		mid1.y = parseFloat($(this).val());
	});

	$('#mid2x').change(function() {
		mid2.x = parseFloat($(this).val());
	});
	$('#mid2y').change(function() {
		mid2.y = parseFloat($(this).val());
	});

	$('#endx').change(function() {
		end.x = parseFloat($(this).val());
	});
	$('#endy').change(function() {
		end.y = parseFloat($(this).val());
	});
	$('#importtext').click(function() {
		let imp = JSON.parse($("#import").val());
		start.x = imp.start.x;
		start.y = imp.start.y;

		mid1.x = imp.mid1.x;
		mid1.y = imp.mid1.y;

		mid2.x = imp.mid2.x;
		mid2.y = imp.mid2.y;

		end.x = imp.end.x;
		end.y = imp.end.y;

		$('#startx').val(start.x);
		$('#starty').val(start.y);

		$('#endx').val(end.x);
		$('#endy').val(end.y);

		$('#mid1x').val(mid1.x);
		$('#mid1y').val(mid1.y);

		$('#mid2x').val(mid2.x);
		$('#mid2y').val(mid2.y);
		
		curve =new Bezier(start , mid1 , mid2 , end);
		draw(curve);
		equation(start, mid1, mid2, end, curve.length());
	});
	$(document).keypress((e)=>{
		update();
	});

	//setInterval(update, 100);
});

function update() {
	start.x = parseFloat($('#startx').val());
	start.y = parseFloat($('#starty').val());

	mid1.x = parseFloat($('#mid1x').val());
	mid1.y = parseFloat($('#mid1y').val());

	mid2.x = parseFloat($('#mid2x').val());
	mid2.y = parseFloat($('#mid2y').val());

	end.x = parseFloat($('#endx').val());
	end.y = parseFloat($('#endy').val());

	// $('#startx').val(start.x);
	// $('#starty').val(start.y);

	// $('#endx').val(end.x);
	// $('#endy').val(end.y);

	// $('#mid1x').val(mid1.x);
	// $('#mid1y').val(mid1.y);

	// $('#mid2x').val(mid2.x);
	// $('#mid2y').val(mid2.y);

	curve =new Bezier(start , mid1 , mid2 , end);
	draw(curve);
	equation(start, mid1, mid2, end, curve.length());
}
