const startButton = document.getElementById('startBtn')
const k1button = document.getElementById('k1-btn')
const k2button = document.getElementById('k2-btn')
const k3button = document.getElementById('k3-btn')
const highscoreButton = document.getElementById('highscoreBtn')
const modebuttons = document.getElementById('mode')
const modebutton1 = document.getElementById('m1-btn')
const modebutton2 = document.getElementById('m2-btn')
const katdiv = document.getElementById('kid')
const trueBtn = document.getElementById("trueBtn")
const falseBtn = document.getElementById("falseBtn")
const questionContainerElement = document.getElementById('question-container')
const startMenuElement = document.getElementById('startDiv')
const questionElement = document.getElementById('q-btn')
const timeButton = document.getElementById('timeBtn')
const pointsButton = document.getElementById('pointsBtn')
const backButton = document.getElementById('back-btn')
const repeatButton = document.getElementById('repeatBtn')
const quitButton = document.getElementById('quitBtn')
const gameoverScreen = document.getElementById('gameoverScreen')
const highscoreScreen = document.getElementById('highscoreScreen')
let k1 = false;
let k2 = false;
let k3 = false;
let points = 0;
let isSpeedmode = false;
let currentPage = 1;
var timer;
var minutes;
var seconds;
let obj;
let fetchString = '../../../../../../ECAutomat-Backend/src/main/resources/static/quiz.json';
var interval;
// ------------------Create Random Number-----------------------------------


function countdown() {
  clearInterval(interval);
  interval = setInterval(function () {
    seconds -= 1;
    if (minutes < 0) return;
    else if (seconds < 0 && minutes != 0) {
      minutes -= 1;
      seconds = 59;
    }
    else if (seconds < 10) seconds = '0' + seconds;

    timeButton.innerHTML = minutes + ':' + seconds;

    if (minutes == 0 && seconds == 0) clearInterval(interval);
  }, 1000);
}

function getRandomNumber(min, max) {
  let step1 = max - min + 1;
  let step2 = Math.random() * step1;
  let result = Math.floor(step2) + min;
  return result;
}
function createArrayOfNumbers(start, end) {
  let myArray = [];
  for (let i = start; i <= end; i++) {
    myArray.push(i);
  }
  return myArray;
}
let numbersArray = createArrayOfNumbers(1, 100);
//------------------------------------------------------
/*
if(numbersArray.length == 0){
  output.innerText = 'No More Random Numbers';
  return;
}
*/
fetch(fetchString)
  .then(res => res.json())
  .then(data => {
    obj = data;
  })
  .then(() => {
    trueBtn.addEventListener('click', () => {
      falseBtn.style.backgroundColor = "#0066B3";
      let randomIndex = getRandomNumber(0, numbersArray.length - 1);
      let randomNumber = numbersArray[randomIndex];
      numbersArray.splice(randomIndex, 1);
      let anser = isCorrect(true, obj.questions[randomNumber].answer);
      questionElement.innerText = obj.questions[randomNumber].question;
      if (anser) {
        setTimeout(() => {
          trueBtn.style.backgroundColor = "#0066B3";
        }, "1000")
        trueBtn.style.backgroundColor = "#B4C424"
        points++;
        pointsButton.innerText = points;
      } else {
        setTimeout(() => {
          trueBtn.style.backgroundColor = "#0066B3";
          if (!isSpeedmode) {
            gameOver();
          }
        }, "1000")
        trueBtn.style.backgroundColor = "Red"
      }
    })

    falseBtn.addEventListener('click', () => {
      trueBtn.style.backgroundColor = "#0066B3";
      let randomIndex = getRandomNumber(0, numbersArray.length - 1);
      let randomNumber = numbersArray[randomIndex];
      numbersArray.splice(randomIndex, 1);
      let anser = isCorrect(false, obj.questions[randomNumber].answer);
      questionElement.innerText = obj.questions[randomNumber].question;
      if (anser) {
        setTimeout(() => {
          falseBtn.style.backgroundColor = "#0066B3";
        }, "1000")
        falseBtn.style.backgroundColor = "#B4C424"
        points++;
        pointsButton.innerText = points;
      } else {
        setTimeout(() => {
          falseBtn.style.backgroundColor = "#0066B3";
          if (!isSpeedmode) {
            gameOver();
          }
        }, "1000")
        falseBtn.style.backgroundColor = "Red"
      }
    })

  });

