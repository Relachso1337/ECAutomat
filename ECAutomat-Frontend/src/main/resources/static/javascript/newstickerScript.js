var paragraphs = [];
var pointer = 0;
var isDoneReading = false;

async function mediaToArray() {
	let obj;
	const res = await fetch('../media/news/news.json')
	obj = await res.json();
	obj.forEach(element => {
		paragraphs.push(element.content);
	});
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
				window.location.replace("../html/mensa.html");
			}, delay);			
		}
	}, delay);
}

mediaToArray();
setTimeout(function() {
	var headlines = document.getElementById("headlines");
	var maincontainer = document.getElementById("imageandnewscontainer");
	headlines.classList.add("fadeOut");
	maincontainer.style.backgroundImage = 'linear-gradient(180deg, rgba(0,102,127,0.4) 0%, rgba(0,102,127,1) 100%), url("../media/news/1.jpg")';
	maincontainer.style.backgroundPosition = 'center';
	maincontainer.style.backgroundRepeat = 'no-repeat';
	maincontainer.style.backgroundSize = 'cover';
	headlines.remove();
	displayAllContent(25000);
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
