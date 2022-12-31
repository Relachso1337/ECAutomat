async function foo() {
	let obj;
	const res = await fetch('../media/news/news.json')
	obj = await res.json();
	var container = document.getElementById("newscontainer");
	obj.forEach(element => {
		var node = document.createElement("div");
		node.classList.add("news-paragraph");
		node.classList.add("my-5");
		node.innerHTML = element.content;
		container.appendChild(node);
	});
}

foo();


setTimeout(function() {
	var container = document.getElementById("redcontainer");
	container.classList.remove("slideOut");
	void container.offsetWidth;
	container.classList.add("slideOut");
	setTimeout(function() {
		window.location.replace("../html/mensa.html");
	}, 1400);

}, 180000);
