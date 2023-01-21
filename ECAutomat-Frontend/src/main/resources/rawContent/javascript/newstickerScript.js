var paragraphs = [];
var articleStr;
var headlineShowTimeMS = 5000;  // shows each paragraphWordCounts-amount Words - default 5 sec for 45 Words
var paragraphShowTimeMS = 10000; // shows each paragraphWordCounts-amount Words - default 10 sec for 45 Words
var paragraphWordCount = 45;  // amounts of words to show
var paragraphNbr = 0; // pointer for linebreak
const randomNbr = Math.floor(Math.random() * ((10-1)+ 1))  // Math.floor(Math.random() * (max - min + 1) + min)  // used for random article From files
window.onload = function() {
	mediaToArray();
}


/**
 * gets file from directory and sets background
 */
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
	articleStr = paragraphsToSingleString().split(" ");
}

/**
 * counts the words from paragraphs[0]..paragraphs[ind_].
 * @param {int} ind_ index of paragraph-content
 * @returns counted words
 */
function countParagraphWords(ind_) {
	if (ind_ >= paragraphs.length) {
		return -1;
	}
	var counter = 0;
	for(var i=0; i<ind_; i++) {
		counter += paragraphs[i].split(" ").length;
	}
	return counter;
}

/**
 * merges all words from paragraphs in a string.
 * @returns merged words from paragraphs-list.
 */
function paragraphsToSingleString() {
	var resStr = "";
	for(var i=0; i<paragraphs.length; i++) {
		resStr += paragraphs[i] + " ";
	}
	return resStr;
}

/**
 * Creates new DOMElement div with given content in it, and appends it to container.
 * @param {*} content - (String) content to add in node.
 * @param {*} container - DOMElement to append node
 */
function createNewsParagraphDiv(content, container) {
	var newNode = document.createElement("div");
	newNode.classList.add("news-paragraph");
	newNode.classList.add("fadeIn");
	newNode.classList.add("my-3");
	newNode.innerHTML = content;
	container.appendChild(newNode);
}

/**
 * chunks words from articleStr (global), starts with startIndex and ends with (startIndex + paragraphWordCount)
 * adds a linebreak if a new Paragraph is given.
 * @param {*} startIndex - index of word to start with.
 */
function singleStringToNewsContent(startIndex) {
	var showContent = "";
	var container = document.getElementById("newscontainer");
	container.innerHTML = "";
	for(var i=startIndex; i<startIndex+paragraphWordCount; i++) {
		if (i<articleStr.length) {
			showContent += articleStr[i] + " ";
			if (i === countParagraphWords(paragraphNbr) - 1) {
				showContent += "<br>";
				paragraphNbr++;
			}
		} else {
			break;
		}
	}
	paragraphNbr++;
	createNewsParagraphDiv(showContent, container);
}

/**
 * main
 */
setTimeout(function() {
	var headline = document.getElementById("headlinecontainer")
	headline.remove();
	var wordCounter = 0;
	singleStringToNewsContent(wordCounter);
	wordCounter += paragraphWordCount;
	setInterval(function() {
		if (wordCounter >= articleStr.length) {  // reloads page to change article
			window.location.reload();
		}
		singleStringToNewsContent(wordCounter);
		wordCounter += paragraphWordCount;
	}, paragraphShowTimeMS); 
}, headlineShowTimeMS);