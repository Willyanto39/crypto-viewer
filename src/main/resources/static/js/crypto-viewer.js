$(document).ready(function(){
	$("button").click(function(){
		var cryptoId = $("select option:selected").val();
		var url = config.apiHost + "/api/v1/cryptoviewer";

		$.ajax({
			url : url,
			type: 'GET',
			data: {
				id: cryptoId,
				convert: 'IDR'
			},
			dataType: 'json',

			success: function(data) {
				var responseData = data["data"];
				var symbol = responseData["symbol"];
				
				$(".crypto-name").text(responseData["name"]);
				$(".crypto-price").text("Rp. " + responseData["price"].toLocaleString("id"));
				$(".crypto-market-cap").text("Rp. " + responseData["marketCap"].toLocaleString("id"));
				$(".crypto-24h-volume").text("Rp. " + responseData["volume24Hour"].toLocaleString("id"));
				$(".circulating-supply").text(responseData["circulatingSupply"].toLocaleString("id") + " " + symbol);
				$(".total-supply").text(responseData["totalSupply"].toLocaleString("id") + " " + symbol);
			},
			fail: function() {
				alert("API call failed");
			}
		});
	});
});
