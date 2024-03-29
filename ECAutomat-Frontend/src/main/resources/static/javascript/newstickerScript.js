var paragraphs = [];
var pointer = 0;
var isDoneReading = false;

window.onload = function() {
	mediaToArray();
}

async function mediaToArray() {
	let obj;
	const res = await fetch('../media/news/news.json')
	obj = await res.json();
	obj.paragraphs.forEach(element => {
		paragraphs.push(element.content);
	});
	var headline = document.getElementById("headlines");
	var author = document.getElementById("author");
	headline.innerHTML = obj.headline;
	author.innerHTML = "Author: " + obj.author;
	var qrcode = new QRCode("qrcode", {
		text: "wdaawd",
		width: 128,
		height: 128,
		correctionLevel: QRCode.CorrectLevel.H
	})
	qrcode.clear(); // clear the code.
	qrcode.makeCode(obj.url); // make another code.
}

function convertContent(content) {
	var container = document.getElementById("newscontainer");
	var node = document.createElement("div");
	node.classList.add("news-paragraph");
	node.classList.add("fadeIn");
	node.classList.add("my-3");
	node.innerHTML = content;
	container.appendChild(node);

	if (node.getBoundingClientRect().bottom > window.screen.height * 0.8) {
		container.removeChild(node); // prevents overflow
	} else {
		pointer++;
	}
};

function displayAllContent(delay) {
	var container = document.getElementById("newscontainer");
	for(let i=pointer; i < paragraphs.length; i++) {
		convertContent(paragraphs[i]);
	}
	setTimeout(function() {
		container.innerHTML = "";
		for(let i=pointer; i < paragraphs.length; i++) {
			convertContent(paragraphs[i]);
		}
		if (pointer < paragraphs.length) {
			displayAllContent(delay);
		} else {
			isDoneReading = true;
		}
		if (isDoneReading) {
			setTimeout(function() {
				window.location.replace("http://127.0.0.1:5500/ECAutomat-Frontend/src/main/resources/rawContent/html/mensa.html");
			}, delay);			
		}
	}, delay);
}
setTimeout(function() {
	var headlines = document.getElementById("headlinecontainer");
	var maincontainer = document.getElementById("imageandnewscontainer");
	headlines.classList.remove("fadeOut");
	headlines.classList.add("fadeOut");
	setTimeout(function() {
		maincontainer.style.backgroundImage = 'linear-gradient(180deg, rgba(0,102,127,0.4) 0%, rgba(0,102,127,1) 100%), url("../media/news/1.jpg")';
		maincontainer.style.backgroundPosition = 'center';
		maincontainer.style.backgroundRepeat = 'no-repeat';
		maincontainer.style.backgroundSize = 'cover';
		headlines.remove();
		displayAllContent(25000);
	}, 1500);
}, 5000);


// setTimeout(function() {
// 	// var container = document.getElementById("redcontainer");
// 	// container.classList.remove("slideOut");
// 	// void container.offsetWidth;
// 	// container.classList.add("slideOut");
// 	setTimeout(function() {
// 		window.location.replace("../html/mensa.html");
// 	}, 1400);

// }, 180000);