k1button.addEventListener('click', hideCategory)
k2button.addEventListener('click', hideCategory)
k3button.addEventListener('click', hideCategory)

startButton.addEventListener('click', () => {
  currentPage++;
  startMenuElement.classList.add('hide')
  katdiv.classList.remove('hide');
})


modebutton1.addEventListener('click', () => {
  currentPage++;
  timeBtn.classList.add('hide')
  startGame();
})

modebutton2.addEventListener('click', () => {
  currentPage++;
  timeBtn.classList.remove('hide')
  isSpeedmode = true;
  startGame();
})

highscoreButton.addEventListener('click', () => {
  console.log(currentPage)
  currentPage = 5;
  highscoreScreen.classList.remove('hide');
  katdiv.classList.add('hide')
  startDiv.classList.add('hide');
})

backButton.addEventListener('click', () => {
  console.log(currentPage)
  if (currentPage == 2) {
    setScreen(2);
  } else if (currentPage == 3) {
    setScreen(3);
  } else if (currentPage == 4) {
    setScreen(4);
  }
  else if (currentPage == 5) {
    setScreen(5);
  }
})

repeatButton.addEventListener('click', () => {
  setScreen(4);
  setScreen(3);
})


quitButton.addEventListener('click', () => {
  window.location.href = 'startseite.html';
})


function startGame() {
  resetTime();
  countdown();
  let randomIndex = getRandomNumber(0, numbersArray.length - 1);
  let randomNumber = numbersArray[randomIndex];
  numbersArray.splice(randomIndex, 1);
  pointsButton.innerText = 0;
  points = 0;
  questionElement.innerText = obj.questions[randomNumber].question;
  modebuttons.classList.add('hide')
  questionContainerElement.classList.remove('hide')
  questionContainerElement.style.display = 'block';
  gameoverScreen.classList.add('hide');
  if (isSpeedmode) {
    setTimeout(() => {
      gameOver();
    }, "121000")
  }
  //setNextQuestion()
}

function hideCategory() {
  currentPage++;
  k1button.classList.add('hide')
  k2button.classList.add('hide')
  k3button.classList.add('hide')
  modebuttons.classList.remove('hide')
}

function resetTime() {
  timeButton.innerText = "2:00";
  timer = timeButton.innerHTML;
  timer = timer.split(':');
  minutes = timer[0];
  seconds = timer[1];
}

function isCorrect(answer, realanswer) {
  return answer == realanswer;
}

function setScreen(page) {
  if (page == 2) {
    currentPage--
    katdiv.classList.add('hide');
    startDiv.classList.remove('hide')
  }
  else if (page == 3) {
    currentPage--;
    modebuttons.classList.add('hide')
    k1button.classList.remove('hide')
    k2button.classList.remove('hide')
    k3button.classList.remove('hide')
  } else if (page == 4) {
    currentPage--;
    isSpeedmode = false;
    gameoverScreen.classList.add('hide');
    questionContainerElement.classList.add('hide')
    modebuttons.classList.remove('hide')
  } else if (page == 5) {
    currentPage = 1;
    highscoreScreen.classList.add('hide')
    startDiv.classList.remove('hide')
  }
}

function gameOver() {
  gameoverScreen.classList.remove('hide');
  questionContainerElement.style.display = 'none';
}

if (timeButton.innerText === "0:00") {
  questionContainerElement.style.display = 'none';
  //block
}
function setTimer() {
  setTimeout
}
setTimeout(() => {
  falseBtn.style.backgroundColor = "#0066B3";
}, "10000")
