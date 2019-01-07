
/*d3.json("/adpostm/adsPerCategory")
	.then(function(data){
		var svgWidth = 250;
		var svgHeight = 200;
		var barWidth = 10;
		var margin = 2;
		var constant = 40;
		var dataSet = new Array(data.length);
		var domain = new Array(data.length);

		console.log(data);
		var container = d3.select("-  ")
			.append("svg")
			.attr("width", svgWidth)
			.attr("height", svgHeight);
		
		for(i=0; i < data.length; i++){
			dataSet[i] = data[i][1]* constant;
			domain[i] = data[i][0];
		}

		var x = d3.scaleLinear()
			.range([0, svgWidth]);
		var y = d3.scaleBand()
			.range([0, svgHeight]);
		
		x.domain([0, d3.max(function(d, i){
			return data
		})])
		y.domain(data.map(function(d, i){
			return data[i][0];
		}));
		
		container.selectAll("rect")
			.data(data)
			.enter()
			.append("rect")
			.attr("y", function(d){
				return y(d[0]);
			})
			.attr("width", function(d, i){
				return d[1]*constant;
			})
			.attr("height", barWidth - margin)
			.attr("transform", function(d, i){
				var translate = [60, (barWidth + margin)];
		         return "translate("+ translate +")";
			})
			.style("fill", "#A4C3B2");
		
		container.append("g")
			.attr("transform", "translate(0," + 100+ ")")
			.call(d3.axisBottom(x));
		
		container.append("g")
		.call(d3.axisLeft(y))
		.attr("transform", function(){
				var translate = [60, 0];
		         return "translate("+ translate +")";
			});

});*/

//https://codepen.io/knbuffum/pen/pbzBWE
d3.json("/adpostm/advert/statusCount")
	.then(function(data){
		
		var svgWidth = 250;
		var svgHeight = 200;
		var totalRadius = Math.min(svgWidth, svgHeight) / 3;
		var holeRadius = totalRadius *0.3;
		var domain = new Array(data.length);
				
		for(i=0; i<data.length; i++){
			domain[i] = data[i][0];
		}
				
		var color = d3.scaleOrdinal()
		.domain(domain)
		.range(["#BFC0C0","#4F5D75","#2D3142"]);
		
		var color_d3 = d3.scaleOrdinal(d3.schemePastel2);
		
		
		var container = d3.select("#adStatusGraph")
			.append("svg")
			.attr("width", svgWidth)
			.attr("height", svgHeight)
			.append("g")
			.attr("transform", function(){
				var translate = [svgWidth/2,svgHeight/2];
				return "translate("+translate+")";
			});
		
		var arc = d3.arc()
			.innerRadius(totalRadius - holeRadius)
			.outerRadius(totalRadius);
		
		var pie = d3.pie()
			.value((d) => d[1])
			.sort(null);
		
		var path = container.selectAll("path")
			.data(pie(data))
			.enter()
			.append("path")
			.attr("d", arc)
			.attr("fill", (d,i) => color_d3(d.data[0]));
		
		//Legend
		var itemWidth = 8;
		var margin = 5;
		var legendWidth = 70;
		
		var legend = container.selectAll(".legend")
			.data(color.domain())
			.enter()
			.append("g")
			.attr("class", "legend")
			.attr("transform", function(d,i){
				var translate = [(legendWidth*i - svgWidth/2),svgHeight/3 + 10];
				return "translate("+translate+")";
			});
		//Each rectangle
		legend.append("rect")
			.attr("width", itemWidth)
			.attr("height", itemWidth)
			.style("fill", color_d3);
		
		legend.append("text")
			.attr("x", itemWidth + margin)
			.attr("y", itemWidth)
			.text((d) => d);
	});

//http://bl.ocks.org/cmdoptesc/6226150
d3.json("/adpostm/adsPerCategory")
	.then(function(data){
		var margin = 10;
		var svgWidth = 250;
		var svgHeight = 200;
		var minRadius = 10;
		var arcWidth = 10;
		var arcPadding = 1;
		var dataSet = new Array(data.length);
		var domain = new Array(data.length);
		
		for(i=0; i<data.length; i++){
			dataSet[i] = data[i][1];
			domain[i] = data[i][0];
		}
		
		var toolTip = d3.select("#adsPerCategory")
		.append("div")
		.attr("class", "tooltip")
		.style("opacity", 0);
		
		var color = d3.scaleOrdinal(d3.schemePastel2) 
		
		var svg = d3.select("#adsPerCategory")
			.append("svg")
			.attr("width", svgWidth)
			.attr("height", svgHeight)
			.append("g")
			.attr("transform", function(){
				var translate = [svgWidth/2,svgHeight/2];
				return "translate("+translate+")";
			});

		var arcGenerator = d3.arc()
			.innerRadius(function(d, i){
				return minRadius + i * arcWidth + arcPadding;
			})
			.outerRadius(function(d, i){
				return minRadius + (i +1)* arcWidth;
			})
			.startAngle(0 * Math.PI/180)
			.endAngle(function(d, i){
				return d * 50 * Math.PI/180;
			});
			
	
		var path = svg.selectAll("path")
			.data(dataSet)
			.enter()
			.append("path")
			.attr("d", arcGenerator)
			.attr("fill", (d, i) => color(d))
			.on("mouseover", function(d, i){
				toolTip.style("opacity", 0.9);
				toolTip.html("Category: " + domain[i] + "(" + d + ")")
				.style("left", 10 +"px")
				.style("top", (svgHeight-10)+"px")
				.style("color", "#666");
			})
			.on("mouseout", function(d, i){
				toolTip.style("opacity", 0);
			});
		
		
		
		

	});