var paragraphs = [];
var pointer = 0;
var isDoneReading = false;

window.onload = function() {
	mediaToArray();
}

const randomNbr = Math.floor(Math.random() * ((10-1)+ 1))  // Math.floor(Math.random() * (max - min + 1) + min)

async function mediaToArray() {
	const res = await fetch(`../media/news/news${randomNbr}.json`)
	var maincontainer = document.getElementById("imageandnewscontainer");
	maincontainer.style.backgroundImage = `linear-gradient(180deg, rgba(0,102,127,0.4) 0%, rgba(0,102,127,1) 100%), url("../media/news/${randomNbr}.jpg")`;
	maincontainer.style.backgroundPosition = 'center';
	maincontainer.style.backgroundRepeat = 'no-repeat';
	maincontainer.style.backgroundSize = 'cover';
	let obj;
	obj = await res.json();
	obj.paragraphs.forEach(element => {
		paragraphs.push(element.content);
	});
	var headline = document.getElementById("headlines");
	var author = document.getElementById("author");
	headline.innerHTML = obj.headline;
	author.innerHTML = "Author: " + obj.author;
	// var qrcode = new QRCode("qrcode", {
	// 	text: "wdaawd",
	// 	width: 128,
	// 	height: 128,
	// 	correctionLevel: QRCode.CorrectLevel.H
	// })
	// qrcode.clear(); // clear the code.
	// qrcode.makeCode(obj.url); // make another code.
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
				window.location.reload();
			}, delay);			
		}
	}, delay);
}
setTimeout(function() {
	var headlines = document.getElementById("headlinecontainer");
	headlines.classList.remove("fadeOut");
	headlines.classList.add("fadeOut");
	setTimeout(function() {
		headlines.remove();
		displayAllContent(5000);
	}, 1500);
}, 5000);